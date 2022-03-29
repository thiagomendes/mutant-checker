package br.com.mutant.checker.controller;

import br.com.mutant.checker.dto.DnaCheckerRequestDto;
import br.com.mutant.checker.service.MutantCheckerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MutantCheckerController {

    private final MutantCheckerService mutantCheckerService;

    @Autowired
    public MutantCheckerController(MutantCheckerService mutantCheckerService) {
        this.mutantCheckerService = mutantCheckerService;
    }

    @PostMapping("/mutant")
    public ResponseEntity<Void> isMutant(@RequestBody DnaCheckerRequestDto dnaCheckerRequestDto) {
        mutantCheckerService.validateRequest(dnaCheckerRequestDto);

        if (mutantCheckerService.isMutant(dnaCheckerRequestDto.getDna())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
