package br.com.mutant.checker.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.regex.Pattern;

@Service
public class MutantCheckerServiceImpl implements MutantCheckerService {

    private static final String VALIDATE_REQUEST_PATTERN = "[ATCG]+";

    private static final String INVALID_DNA_ERRO_MESSAGE = "Invalid DNA";

    @Override
    public void validateRequest(String[] dna) {
        Pattern pattern = Pattern.compile(VALIDATE_REQUEST_PATTERN);
        Arrays.asList(dna).forEach(i -> {
            if (!pattern.matcher(i).matches()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, INVALID_DNA_ERRO_MESSAGE);
            }
        });
    }

    @Override
    public boolean isMutant(String[] dna) {
        return false;
    }
}
