package br.com.gestimoveis.gprojetos.controller;

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

import br.com.gestimoveis.gprojetos.repositorios.ClienteRepositorio;
import br.com.gestimoveis.gprojetos.servicos.CorretorServico;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@RunWith(SpringRunner.class)
@ContextConfiguration
@WebAppConfiguration
@WebMvcTest(controllers = ClienteController.class)
public class ClienteControllerTest {
    
	private WebApplicationContext context;
	
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CorretorServico corretorServico;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private ClienteRepositorio clienteRepositorio;

    @Before
    public void setup() {
    	mockMvc = MockMvcBuilders
    			.webAppContextSetup(context).
    			apply(springSecurity())
    			.build();
    }

    @Test
    public void testHomeOK() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
        		.get("/clientes")
        		.with(user("esquadrao@gestimoveis.com")
        		.password("pele")))
        		.andExpect(MockMvcResultMatchers.status().isOk());
    }

    /*
    @Test
    public void testSalvar() throws Exception{

        Cliente cliente = new Cliente();

        mockMvc.perform(MockMvcRequestBuilders
        .post("/cadastrar")
        .with(user("esquadrao@gestimoveis.com")
        .password("pele"))
        .content());
    }*/
}
