package br.com.gestimoveis.gprojetos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.gestimoveis.gprojetos.model.Cliente;
import br.com.gestimoveis.gprojetos.model.EstadoCivil;
import br.com.gestimoveis.gprojetos.model.Genero;
import br.com.gestimoveis.gprojetos.model.StatusCliente;
import br.com.gestimoveis.gprojetos.model.UF;
import br.com.gestimoveis.gprojetos.repositorios.ClienteRepositorio;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

@Autowired
private ClienteRepositorio clienteRepositorio;

@GetMapping
public ModelAndView home(){
    ModelAndView modelAndView = new ModelAndView("cliente/home");

    modelAndView.addObject("clientes", clienteRepositorio.findAll());
    modelAndView.addObject("statuses", StatusCliente.values());

    return modelAndView;

}

@PostMapping("**/pesquisarcliente")
public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa){
    ModelAndView modelAndView = new ModelAndView("cliente/home");
    modelAndView.addObject("clientes", clienteRepositorio.findClienteByName(nomepesquisa));
    modelAndView.addObject("statuses", StatusCliente.values());
    return modelAndView;
}


@GetMapping("/inativos")
public ModelAndView inativos(){
    ModelAndView modelAndView = new ModelAndView("cliente/inativos");
    modelAndView.addObject("clientes", clienteRepositorio.buscarinativos(StatusCliente.AGUARDANDO_CONTATO,StatusCliente.SEM_SIMULACAO,StatusCliente.NEGADO));
    modelAndView.addObject("verificar", true);
    return modelAndView;

} 
@GetMapping("/aguardandocontato")
public ModelAndView aguardandocontato(){
    ModelAndView modelAndView = new ModelAndView("cliente/filtro");
    modelAndView.addObject("clientes", clienteRepositorio.buscaporstatus(StatusCliente.AGUARDANDO_CONTATO));
    modelAndView.addObject("verificar", true);
    return modelAndView;

} 
@GetMapping("/semsimulacao")
public ModelAndView semsimulacao(){
    ModelAndView modelAndView = new ModelAndView("cliente/filtro");
    modelAndView.addObject("clientes", clienteRepositorio.buscaporstatus(StatusCliente.SEM_SIMULACAO));
    modelAndView.addObject("verificar", true);
    return modelAndView;

} 
@GetMapping("/emsimulacao")
public ModelAndView emsimulacao(){
    ModelAndView modelAndView = new ModelAndView("cliente/filtro");
    modelAndView.addObject("clientes", clienteRepositorio.buscaporstatus(StatusCliente.EM_SIMULACAO));
    modelAndView.addObject("verificar", true);
    return modelAndView;
} 

@GetMapping("/simulacaopronta")
public ModelAndView simulacaopronta(){
    ModelAndView modelAndView = new ModelAndView("cliente/filtro");
    modelAndView.addObject("clientes", clienteRepositorio.buscaporstatus(StatusCliente.SIMULACAO_PRONTA));
    modelAndView.addObject("verificar", true);
    return modelAndView;

} 
@GetMapping("/negado")
public ModelAndView negado(){
    ModelAndView modelAndView = new ModelAndView("cliente/filtro");
    modelAndView.addObject("clientes", clienteRepositorio.buscaporstatus(StatusCliente.NEGADO));
    modelAndView.addObject("verificar", true);
    return modelAndView;

} 
@GetMapping("/emaprovacao")
public ModelAndView emaprovacao(){
    ModelAndView modelAndView = new ModelAndView("cliente/filtro");
    modelAndView.addObject("clientes", clienteRepositorio.buscaporstatus(StatusCliente.EM_APROVACAO));
    modelAndView.addObject("verificar", true);
    return modelAndView;

} 
@GetMapping("/aprovado")
public ModelAndView aprovado(){
    ModelAndView modelAndView = new ModelAndView("cliente/filtro");
    modelAndView.addObject("clientes", clienteRepositorio.buscaporstatus(StatusCliente.APROVADO));
    modelAndView.addObject("verificar", true);
    return modelAndView;

} 
@GetMapping("/vendaconcluida")
public ModelAndView vendaconcluida(){
    ModelAndView modelAndView = new ModelAndView("cliente/filtro");
    modelAndView.addObject("clientes", clienteRepositorio.buscaporstatus(StatusCliente.VENDA_CONCLUIDA));
    modelAndView.addObject("verificar", true);
    return modelAndView;

} 
@GetMapping("/{id}")
public ModelAndView detalhes(@PathVariable Long id){
    ModelAndView modelAndView = new ModelAndView("cliente/detalhes");  
    modelAndView.addObject("cliente", clienteRepositorio.findById(id).get());

    return modelAndView;
}
@GetMapping("/cadastrar")
public ModelAndView cadastrar(){
    ModelAndView modelAndView= new ModelAndView ("cliente/formulario");
    

    modelAndView.addObject("cliente", new Cliente());
    modelAndView.addObject("ufs", UF.values());
    modelAndView.addObject("statuses", StatusCliente.values());
    modelAndView.addObject("generos", Genero.values());
    modelAndView.addObject("estadosCivis", EstadoCivil.values());

    return modelAndView;
}

@GetMapping("/{id}/editar")
public ModelAndView editar(@PathVariable Long id){
    ModelAndView modelAndView= new ModelAndView("cliente/formulario");

    modelAndView.addObject("cliente", clienteRepositorio.findById(id).get());
    modelAndView.addObject("ufs", UF.values());
    modelAndView.addObject("statuses", StatusCliente.values());
    modelAndView.addObject("generos", Genero.values());
    modelAndView.addObject("estadosCivis", EstadoCivil.values());
   
    return modelAndView;

}
@PostMapping({"/cadastrar","/{id}/editar"})
public String salvar(@Valid Cliente cliente, BindingResult resultado,ModelMap model){
    if(resultado.hasErrors()){
    model.addAttribute("ufs", UF.values());
    model.addAttribute("statuses", StatusCliente.values());
    model.addAttribute("generos", Genero.values());
    model.addAttribute("estadosCivis", EstadoCivil.values());
    return "cliente/formulario";
    }
    clienteRepositorio.save(cliente);

    return "redirect:/clientes";
}



@GetMapping("/{id}/excluir")
public String excluir (@PathVariable Long id){
    clienteRepositorio.deleteById(id);

    return "redirect:/clientes";
}


}
