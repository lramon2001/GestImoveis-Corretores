package br.com.gestimoveis.gprojetos.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.gestimoveis.gprojetos.dao.AlterarSenhaDAO;
import br.com.gestimoveis.gprojetos.model.Corretor;
import br.com.gestimoveis.gprojetos.repositorios.CorretorRepositorio;
import br.com.gestimoveis.gprojetos.utils.SenhaUtils;

@Controller

public class UsuarioController {
   
    @Autowired
    private CorretorRepositorio corretorRepositorio;
    @GetMapping("/login")
    public String login() {
        return "usuario/login";
    }

    @GetMapping("/perfil")
    public ModelAndView perfil(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("usuario/perfil");
        
        Corretor usuario = corretorRepositorio.findByEmail(principal.getName()).get();
        modelAndView.addObject("logado", usuario);
        modelAndView.addObject("alterarSenhaForm", new AlterarSenhaDAO());

        return modelAndView;
    }

    @PostMapping("/alterar-senha")
    public String alterarSenha(AlterarSenhaDAO form, Principal principal){
        Corretor usuario = corretorRepositorio.findByEmail(principal.getName()).get();

        if(SenhaUtils.matches(form.getSenhaAtual(),usuario.getSenha())){
            usuario.setSenha(SenhaUtils.encode(form.getNovaSenha()));

            corretorRepositorio.save(usuario);
        }
       

        return "redirect:/perfil";
         
    }



}
