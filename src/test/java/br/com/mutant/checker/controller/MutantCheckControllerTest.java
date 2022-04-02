package br.com.mutant.checker.controller;

import br.com.mutant.checker.dto.DnaCheckerRequestDto;
import br.com.mutant.checker.helper.MutantCheckerTestHelper;
import br.com.mutant.checker.service.MutantCheckService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
class MutantCheckControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private MutantCheckController mutantCheckController;

    @Mock
    private MutantCheckService mutantCheckService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        mutantCheckController = new MutantCheckController(mutantCheckService);
        mockMvc = MockMvcBuilders.standaloneSetup(mutantCheckController).build();
    }

    @Test
    void testMutantDna() throws Exception {

        DnaCheckerRequestDto dnaCheckerRequestDto = new DnaCheckerRequestDto();
        dnaCheckerRequestDto.setDna(MutantCheckerTestHelper.getMutantDnaMatrixInHorizontalPosition());

        Mockito.when(mutantCheckService.isMutant(dnaCheckerRequestDto.getDna())).thenReturn(true);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/mutant")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsBytes(dnaCheckerRequestDto))
                )
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void testHumanDna() throws Exception {

        DnaCheckerRequestDto dnaCheckerRequestDto = new DnaCheckerRequestDto();
        dnaCheckerRequestDto.setDna(MutantCheckerTestHelper.getMutantDnaMatrixInHorizontalPosition());

        Mockito.when(mutantCheckService.isMutant(dnaCheckerRequestDto.getDna())).thenReturn(false);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/mutant")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsBytes(dnaCheckerRequestDto))
                )
                .andExpect(MockMvcResultMatchers.status().isForbidden());

    }
}
