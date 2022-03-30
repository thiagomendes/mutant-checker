package br.com.mutant.checker.service;

import br.com.mutant.checker.dto.DnaCheckerRequestDto;
import br.com.mutant.checker.helper.MutantCheckerTestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@SpringBootTest
class MutantCheckerServiceImplTest {

    @Autowired
    private MutantCheckerService mutantCheckerService;

    @Test
    void testHumanDna() {
        String[] dna = MutantCheckerTestHelper.getHumanDnaMatrixWithSixPositionsStringArray();
        boolean isMutant = mutantCheckerService.isMutant(dna);
        Assertions.assertFalse(isMutant);
    }

    @Test
    void testMutantDnaInHorizontalPosition() {
        String[] dna = MutantCheckerTestHelper.getMutantDnaMatrixInHorizontalPosition();
        boolean isMutant = mutantCheckerService.isMutant(dna);
        Assertions.assertTrue(isMutant);
    }

    @Test
    void testMutantDnaInVerticalPosition() {
        String[] dna = MutantCheckerTestHelper.getMutantDnaMatrixInVerticalPosition();
        boolean isMutant = mutantCheckerService.isMutant(dna);
        Assertions.assertTrue(isMutant);
    }

    @Test
    void testMutantDnaInTopToRightPosition() {
        String[] dna = MutantCheckerTestHelper.getMutantDnaMatrixInTopToRightPosition();
        boolean isMutant = mutantCheckerService.isMutant(dna);
        Assertions.assertTrue(isMutant);
    }

    @Test
    void testMutantDnaInTopToLeftPosition() {
        String[] dna = MutantCheckerTestHelper.getMutantDnaMatrixInTopToLeftPosition();
        boolean isMutant = mutantCheckerService.isMutant(dna);
        Assertions.assertTrue(isMutant);
    }

    @Test
    void testMutantDnaInBottomToLeftPosition() {
        String[] dna = MutantCheckerTestHelper.getMutantDnaMatrixInBottomToLeftPosition();
        boolean isMutant = mutantCheckerService.isMutant(dna);
        Assertions.assertTrue(isMutant);
    }

    @Test
    void testMutantDnaInBottomToRightPosition() {
        String[] dna = MutantCheckerTestHelper.getMutantDnaMatrixInBottomToRightPosition();
        boolean isMutant = mutantCheckerService.isMutant(dna);
        Assertions.assertTrue(isMutant);
    }

    @Test
    void testRequestWithInvalidTable() {
        String[] dna = MutantCheckerTestHelper.getInvalidTable();
        DnaCheckerRequestDto dnaCheckerRequestDto = new DnaCheckerRequestDto();
        dnaCheckerRequestDto.setDna(dna);
        ResponseStatusException responseStatusException = Assertions.assertThrows(ResponseStatusException.class, () -> mutantCheckerService.validateRequest(dnaCheckerRequestDto));
        Assertions.assertEquals("Input table with invalid format", responseStatusException.getReason());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseStatusException.getStatus());
    }

    @Test
    void testRequestDnaWithInvalidChar() {
        String[] dna = MutantCheckerTestHelper.getDnaWithInvalidChar();
        DnaCheckerRequestDto dnaCheckerRequestDto = new DnaCheckerRequestDto();
        dnaCheckerRequestDto.setDna(dna);
        ResponseStatusException responseStatusException = Assertions.assertThrows(ResponseStatusException.class, () -> mutantCheckerService.validateRequest(dnaCheckerRequestDto));
        Assertions.assertEquals("Invalid DNA", responseStatusException.getReason());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseStatusException.getStatus());
    }
}
