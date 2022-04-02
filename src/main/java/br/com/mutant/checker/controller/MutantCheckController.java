package br.com.mutant.checker.controller;

import br.com.mutant.checker.dto.DnaCheckerRequestDto;
import br.com.mutant.checker.service.MutantCheckService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;

@Controller
public class MutantCheckController {

    private final Logger logger = LoggerFactory.getLogger(MutantCheckController.class);

    private final MutantCheckService mutantCheckService;

    @Autowired
    public MutantCheckController(MutantCheckService mutantCheckService) {
        this.mutantCheckService = mutantCheckService;
    }

    @ApiOperation(value = "Based on DNA, checks if it is mutant")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "It's mutant"),
            @ApiResponse(code = 403, message = "It's not mutant")
    })
    @PostMapping("/mutant")
    public ResponseEntity<Void> isMutant(@RequestBody DnaCheckerRequestDto dnaCheckerRequestDto) {
        mutantCheckService.validateRequest(dnaCheckerRequestDto);

        boolean result = mutantCheckService.isMutant(dnaCheckerRequestDto.getDna());
        mutantCheckService.saveResult(result, dnaCheckerRequestDto.getDna());

        String stringDna = Arrays.toString(dnaCheckerRequestDto.getDna());

        if (result) {
            logger.info("Mutant detected for stringDna {}", stringDna);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        logger.info("Human detected for stringDna {}", stringDna);
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
