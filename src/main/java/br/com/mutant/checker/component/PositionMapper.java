package br.com.mutant.checker.component;

import br.com.mutant.checker.domain.vo.Position;

import java.util.List;

public interface PositionMapper {

    List<List<Position>> getPositions(char[][] matrix, int detectionNumber);
}
