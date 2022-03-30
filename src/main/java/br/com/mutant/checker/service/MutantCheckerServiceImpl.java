package br.com.mutant.checker.service;

import br.com.mutant.checker.component.PositionMapper;
import br.com.mutant.checker.dto.DnaCheckerRequestDto;
import br.com.mutant.checker.domain.vo.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class MutantCheckerServiceImpl implements MutantCheckerService {

    private static final String VALIDATE_REQUEST_PATTERN = "[ATCG]+";

    private static final String INVALID_DNA_ERRO_MESSAGE = "Invalid DNA";

    private static final String INVALID_INPUT_TABLE_MESSAGE = "Input table with invalid format";

    @Autowired
    private List<PositionMapper> positionMappers;

    @Override
    public void validateRequest(DnaCheckerRequestDto dnaCheckerRequestDto) {
        Pattern pattern = Pattern.compile(VALIDATE_REQUEST_PATTERN);
        Arrays.asList(dnaCheckerRequestDto.getDna()).forEach(i -> {
            if (i.length() != dnaCheckerRequestDto.getDna().length) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, INVALID_INPUT_TABLE_MESSAGE);
            } else if (!pattern.matcher(i).matches()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, INVALID_DNA_ERRO_MESSAGE);
            }
        });
    }

    @Override
    public boolean isMutant(String[] dna) {
        char[][] matrix = convertDnaToMatrix(dna);
        List<List<Position>> rootPositions = new ArrayList<>();
        positionMappers.forEach(i -> rootPositions.addAll(i.getPositions(matrix)));
        return checkPositions(rootPositions, matrix);
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
                if (counter == 4) {
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
