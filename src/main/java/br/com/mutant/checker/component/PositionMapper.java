package br.com.mutant.checker.component;

import br.com.mutant.checker.domain.vo.Position;

import java.util.List;

public interface PositionMapper {

    int DETECTION_NUMBER = 4;

    List<List<Position>> getPositions(char[][] matrix);
}
