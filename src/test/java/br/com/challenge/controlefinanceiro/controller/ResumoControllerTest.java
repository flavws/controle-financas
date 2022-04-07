package br.com.challenge.controlefinanceiro.controller;

import br.com.challenge.controlefinanceiro.dto.ResumoMensalDTO;
import br.com.challenge.controlefinanceiro.model.Despesa;
import br.com.challenge.controlefinanceiro.service.ResumoService;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ResumoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ResumoController resumoController;

    @MockBean
    private ResumoService resumoService;

    @Before
    public void before(){
        mockMvc = MockMvcBuilders.standaloneSetup(new Object[]{this.resumoController}).build();
        MockitoAnnotations.initMocks(this);
        FixtureFactoryLoader.loadTemplates("br.com.challenge.controlefinanceiro.template");
    }

    @Test
    public void deveTestarRequisicaoPostDaReceita() throws Exception{
        ResumoMensalDTO entrada = Fixture.from(ResumoMensalDTO.class).gimme("dto");
        ResumoMensalDTO saida = Fixture.from(ResumoMensalDTO.class).gimme("dto");

        Mockito.when(this.resumoService.resumoMes(any(), any())).thenReturn(saida);

        String ano = "2022";

        String mes = "02";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/resumo/{ano}/{mes}", ano, mes)
                        .contentType(asJsonString(entrada))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    public static String asJsonString(final Object object) {
        try{
            return new ObjectMapper().writeValueAsString(object);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
