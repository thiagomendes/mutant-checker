package br.com.mutant.checker.component;

import br.com.mutant.checker.domain.vo.Position;
import br.com.mutant.checker.helper.MutantCheckerTestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiagonalTopToLeftPositionMapperTest {

    private DiagonalTopToLeftPositionMapper positionMapper;

    @BeforeEach
    public void init() {
        positionMapper = new DiagonalTopToLeftPositionMapper();
    }

    @Test
    void testHorizontalPositionMapperConverterToList() {
        char[][] humanDnaMatrix = MutantCheckerTestHelper.getHumanDnaMatrixWithSixPositionsCharArray();
        List<List<Position>> positions = positionMapper.getPositions(humanDnaMatrix, 4);

        assertEquals(3, positions.size());

        assertEquals(0, positions.get(0).get(0).getLine());
        assertEquals(5, positions.get(0).get(0).getColumn());
        assertEquals(3, positions.get(2).get(3).getLine());
        assertEquals(0, positions.get(2).get(3).getColumn());
    }
}
