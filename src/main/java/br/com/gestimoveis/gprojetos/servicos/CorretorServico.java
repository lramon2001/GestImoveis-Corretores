package br.com.gestimoveis.gprojetos.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestimoveis.gprojetos.exceptions.CorretorNaoEncontradoException;
import br.com.gestimoveis.gprojetos.model.Corretor;

import br.com.gestimoveis.gprojetos.repositorios.CorretorRepositorio;
import br.com.gestimoveis.gprojetos.utils.SenhaUtils;

@Service
public class CorretorServico {
    @Autowired
    private CorretorRepositorio corretorRepositorio;

    public List<Corretor> buscarTodos(){
        return corretorRepositorio.findAll();
    }

    public Corretor buscaPorId(Long id){
        return corretorRepositorio.findById(id).
        orElseThrow(()-> new CorretorNaoEncontradoException(id));
    }

    public Corretor cadastrar(Corretor corretor){
        String senhaCriptografada = SenhaUtils.encode(corretor.getSenha()); 

        corretor.setSenha(senhaCriptografada);
        return corretorRepositorio.save(corretor);
    }

    public Corretor atualizar(Corretor corretor,Long id){
        Corretor corretorEncontrado =buscaPorId(id);
        corretor.setSenha(corretorEncontrado.getSenha());

        return corretorRepositorio.save(corretor);
    }

    public void excluirPorId(Long id){
    
        Corretor corretorEncontrado = buscaPorId(id);

        corretorRepositorio.delete(corretorEncontrado);
    }
    
}
