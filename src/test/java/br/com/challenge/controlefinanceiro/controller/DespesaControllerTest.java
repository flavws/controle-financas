package br.com.challenge.controlefinanceiro.controller;

import br.com.challenge.controlefinanceiro.dto.DespesaDTO;
import br.com.challenge.controlefinanceiro.model.Despesa;
import br.com.challenge.controlefinanceiro.repository.DespesaRepository;
import br.com.challenge.controlefinanceiro.service.DespesaService;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DespesaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private DespesaController despesaController;

    @MockBean
    private DespesaService despesaService;

    @MockBean
    private DespesaRepository despesaRepository;

    @Before
    public void before(){
        mockMvc = MockMvcBuilders.standaloneSetup(new Object[]{this.despesaController}).build();
        MockitoAnnotations.initMocks(this);
        FixtureFactoryLoader.loadTemplates("br.com.challenge.controlefinanceiro.template");
    }

    @Test
    public void deveTestarRequisicaoPostDaDespesa() throws Exception{
        Despesa entrada = Fixture.from(Despesa.class).gimme("model");
        Despesa saida = Fixture.from(Despesa.class).gimme("model");

        Mockito.when(this.despesaService.save(any())).thenReturn(saida);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/despesa")
                        .contentType(asJsonString(entrada))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void deveTestarRequisicaoGetDaDespesa() throws Exception{
        Despesa entrada = Fixture.from(Despesa.class).gimme("model");
        List<DespesaDTO> saida = Fixture.from(DespesaDTO.class).gimme(3, "valid");

        Mockito.when(this.despesaService.findAll()).thenReturn(saida);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/despesa")
                        .contentType(asJsonString(entrada))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void deveTestarRequisicaoGetIdDaDespesa() throws Exception{
        Despesa entrada = Fixture.from(Despesa.class).gimme("model");
        List<DespesaDTO> saida = Fixture.from(DespesaDTO.class).gimme(3, "valid");

        String idRequest = entrada.getId().toString();

        Mockito.when(this.despesaService.findId(any())).thenReturn(saida);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/despesa/{id}", idRequest)
                        .contentType(asJsonString(entrada))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void deveTestarRequisicaoGetDescricaoDaDespesa() throws Exception{
        Despesa entrada = Fixture.from(Despesa.class).gimme("model");
        List<DespesaDTO> saida = Fixture.from(DespesaDTO.class).gimme(3, "valid");

        String descRequest = entrada.getDescricao();

        Mockito.when(this.despesaService.findDescricao(any())).thenReturn(saida);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/despesa//descricao/{descricao}", descRequest)
                        .contentType(asJsonString(entrada))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void deveTestarRequisicaoPutDaDespesa() throws Exception{
        Despesa entrada = Fixture.from(Despesa.class).gimme("model");
        DespesaDTO saida = Fixture.from(DespesaDTO.class).gimme("valid");

        String idRequest = entrada.getId().toString();

        Mockito.when(this.despesaService.update(any(), any())).thenReturn(saida);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/despesa/{id}", idRequest)
                        .contentType(asJsonString(entrada))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void deveTestarRequisicaoDeleteDaDespesa() throws Exception{
        Despesa entrada = Fixture.from(Despesa.class).gimme("model");
        DespesaDTO saida = Fixture.from(DespesaDTO.class).gimme("valid");

        String idRequest = entrada.getId().toString();

        Mockito.when(this.despesaService.update(any(), any())).thenReturn(saida);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/despesa/{id}", idRequest)
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
