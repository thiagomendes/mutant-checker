package br.com.mutant.checker.helper;

import br.com.mutant.checker.domain.entity.CheckResult;
import br.com.mutant.checker.domain.entity.Kind;
import br.com.mutant.checker.dto.DnaCheckStatsResponseDto;

import java.util.Arrays;
import java.util.List;

public class MutantStatsTestHelper {

    public static List<CheckResult> getCheckResultsForStats() {
        return Arrays.asList(
                new CheckResult(null, null, Kind.HUMAN, null),
                new CheckResult(null, null, Kind.HUMAN, null),
                new CheckResult(null, null, Kind.HUMAN, null),
                new CheckResult(null, null, Kind.HUMAN, null),
                new CheckResult(null, null, Kind.HUMAN, null),
                new CheckResult(null, null, Kind.HUMAN, null),
                new CheckResult(null, null, Kind.HUMAN, null),
                new CheckResult(null, null, Kind.HUMAN, null),
                new CheckResult(null, null, Kind.HUMAN, null),
                new CheckResult(null, null, Kind.HUMAN, null),
                new CheckResult(null, null, Kind.MUTANT, null),
                new CheckResult(null, null, Kind.MUTANT, null),
                new CheckResult(null, null, Kind.MUTANT, null),
                new CheckResult(null, null, Kind.MUTANT, null)
        );
    }

    public static DnaCheckStatsResponseDto getDnaCheckStatsResponseDto() {
        DnaCheckStatsResponseDto dnaCheckStatsResponseDto = new DnaCheckStatsResponseDto();
        dnaCheckStatsResponseDto.setCountMutantDna(5);
        dnaCheckStatsResponseDto.setCountHumanDna(10);
        dnaCheckStatsResponseDto.setRatio(0.5f);
        return dnaCheckStatsResponseDto;
    }
}
