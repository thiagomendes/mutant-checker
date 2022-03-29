package br.com.mutant.checker.service;

import br.com.mutant.checker.dto.DnaCheckerRequestDto;
import br.com.mutant.checker.vo.Position;
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

    @Override
    public void validateRequest(DnaCheckerRequestDto dnaCheckerRequestDto) {
        Pattern pattern = Pattern.compile(VALIDATE_REQUEST_PATTERN);
        Arrays.asList(dnaCheckerRequestDto.getDna()).forEach(i -> {
            if (!pattern.matcher(i).matches()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, INVALID_DNA_ERRO_MESSAGE);
            }
        });
    }

    @Override
    public boolean isMutant(String[] dna) {
        char[][] matrix = convertDnaToMatrix(dna);
        List<List<Position>> rootPositions = getPositions(matrix, Position.VERTICAL_DIRECTION);
        rootPositions.addAll(getPositions(matrix, Position.HORIZONTAL_DIRECTION));
        return checkPositions(rootPositions, matrix);
    }

    private boolean checkPositions(List<List<Position>> rootPositions, char[][] matrix) {
        for (List<Position> positions : rootPositions) {
            int counter = 1;
            char lastChar = 0;
            for (Position p : positions) {
                char c = matrix[p.getX()][p.getY()];
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

    private List<List<Position>> getPositions(char[][] matrix, int direction) {
        List<List<Position>> root = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            List<Position> positions = new ArrayList<>();
            for (int p = 0; p < matrix.length; p++) {
                if (direction == Position.HORIZONTAL_DIRECTION) {
                    positions.add(new Position(i, p));
                } else {
                    positions.add(new Position(p, i));
                }
            }
            root.add(positions);
        }
        return root;
    }

    public List<Position> addMockPosition() {
        Position positionDto1 = new Position(0, 4);
        Position positionDto2 = new Position(1, 4);
        Position positionDto3 = new Position(2, 4);
        Position positionDto4 = new Position(3, 4);
        Position positionDto5 = new Position(4, 4);
        Position positionDto6 = new Position(5, 4);
        return Arrays.asList(positionDto1, positionDto2, positionDto3, positionDto4, positionDto5, positionDto6);
    }

    private char[][] convertDnaToMatrix(String[] dna) {
        return Arrays.stream(dna)
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }
}
