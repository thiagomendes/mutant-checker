package br.com.mutant.checker.controller;

import br.com.mutant.checker.dto.DnaCheckStatsResponseDto;
import br.com.mutant.checker.service.MutantStatsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MutantStatsController {

    private final MutantStatsService mutantStatsService;

    @Autowired
    public MutantStatsController(MutantStatsService mutantStatsService) {
        this.mutantStatsService = mutantStatsService;
    }

    @ApiOperation(value = "Get the DNA check statistics")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get stats successfully")
    })
    @GetMapping("/stats")
    public ResponseEntity<DnaCheckStatsResponseDto> stats() {
        return new ResponseEntity<>(mutantStatsService.getStats(), HttpStatus.OK);
    }
}
