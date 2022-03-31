package br.com.mutant.checker.service;

import br.com.mutant.checker.component.PositionMapper;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class MutantCheckerServiceImplTest {

    @InjectMocks
    private MutantCheckerServiceImpl mutantCheckerService;

    @Mock
    private CheckResultRepository checkResultRepository;

    @Autowired
    private List<PositionMapper> positionMappers;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        mutantCheckerService = new MutantCheckerServiceImpl(checkResultRepository, positionMappers);
    }

    @Test
    void testHumanDna() {
        String[] dna = MutantCheckerTestHelper.getHumanDnaMatrixWithSixPositionsStringArray();
        boolean isMutant = mutantCheckerService.isMutant(dna);
        assertFalse(isMutant);
    }

    @Test
    void testHumanDnaWithTableLargerThanUsual() {
        String[] dna = MutantCheckerTestHelper.getHumanDnaMatrixWithTableLargerThanUsual();
        boolean isMutant = mutantCheckerService.isMutant(dna);
        assertFalse(isMutant);
    }

    @Test
    void testMutantDnaWithTableLargerThanUsual() {
        String[] dna = MutantCheckerTestHelper.getMutantDnaMatrixWithTableLargerThanUsual();
        boolean isMutant = mutantCheckerService.isMutant(dna);
        assertTrue(isMutant);
    }

    @Test
    void testMutantDnaInHorizontalPosition() {
        String[] dna = MutantCheckerTestHelper.getMutantDnaMatrixInHorizontalPosition();
        boolean isMutant = mutantCheckerService.isMutant(dna);
        assertTrue(isMutant);
    }

    @Test
    void testMutantDnaInVerticalPosition() {
        String[] dna = MutantCheckerTestHelper.getMutantDnaMatrixInVerticalPosition();
        boolean isMutant = mutantCheckerService.isMutant(dna);
        assertTrue(isMutant);
    }

    @Test
    void testMutantDnaInTopToRightPosition() {
        String[] dna = MutantCheckerTestHelper.getMutantDnaMatrixInTopToRightPosition();
        boolean isMutant = mutantCheckerService.isMutant(dna);
        assertTrue(isMutant);
    }

    @Test
    void testMutantDnaInTopToLeftPosition() {
        String[] dna = MutantCheckerTestHelper.getMutantDnaMatrixInTopToLeftPosition();
        boolean isMutant = mutantCheckerService.isMutant(dna);
        assertTrue(isMutant);
    }

    @Test
    void testMutantDnaInBottomToLeftPosition() {
        String[] dna = MutantCheckerTestHelper.getMutantDnaMatrixInBottomToLeftPosition();
        boolean isMutant = mutantCheckerService.isMutant(dna);
        assertTrue(isMutant);
    }

    @Test
    void testMutantDnaInBottomToRightPosition() {
        String[] dna = MutantCheckerTestHelper.getMutantDnaMatrixInBottomToRightPosition();
        boolean isMutant = mutantCheckerService.isMutant(dna);
        assertTrue(isMutant);
    }

    @Test
    void testRequestWithInvalidTable() {
        String[] dna = MutantCheckerTestHelper.getInvalidTable();
        DnaCheckerRequestDto dnaCheckerRequestDto = new DnaCheckerRequestDto();
        dnaCheckerRequestDto.setDna(dna);
        ResponseStatusException responseStatusException = Assertions.assertThrows(ResponseStatusException.class, () -> mutantCheckerService.validateRequest(dnaCheckerRequestDto));
        assertEquals("Input table with invalid format", responseStatusException.getReason());
        assertEquals(HttpStatus.BAD_REQUEST, responseStatusException.getStatus());
    }

    @Test
    void testRequestDnaWithInvalidChar() {
        String[] dna = MutantCheckerTestHelper.getDnaWithInvalidChar();
        DnaCheckerRequestDto dnaCheckerRequestDto = new DnaCheckerRequestDto();
        dnaCheckerRequestDto.setDna(dna);
        ResponseStatusException responseStatusException = Assertions.assertThrows(ResponseStatusException.class, () -> mutantCheckerService.validateRequest(dnaCheckerRequestDto));
        assertEquals("Invalid DNA", responseStatusException.getReason());
        assertEquals(HttpStatus.BAD_REQUEST, responseStatusException.getStatus());
    }
}
