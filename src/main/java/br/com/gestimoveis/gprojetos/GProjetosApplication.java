package br.com.gestimoveis.gprojetos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.gestimoveis.gprojetos.model.Corretor;
import br.com.gestimoveis.gprojetos.servicos.CorretorServico;

@SpringBootApplication
public class GProjetosApplication implements CommandLineRunner{

@Autowired
private CorretorServico corretorServico;

	public static void main(String[] args) {
		SpringApplication.run(GProjetosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(corretorServico.buscarTodos().isEmpty()){
			 Corretor corretor = new Corretor();
			 corretor.setNome("Esquadrão");
			 corretor.setEmail("esquadrao@gestimoveis.com");
			 corretor.setUsuario("Administrador");
			 corretor.setSenha("pele");

			 corretorServico.cadastrar(corretor);
		}
		
		
	}

}
