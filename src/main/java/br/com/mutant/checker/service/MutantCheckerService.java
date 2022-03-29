package br.com.mutant.checker.service;

public interface MutantCheckerService {

    boolean isMutant(String[] dna);

    void validateRequest(String[] dna);

}
