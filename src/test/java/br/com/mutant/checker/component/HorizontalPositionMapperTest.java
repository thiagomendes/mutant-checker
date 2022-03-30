package br.com.mutant.checker.component;

import br.com.mutant.checker.domain.vo.Position;
import br.com.mutant.checker.helper.MutantCheckerTestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class HorizontalPositionMapperTest {

    private HorizontalPositionMapper positionMapper;

    @BeforeEach
    public void init() {
        positionMapper = new HorizontalPositionMapper();
    }

    @Test
    void testHorizontalPositionMapperConverterToList() {
        char[][] humanDnaMatrix = MutantCheckerTestHelper.getHumanDnaMatrixWithSixPositionsCharArray();
        List<List<Position>> positions = positionMapper.getPositions(humanDnaMatrix, 4);

        Assertions.assertEquals(6, positions.size());

        positions.forEach(i -> Assertions.assertEquals(6, i.size()));

        Assertions.assertEquals(0, positions.get(0).get(0).getLine());
        Assertions.assertEquals(0, positions.get(0).get(0).getColumn());
        Assertions.assertEquals(5, positions.get(5).get(5).getLine());
        Assertions.assertEquals(5, positions.get(5).get(5).getColumn());
    }
}
