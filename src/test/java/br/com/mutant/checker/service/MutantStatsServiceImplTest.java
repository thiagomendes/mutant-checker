package br.com.mutant.checker.service;

import br.com.mutant.checker.dto.DnaCheckStatsResponseDto;
import br.com.mutant.checker.helper.MutantStatsTestHelper;
import br.com.mutant.checker.repository.CheckResultRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.DecimalFormat;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class MutantStatsServiceImplTest {

    @InjectMocks
    private MutantStatsServiceImpl mutantStatsService;

    @Mock
    private CheckResultRepository checkResultRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        mutantStatsService = new MutantStatsServiceImpl(checkResultRepository);
    }

    @Test
    void testStatsCountsAndRatio() {
        Mockito.when(checkResultRepository.findAll()).thenReturn(MutantStatsTestHelper.getCheckResultsForStats());
        DnaCheckStatsResponseDto stats = mutantStatsService.getStats();
        Assertions.assertEquals(10, stats.getCountHumanDna());
        Assertions.assertEquals(4, stats.getCountMutantDna());
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        Assertions.assertEquals("0.4", decimalFormat.format(stats.getRatio()));
    }

    @Test
    void testStatsWithZeroRecordsOnDatabase() {
        Mockito.when(checkResultRepository.findAll()).thenReturn(List.of());
        DnaCheckStatsResponseDto stats = mutantStatsService.getStats();
        Assertions.assertEquals(0, stats.getCountHumanDna());
        Assertions.assertEquals(0, stats.getCountMutantDna());
        Assertions.assertEquals(0.0, stats.getRatio());
    }
}
