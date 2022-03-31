package br.com.mutant.checker.component;

import br.com.mutant.checker.domain.vo.Position;
import br.com.mutant.checker.helper.MutantCheckerTestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VerticalPositionMapperTest {

    private VerticalPositionMapper positionMapper;

    @BeforeEach
    public void init() {
        positionMapper = new VerticalPositionMapper();
    }

    @Test
    void testVerticalPositionMapperConverterToList() {
        char[][] humanDnaMatrix = MutantCheckerTestHelper.getHumanDnaMatrixWithSixPositionsCharArray();
        List<List<Position>> positions = positionMapper.getPositions(humanDnaMatrix, 4);

        assertEquals(6, positions.size());

        positions.forEach(i -> assertEquals(6, i.size()));

        assertEquals(0, positions.get(0).get(0).getLine());
        assertEquals(0, positions.get(0).get(0).getColumn());
        assertEquals(5, positions.get(5).get(5).getLine());
        assertEquals(5, positions.get(5).get(5).getColumn());
    }
}
