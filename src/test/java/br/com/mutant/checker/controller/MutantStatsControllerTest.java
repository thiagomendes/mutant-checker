package br.com.mutant.checker.controller;

import br.com.mutant.checker.helper.MutantStatsTestHelper;
import br.com.mutant.checker.service.MutantStatsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class MutantStatsControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private MutantStatsController mutantStatsController;

    @Mock
    private MutantStatsService mutantStatsService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        mutantStatsController = new MutantStatsController(mutantStatsService);
        mockMvc = MockMvcBuilders.standaloneSetup(mutantStatsController).build();
    }

    @Test
    void testGetStats() throws Exception {

        Mockito.when(mutantStatsService.getStats()).thenReturn(MutantStatsTestHelper.getDnaCheckStatsResponseDto());

        String responseJson = "{\"ratio\":0.5,\"count_mutant_dna\":5,\"count_human_dna\":10}";

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/stats")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(responseJson));
    }
}
