package br.com.mutant.checker.service;

import br.com.mutant.checker.component.PositionMapper;
import br.com.mutant.checker.domain.entity.CheckResult;
import br.com.mutant.checker.domain.entity.Kind;
import br.com.mutant.checker.domain.vo.Position;
import br.com.mutant.checker.dto.DnaCheckerRequestDto;
import br.com.mutant.checker.repository.CheckResultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class MutantCheckServiceImpl implements MutantCheckService {

    private final Logger logger = LoggerFactory.getLogger(MutantCheckServiceImpl.class);

    private static final String VALIDATE_REQUEST_PATTERN = "[ATCG]+";

    private static final String INVALID_DNA_ERROR_MESSAGE = "Invalid DNA";

    private static final String INVALID_INPUT_TABLE_ERROR_MESSAGE = "Input table with invalid format";

    private static final int DETECTION_NUMBER = 4;

    private final CheckResultRepository checkResultRepository;

    private final List<PositionMapper> positionMappers;

    @Autowired
    public MutantCheckServiceImpl(CheckResultRepository checkResultRepository, List<PositionMapper> positionMappers) {
        this.checkResultRepository = checkResultRepository;
        this.positionMappers = positionMappers;
    }

    @Override
    public void validateRequest(DnaCheckerRequestDto dnaCheckerRequestDto) {
        Pattern pattern = Pattern.compile(VALIDATE_REQUEST_PATTERN);
        Arrays.asList(dnaCheckerRequestDto.getDna()).forEach(i -> {
            if (i.length() != dnaCheckerRequestDto.getDna().length) {
                logger.info(INVALID_INPUT_TABLE_ERROR_MESSAGE + ", dna={}", Arrays.toString(dnaCheckerRequestDto.getDna()));
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, INVALID_INPUT_TABLE_ERROR_MESSAGE);
            } else if (!pattern.matcher(i).matches()) {
                logger.info(INVALID_DNA_ERROR_MESSAGE + ", dna={}", Arrays.toString(dnaCheckerRequestDto.getDna()));
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, INVALID_DNA_ERROR_MESSAGE);
            }
        });
    }

    @Override
    @Cacheable("checkResultCacheName")
    public boolean isMutant(String[] dna) {
        char[][] matrix = convertDnaToMatrix(dna);
        List<List<Position>> rootPositions = new ArrayList<>();
        positionMappers.forEach(i -> rootPositions.addAll(i.getPositions(matrix, DETECTION_NUMBER)));
        return checkPositions(rootPositions, matrix);
    }

    @Override
    public void saveResult(boolean result, String[] dna) {
        CheckResult checkResult = new CheckResult();
        checkResult.setDna(Arrays.toString(dna));
        checkResult.setCreateOn(LocalDateTime.now());
        checkResult.setKind(result ? Kind.MUTANT : Kind.HUMAN);
        checkResultRepository.save(checkResult);
    }

    private boolean checkPositions(List<List<Position>> rootPositions, char[][] matrix) {
        for (List<Position> positions : rootPositions) {
            int counter = 1;
            char lastChar = 0;
            for (Position p : positions) {
                char c = matrix[p.getLine()][p.getColumn()];
                if (c == lastChar) {
                    counter += 1;
                } else {
                    counter = 1;
                }
                if (counter == DETECTION_NUMBER) {
                    logger.info("Mutant detected by the character [{}] at position [{}] of the sequence [{}]", c, p, positions);
                    return true;
                }
                lastChar = c;
            }
        }
        return false;
    }

    private char[][] convertDnaToMatrix(String[] dna) {
        return Arrays.stream(dna)
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }
}
