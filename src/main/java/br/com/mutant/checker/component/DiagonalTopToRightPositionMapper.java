package br.com.mutant.checker.component;

import br.com.mutant.checker.domain.vo.Position;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DiagonalTopToRightPositionMapper implements PositionMapper {
    @Override
    public List<List<Position>> getPositions(char[][] matrix) {
        List<List<Position>> root = new ArrayList<>();
        for (int p0 = 0; p0 < matrix.length; p0++) {
            List<Position> positions = new ArrayList<>();
            for (int p1 = 0; p1 < matrix.length; p1++) {
                int n = p1 + p0;
                if (n < matrix.length) {
                    positions.add(new Position(p1, n));
                }
            }
            if (positions.size() >= DETECTION_NUMBER) {
                root.add(positions);
            }
        }
        return root;
    }
}
