package br.com.mutant.checker.controller;

import br.com.mutant.checker.service.MutantCheckerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class MutantCheckerController {

    private MutantCheckerService mutantCheckerService;

    @Autowired
    public MutantCheckerController(MutantCheckerService mutantCheckerService) {
        this.mutantCheckerService = mutantCheckerService;
    }

    @PostMapping("/mutant")
    @ResponseStatus(code = HttpStatus.OK)
    public void isMutant(@RequestBody String[] dna) {
        mutantCheckerService.validateRequest(dna);
    }
}
