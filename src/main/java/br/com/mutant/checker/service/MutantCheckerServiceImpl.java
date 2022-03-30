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

    private static final String INVALID_INPUT_TABLE_MESSAGE = "Input table with invalid format";

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
        rootPositions.addAll(getDiagonalPositions(matrix, Position.DIAGONAL_DIRECTION_TOP_TO_LEFT));
        rootPositions.addAll(getDiagonalPositions(matrix, Position.DIAGONAL_DIRECTION_TOP_TO_RIGHT));
        rootPositions.addAll(getVerticalAndHorizontalPositions(matrix, Position.VERTICAL_DIRECTION));
        rootPositions.addAll(getVerticalAndHorizontalPositions(matrix, Position.HORIZONTAL_DIRECTION));
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

    private List<List<Position>> getVerticalAndHorizontalPositions(char[][] matrix, int direction) {
        List<List<Position>> root = new ArrayList<>();
        for (int line = 0; line < matrix.length; line++) {
            List<Position> positions = new ArrayList<>();
            for (int column = 0; column < matrix.length; column++) {
                if (direction == Position.HORIZONTAL_DIRECTION) {
                    positions.add(new Position(line, column));
                } else if (direction == Position.VERTICAL_DIRECTION) {
                    positions.add(new Position(column, line));
                }
            }
            if (positions.size() >= 4) {
                root.add(positions);
            }
        }
        return root;
    }

    private List<List<Position>> getDiagonalPositions(char[][] matrix, int direction) {

        List<List<Position>> root = new ArrayList<>();

        if (direction == Position.DIAGONAL_DIRECTION_TOP_TO_RIGHT) {
            for (int line = 0; line < matrix.length; line++) {
                List<Position> positions = new ArrayList<>();
                for (int column = 0; column < matrix.length; column++) {
                    if (column + line < matrix.length) {
                        positions.add(new Position(column, column + line));
                    }
                }
                if (positions.size() >= 4) {
                    root.add(positions);
                }
            }
        } else if (direction == Position.DIAGONAL_DIRECTION_TOP_TO_LEFT) {
            for (int line = matrix.length - 1; line > 0; line--) {
                List<Position> positions = new ArrayList<>();
                for (int column = 0; column < matrix.length; column++) {
                    if (line - column >= 0) {
                        positions.add(new Position(column, line - column));
                    }
                }
                if (positions.size() >= 4) {
                    root.add(positions);
                }
            }
        }

        return root;
    }

    private char[][] convertDnaToMatrix(String[] dna) {
        return Arrays.stream(dna)
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }
}
