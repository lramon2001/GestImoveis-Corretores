package br.com.gestimoveis.gprojetos.controller;

import br.com.gestimoveis.gprojetos.repositorios.ClienteRepositorio;
import br.com.gestimoveis.gprojetos.repositorios.CorretorRepositorio;
import br.com.gestimoveis.gprojetos.repositorios.EmpreendimentoRepositorio;
import br.com.gestimoveis.gprojetos.servicos.CorretorServico;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
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
@WebMvcTest(controllers = CorretorController.class)
public class CorretorControllerTest {
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CorretorServico corretorServico;

    @MockBean
    private ClienteRepositorio clienteRepositorio;

    @MockBean
    private CorretorRepositorio corretorRepositorio;

    @MockBean
    private EmpreendimentoRepositorio empreendimentoRepositorio;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void testHomeAccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/corretores")
                .with(user("esquadrao@gestimoveis.com")
                .password("pele")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testcadastrarAccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/corretores/cadastrar")
                .with(user("esquadrao@gestimoveis.com")
                .password("pele")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testRelatorioAcess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/corretores/relatorio")
                .with(user("esquadrao@gestimoveis.com")
                .password("pele")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}