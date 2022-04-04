package br.com.challenge.controlefinanceiro.controller;

import br.com.challenge.controlefinanceiro.model.Despesa;
import br.com.challenge.controlefinanceiro.repository.DespesaRepository;
import br.com.challenge.controlefinanceiro.service.DespesaService;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DespesaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private MockRestServiceServer mockServer;

    @InjectMocks
    private DespesaController despesaController;

    @MockBean
    private DespesaService despesaService;
    @MockBean
    private DespesaRepository despesaRepository;

    @Before
    public void before(){
        mockMvc = MockMvcBuilders.standaloneSetup(despesaController).build();
        MockitoAnnotations.initMocks(this);
        FixtureFactoryLoader.loadTemplates("br.com.challenge.controlefinanceiro.template");
    }

    @Test
    public void deveTestarRequisicaoPostDaDespesa(){
        Despesa despesa = Fixture.from(Despesa.class).gimme("model");

        Mockito.when(this.despesaService.save(any())).thenReturn(despesa);

        mockMvc.perform(post("/despesa").contentType(MediaType.APPLICATION_JSON).
    }
    }


}
