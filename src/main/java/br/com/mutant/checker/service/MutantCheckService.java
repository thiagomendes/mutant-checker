package br.com.mutant.checker.service;

import br.com.mutant.checker.dto.DnaCheckerRequestDto;

public interface MutantCheckService {

    boolean isMutant(String[] dna);

    void validateRequest(DnaCheckerRequestDto dnaCheckerRequestDto);

    void saveResult(boolean result, String[] dna);
}
