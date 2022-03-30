package br.com.mutant.checker.component;

import br.com.mutant.checker.domain.vo.Position;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DiagonalTopToLeftPositionMapper implements PositionMapper {
    @Override
    public List<List<Position>> getPositions(char[][] matrix, int detectionNumber) {
        List<List<Position>> root = new ArrayList<>();
        for (int p0 = matrix.length - 1; p0 > 0; p0--) {
            List<Position> positions = new ArrayList<>();
            for (int p1 = 0; p1 < matrix.length; p1++) {
                int n = p0 - p1;
                if (n >= 0) {
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
