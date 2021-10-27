package br.com.gestimoveis.gprojetos.controller;

import br.com.gestimoveis.gprojetos.repositorios.ClienteRepositorio;
import br.com.gestimoveis.gprojetos.repositorios.CorretorRepositorio;
import br.com.gestimoveis.gprojetos.repositorios.EmpreendimentoRepositorio;
import br.com.gestimoveis.gprojetos.servicos.CorretorServico;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@ContextConfiguration
@WebAppConfiguration
@WebMvcTest(controllers = EmpreendimentoController.class)
public class EmpreendimentoControllerTest {
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CorretorServico corretorServico;

    @MockBean
    CorretorRepositorio corretorRepositorio;

    @MockBean
    EmpreendimentoRepositorio empreendimentoRepositorio;

    @MockBean
    ClienteRepositorio clienteRepositorio;


    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void testHomeAccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/projetos")
                        .with(user("esquadrao@gestimoveis.com")
                                .password("pele")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testDisponivelAccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/projetos/disponivel")
                        .with(user("esquadrao@gestimoveis.com")
                                .password("pele")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testIndisponivelAccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/projetos/indisponivel")
                        .with(user("esquadrao@gestimoveis.com")
                                .password("pele")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testVendidoAcess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/projetos/vendido")
                        .with(user("esquadrao@gestimoveis.com")
                                .password("pele")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testCadastrarAccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/projetos/cadastrar")
                        .with(user("esquadrao@gestimoveis.com")
                                .password("pele")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
