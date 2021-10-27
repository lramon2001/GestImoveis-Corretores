package br.com.gestimoveis.gprojetos.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import java.time.LocalDate;

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

import br.com.gestimoveis.gprojetos.model.Cliente;
import br.com.gestimoveis.gprojetos.model.Endereço;
import br.com.gestimoveis.gprojetos.model.EstadoCivil;
import br.com.gestimoveis.gprojetos.model.Genero;
import br.com.gestimoveis.gprojetos.model.StatusCliente;
import br.com.gestimoveis.gprojetos.model.UF;
import br.com.gestimoveis.gprojetos.repositorios.ClienteRepositorio;
import br.com.gestimoveis.gprojetos.servicos.CorretorServico;

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
    	/*Cliente cliente = new Cliente();
    	cliente.setId(1L);
        cliente.setNome("Joaquim");
        cliente.setCpf("434.463.340-76");
        cliente.setRg("179031235");
        cliente.setEstadoCivil(EstadoCivil.CASADO);
        cliente.setDataNascimento(LocalDate.of(1991, 5, 30));
        cliente.setDependentes(4);
        cliente.setEmail("testando@gest.com");
        cliente.setFgts(1000.0);
        cliente.setGenero(Genero.MASCULINO);
        cliente.setObservacoes("nenhuma");
        cliente.setRenda(10000.0);
        cliente.setStatus(StatusCliente.AGUARDANDO_CONTATO);
        cliente.setTelefone("(61) 98799-8754");
        Endereço endereço = new Endereço();
        endereço.setBairro("Bairro teste");
        endereço.setCep("77022-970");
        endereço.setCidade("Palmas");
        endereço.setComplemento("nenhum");
        endereço.setLogradouro("Avenida LO");
        endereço.setNumero("10");
        endereço.setUf(UF.TO);
        cliente.setEndereco(endereço);
    	clienteRepositorio.save(cliente);*/
    }

    @Test
    public void testHomeAccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
        		.get("/clientes")
        		.with(user("esquadrao@gestimoveis.com")
        		.password("pele")))
        		.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testInativosAccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
        		.get("/clientes/inativos")
        		.with(user("esquadrao@gestimoveis.com")
        		.password("pele")))
        		.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAguardandoContatoAccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
        		.get("/clientes/aguardandocontato")
        		.with(user("esquadrao@gestimoveis.com")
        		.password("pele")))
        		.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testSemSimulacaoAccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
        		.get("/clientes/semsimulacao")
        		.with(user("esquadrao@gestimoveis.com")
        		.password("pele")))
        		.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testemSimulacaoAccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
        		.get("/clientes/emsimulacao")
        		.with(user("esquadrao@gestimoveis.com")
        		.password("pele")))
        		.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testSimulacaoprontaAccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
        		.get("/clientes/simulacaopronta")
        		.with(user("esquadrao@gestimoveis.com")
        		.password("pele")))
        		.andExpect(MockMvcResultMatchers.status().isOk());
    }
    //--------------------------------------------------
    @Test
    public void testNegadoAccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
        		.get("/clientes/negado")
        		.with(user("esquadrao@gestimoveis.com")
        		.password("pele")))
        		.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testEmAprovacaoAccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
        		.get("/clientes/emaprovacao")
        		.with(user("esquadrao@gestimoveis.com")
        		.password("pele")))
        		.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAprovadoAccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
        		.get("/clientes/aprovado")
        		.with(user("esquadrao@gestimoveis.com")
        		.password("pele")))
        		.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testVendaConcluidaAccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
        		.get("/clientes/vendaconcluida")
        		.with(user("esquadrao@gestimoveis.com")
        		.password("pele")))
        		.andExpect(MockMvcResultMatchers.status().isOk());
    }
/*
    @Test
    public void testCadastrarAccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
        		.get("/clientes/cadastrar")
        		.with(user("esquadrao@gestimoveis.com")
        		.password("pele")))
        		.andExpect(MockMvcResultMatchers.status().isOk());
    }
 */
    /*
    @Test
    public void testCadastrarrAccess() throws Exception{
    	Cliente cliente = new Cliente();
        cliente.setNome("Joaquim");
        cliente.setCpf("434.463.340-76");
        cliente.setRg("179031235");
        cliente.setEstadoCivil(EstadoCivil.CASADO);
        cliente.setDataNascimento(LocalDate.of(1991, 5, 30));
        cliente.setDependentes(4);
        cliente.setEmail("testando@gest.com");
        cliente.setFgts(1000.0);
        cliente.setGenero(Genero.MASCULINO);
        cliente.setObservacoes("nenhuma");
        cliente.setRenda(10000.0);
        cliente.setStatus(StatusCliente.AGUARDANDO_CONTATO);
        cliente.setTelefone("(61) 98799-8754");
        Endereço endereço = new Endereço();
        endereço.setBairro("Bairro teste");
        endereço.setCep("77022-970");
        endereço.setCidade("Palmas");
        endereço.setComplemento("nenhum");
        endereço.setLogradouro("Avenida LO");
        endereço.setNumero("10");
        endereço.setUf(UF.TO);
        cliente.setEndereco(endereço);
        mockMvc.perform(MockMvcRequestBuilders
        		.post("/clientes/cadastrar")
        		.with(user("esquadrao@gestimoveis.com")
        		.password("pele")))
        		.andExpect(MockMvcResultMatchers.status().isOk());
    }


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
