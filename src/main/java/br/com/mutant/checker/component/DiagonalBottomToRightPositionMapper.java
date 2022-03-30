package br.com.mutant.checker.component;

import br.com.mutant.checker.domain.vo.Position;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DiagonalBottomToRightPositionMapper implements PositionMapper {
    @Override
    public List<List<Position>> getPositions(char[][] matrix, int detectionNumber) {
        List<List<Position>> root = new ArrayList<>();
        for (int p0 = 0; p0 < matrix.length; p0++) {
            List<Position> positions = new ArrayList<>();
            for (int p1 = matrix.length - 1; p1 >= 0; p1--) {
                int n = (matrix.length - 1) - (p1 - p0);
                if (n <= matrix.length - 1) {
                    positions.add(new Position(p1, n));
                }
            }
            if (positions.size() >= detectionNumber) {
                root.add(positions);
            }
        }
        return root;
    }
}
