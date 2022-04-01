package br.com.mutant.checker.controller;

import br.com.mutant.checker.dto.DnaCheckerRequestDto;
import br.com.mutant.checker.service.MutantCheckerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Based on DNA, checks if it is mutant")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "It's mutant"),
            @ApiResponse(code = 403, message = "It's not mutant")
    })
    @PostMapping("/mutant")
    public ResponseEntity<Void> isMutant(@RequestBody DnaCheckerRequestDto dnaCheckerRequestDto) {
        mutantCheckerService.validateRequest(dnaCheckerRequestDto);

        boolean result = mutantCheckerService.isMutant(dnaCheckerRequestDto.getDna());
        mutantCheckerService.saveResult(result, dnaCheckerRequestDto.getDna());

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
