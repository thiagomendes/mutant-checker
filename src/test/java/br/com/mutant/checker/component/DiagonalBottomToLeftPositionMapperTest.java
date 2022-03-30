package br.com.mutant.checker.component;

import br.com.mutant.checker.domain.vo.Position;
import br.com.mutant.checker.helper.MutantCheckerTestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class DiagonalBottomToLeftPositionMapperTest {

    private DiagonalBottomToLeftPositionMapper positionMapper;

    @BeforeEach
    public void init() {
        positionMapper = new DiagonalBottomToLeftPositionMapper();
    }

    @Test
    void testHorizontalPositionMapperConverterToList() {
        char[][] humanDnaMatrix = MutantCheckerTestHelper.getHumanDnaMatrixWithSixPositionsCharArray();
        List<List<Position>> positions = positionMapper.getPositions(humanDnaMatrix, 4);

        Assertions.assertEquals(3, positions.size());

        Assertions.assertEquals(5, positions.get(0).get(0).getLine());
        Assertions.assertEquals(5, positions.get(0).get(0).getColumn());
        Assertions.assertEquals(2, positions.get(2).get(3).getLine());
        Assertions.assertEquals(0, positions.get(2).get(3).getColumn());
    }
}
