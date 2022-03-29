package br.com.mutant.checker.service;

import br.com.mutant.checker.dto.DnaCheckerRequestDto;

public interface MutantCheckerService {

    boolean isMutant(String[] dna);

    void validateRequest(DnaCheckerRequestDto dnaCheckerRequestDto);

    public char[][] convertDnaToMatrix(String[] dna);

}
