package br.com.gestimoveis.gprojetos.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.gestimoveis.gprojetos.model.Corretor;
import br.com.gestimoveis.gprojetos.model.StatusCliente;
import br.com.gestimoveis.gprojetos.model.StatusImovel;
import br.com.gestimoveis.gprojetos.repositorios.ClienteRepositorio;
import br.com.gestimoveis.gprojetos.repositorios.CorretorRepositorio;
import br.com.gestimoveis.gprojetos.repositorios.EmpreendimentoRepositorio;
import br.com.gestimoveis.gprojetos.servicos.CorretorServico;


@Controller
@RequestMapping("/corretores")
public class CorretorController {
    @Autowired
    private CorretorRepositorio corretorRepositorio;

    
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private EmpreendimentoRepositorio empreendimentoRepositorio;

    @Autowired 
    private CorretorServico  corretorServico;

    @GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("corretor/home");

        modelAndView.addObject("corretores", corretorRepositorio.findAll());

        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        List<String> usuarios = new ArrayList<String>();
        usuarios.add("Administrador");
        usuarios.add("Usuário padrão");
        ModelAndView modelAndView = new ModelAndView("corretor/formulario");
        modelAndView.addObject("usuarios", usuarios);
        modelAndView.addObject("corretor", new Corretor());
        
      
        return modelAndView;
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        List<String> usuarios = new ArrayList<String>();
        usuarios.add("Administrador");
        usuarios.add("Usuário padrão");
        
        ModelAndView modelAndView = new ModelAndView("corretor/formulario");
        modelAndView.addObject("usuarios", usuarios);
        modelAndView.addObject("corretor", corretorServico.buscaPorId(id));
      


        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid Corretor corretor, BindingResult resultado) {
        

        corretorServico.cadastrar(corretor);
     

        return "redirect:/corretores";
    }

    @PostMapping("/{id}/editar")
    public String editar(Corretor corretor, @PathVariable Long id){
        
        corretorServico.atualizar(corretor, id);
       

        return "redirect:/corretores";

    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        corretorServico.excluirPorId(id);

        return "redirect:/corretores";
    }

    @GetMapping("/{id}")
    public ModelAndView detalhes (@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("corretor/detalhes");

        modelAndView.addObject("corretor", corretorServico.buscaPorId(id));

        return modelAndView;
    }


   @GetMapping("/relatorio")
    public ModelAndView relatorio(){
        ModelAndView modelAndView = new ModelAndView("corretor/relatorio");

        modelAndView.addObject("aguardando_contato", clienteRepositorio.buscaporstatus(StatusCliente.AGUARDANDO_CONTATO).size());
        modelAndView.addObject("sem_simulacao", clienteRepositorio.buscaporstatus(StatusCliente.SEM_SIMULACAO).size());
        modelAndView.addObject("em_simulacao", clienteRepositorio.buscaporstatus(StatusCliente.EM_SIMULACAO).size());
        modelAndView.addObject("simulacao_pronta", clienteRepositorio.buscaporstatus(StatusCliente.SIMULACAO_PRONTA).size());
        modelAndView.addObject("negado", clienteRepositorio.buscaporstatus(StatusCliente.NEGADO).size());
        modelAndView.addObject("em_aprovacao", clienteRepositorio.buscaporstatus(StatusCliente.EM_APROVACAO).size());
        modelAndView.addObject("aprovado", clienteRepositorio.buscaporstatus(StatusCliente.APROVADO).size());
        modelAndView.addObject("inativos",clienteRepositorio.buscarinativos(StatusCliente.AGUARDANDO_CONTATO, StatusCliente.SEM_SIMULACAO, StatusCliente.NEGADO).size());
        modelAndView.addObject("ativos", clienteRepositorio.findAll().size()-clienteRepositorio.buscarinativos(StatusCliente.AGUARDANDO_CONTATO, StatusCliente.SEM_SIMULACAO, StatusCliente.NEGADO).size());
        modelAndView.addObject("total_clientes", clienteRepositorio.findAll().size());
        modelAndView.addObject("venda_concluida", clienteRepositorio.buscaporstatus(StatusCliente.VENDA_CONCLUIDA).size());

        modelAndView.addObject("disponivel",empreendimentoRepositorio.findByStatus(StatusImovel.DISPONIVEL).size());
        modelAndView.addObject("indisponivel",empreendimentoRepositorio.findByStatus(StatusImovel.INDISPONIVEL).size());
        modelAndView.addObject("vendido", empreendimentoRepositorio.findByStatus(StatusImovel.VENDIDO).size());
        modelAndView.addObject("total_empreendimentos", empreendimentoRepositorio.findAll().size());
        return modelAndView;
    } 



    
}
