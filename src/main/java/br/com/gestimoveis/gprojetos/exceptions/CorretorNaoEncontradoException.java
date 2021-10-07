package br.com.gestimoveis.gprojetos.exceptions;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CorretorNaoEncontradoException extends EntityNotFoundException{


    public CorretorNaoEncontradoException (Long id) {
        super(String.format("O corretor com o ID %s ", id));
    }
    
}
