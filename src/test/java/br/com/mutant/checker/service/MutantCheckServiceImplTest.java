package br.com.mutant.checker.service;

import br.com.mutant.checker.component.DiagonalBottomToLeftPositionMapper;
import br.com.mutant.checker.component.DiagonalBottomToRightPositionMapper;
import br.com.mutant.checker.component.DiagonalTopToRightPositionMapper;
import br.com.mutant.checker.component.HorizontalPositionMapper;
import br.com.mutant.checker.component.PositionMapper;
import br.com.mutant.checker.component.VerticalPositionMapper;
import br.com.mutant.checker.dto.DnaCheckerRequestDto;
import br.com.mutant.checker.helper.MutantCheckerTestHelper;
import br.com.mutant.checker.repository.CheckResultRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MutantCheckServiceImplTest {

    @InjectMocks
    private MutantCheckServiceImpl mutantCheckService;

    @Mock
    private CheckResultRepository checkResultRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        List<PositionMapper> positionMappers = Arrays.asList(
                new VerticalPositionMapper(),
                new HorizontalPositionMapper(),
                new DiagonalBottomToLeftPositionMapper(),
                new DiagonalBottomToRightPositionMapper(),
                new DiagonalBottomToLeftPositionMapper(),
                new DiagonalTopToRightPositionMapper()
        );

        mutantCheckService = new MutantCheckServiceImpl(checkResultRepository, positionMappers);
    }

    @Test
    void testHumanDna() {
        String[] dna = MutantCheckerTestHelper.getHumanDnaMatrixWithSixPositionsStringArray();
        boolean isMutant = mutantCheckService.isMutant(dna);
        assertFalse(isMutant);
    }

    @Test
    void testHumanDnaWithTableLargerThanUsual() {
        String[] dna = MutantCheckerTestHelper.getHumanDnaMatrixWithTableLargerThanUsual();
        boolean isMutant = mutantCheckService.isMutant(dna);
        assertFalse(isMutant);
    }

    @Test
    void testMutantDnaWithTableLargerThanUsual() {
        String[] dna = MutantCheckerTestHelper.getMutantDnaMatrixWithTableLargerThanUsual();
        boolean isMutant = mutantCheckService.isMutant(dna);
        assertTrue(isMutant);
    }

    @Test
    void testMutantDnaInHorizontalPosition() {
        String[] dna = MutantCheckerTestHelper.getMutantDnaMatrixInHorizontalPosition();
        boolean isMutant = mutantCheckService.isMutant(dna);
        assertTrue(isMutant);
    }

    @Test
    void testMutantDnaInVerticalPosition() {
        String[] dna = MutantCheckerTestHelper.getMutantDnaMatrixInVerticalPosition();
        boolean isMutant = mutantCheckService.isMutant(dna);
        assertTrue(isMutant);
    }

    @Test
    void testMutantDnaInTopToRightPosition() {
        String[] dna = MutantCheckerTestHelper.getMutantDnaMatrixInTopToRightPosition();
        boolean isMutant = mutantCheckService.isMutant(dna);
        assertTrue(isMutant);
    }

    @Test
    void testMutantDnaInTopToLeftPosition() {
        String[] dna = MutantCheckerTestHelper.getMutantDnaMatrixInTopToLeftPosition();
        boolean isMutant = mutantCheckService.isMutant(dna);
        assertTrue(isMutant);
    }

    @Test
    void testMutantDnaInBottomToLeftPosition() {
        String[] dna = MutantCheckerTestHelper.getMutantDnaMatrixInBottomToLeftPosition();
        boolean isMutant = mutantCheckService.isMutant(dna);
        assertTrue(isMutant);
    }

    @Test
    void testMutantDnaInBottomToRightPosition() {
        String[] dna = MutantCheckerTestHelper.getMutantDnaMatrixInBottomToRightPosition();
        boolean isMutant = mutantCheckService.isMutant(dna);
        assertTrue(isMutant);
    }

    @Test
    void testRequestWithInvalidTable() {
        String[] dna = MutantCheckerTestHelper.getInvalidTable();
        DnaCheckerRequestDto dnaCheckerRequestDto = new DnaCheckerRequestDto();
        dnaCheckerRequestDto.setDna(dna);
        ResponseStatusException responseStatusException = Assertions.assertThrows(ResponseStatusException.class, () -> mutantCheckService.validateRequest(dnaCheckerRequestDto));
        assertEquals("Input table with invalid format", responseStatusException.getReason());
        assertEquals(HttpStatus.BAD_REQUEST, responseStatusException.getStatus());
    }

    @Test
    void testRequestDnaWithInvalidChar() {
        String[] dna = MutantCheckerTestHelper.getDnaWithInvalidChar();
        DnaCheckerRequestDto dnaCheckerRequestDto = new DnaCheckerRequestDto();
        dnaCheckerRequestDto.setDna(dna);
        ResponseStatusException responseStatusException = Assertions.assertThrows(ResponseStatusException.class, () -> mutantCheckService.validateRequest(dnaCheckerRequestDto));
        assertEquals("Invalid DNA", responseStatusException.getReason());
        assertEquals(HttpStatus.BAD_REQUEST, responseStatusException.getStatus());
    }

    @Test
    void testSaveResult() {
        mutantCheckService.saveResult(true, MutantCheckerTestHelper.getMutantDnaMatrixInVerticalPosition());
        verify(checkResultRepository, times(1)).save(any());
    }
}
