package br.com.challenge.controlefinanceiro.controller;

import br.com.challenge.controlefinanceiro.dto.ReceitaDTO;
import br.com.challenge.controlefinanceiro.model.Receita;
import br.com.challenge.controlefinanceiro.repository.ReceitaRepository;
import br.com.challenge.controlefinanceiro.service.ReceitaService;
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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReceitaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ReceitaController receitaController;

    @MockBean
    private ReceitaService receitaService;

    @MockBean
    private ReceitaRepository receitaRepository;

    @Before
    public void before(){
        mockMvc = MockMvcBuilders.standaloneSetup(new Object[]{this.receitaController}).build();
        MockitoAnnotations.initMocks(this);
        FixtureFactoryLoader.loadTemplates("br.com.challenge.controlefinanceiro.template");
    }

    @Test
    public void deveTestarRequisicaoPostDaReceita() throws Exception{
        Receita entrada = Fixture.from(Receita.class).gimme("model");
        Receita saida = Fixture.from(Receita.class).gimme("model");

        Mockito.when(this.receitaService.adicionar(any())).thenReturn(saida);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/receita")
                        .contentType(asJsonString(entrada))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void deveTestarRequisicaoGetDaReceita() throws Exception{
        Receita entrada = Fixture.from(Receita.class).gimme("model");
        List<ReceitaDTO> saida = Fixture.from(ReceitaDTO.class).gimme(3, "dto");

        Mockito.when(this.receitaService.findAll()).thenReturn(saida);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/receita")
                        .contentType(asJsonString(entrada))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void deveTestarRequisicaoGetIdDaReceita() throws Exception{
        Receita entrada = Fixture.from(Receita.class).gimme("model");
        Receita saida = Fixture.from(Receita.class).gimme("model");

        String idRequest = entrada.getId().toString();

        Mockito.when(this.receitaService.findId(any())).thenReturn((List<ReceitaDTO>) saida);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/receita/{id}", idRequest)
                        .contentType(asJsonString(entrada))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }


    @Test
    public void deveTestarRequisicaoPutDaReceita() throws Exception{
        Receita entrada = Fixture.from(Receita.class).gimme("model");
        ReceitaDTO saida = Fixture.from(ReceitaDTO.class).gimme("dto");

        String idRequest = entrada.getId().toString();

        Mockito.when(this.receitaService.update(any(), any())).thenReturn(saida);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/receita/{id}", idRequest)
                        .contentType(asJsonString(entrada))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void deveTestarRequisicaoDeleteDaDespesa() throws Exception{
        Receita entrada = Fixture.from(Receita.class).gimme("model");
        ReceitaDTO saida = Fixture.from(ReceitaDTO.class).gimme("dto");

        String idRequest = entrada.getId().toString();

        Mockito.when(this.receitaService.update(any(), any())).thenReturn(saida);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/receita/{id}", idRequest)
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
