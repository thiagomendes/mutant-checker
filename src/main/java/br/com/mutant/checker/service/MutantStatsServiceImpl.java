package br.com.mutant.checker.service;

import br.com.mutant.checker.domain.entity.CheckResult;
import br.com.mutant.checker.domain.entity.Kind;
import br.com.mutant.checker.dto.DnaCheckStatsResponseDto;
import br.com.mutant.checker.repository.CheckResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MutantStatsServiceImpl implements MutantStatsService {

    private final CheckResultRepository checkResultRepository;

    @Autowired
    public MutantStatsServiceImpl(CheckResultRepository checkResultRepository) {
        this.checkResultRepository = checkResultRepository;
    }

    @Override
    public DnaCheckStatsResponseDto getStats() {
        Map<Kind, Long> collect = checkResultRepository
                .findAll()
                .stream()
                .map(CheckResult::getKind)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        DnaCheckStatsResponseDto dnaCheckStatsResponseDto = new DnaCheckStatsResponseDto();
        dnaCheckStatsResponseDto.setCountHumanDna(collect.containsKey(Kind.HUMAN) ? collect.get(Kind.HUMAN) : 0);
        dnaCheckStatsResponseDto.setCountMutantDna(collect.containsKey(Kind.MUTANT) ? collect.get(Kind.MUTANT) : 0);

        if (dnaCheckStatsResponseDto.getCountMutantDna() == 0 || dnaCheckStatsResponseDto.getCountHumanDna() == 0) {
            dnaCheckStatsResponseDto.setRatio(0);
        } else {
            float ratio = (float) dnaCheckStatsResponseDto.getCountMutantDna() / (float) dnaCheckStatsResponseDto.getCountHumanDna();
            dnaCheckStatsResponseDto.setRatio(ratio);
        }

        return dnaCheckStatsResponseDto;
    }
}
