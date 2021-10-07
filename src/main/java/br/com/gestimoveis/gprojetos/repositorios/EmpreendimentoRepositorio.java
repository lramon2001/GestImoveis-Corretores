package br.com.gestimoveis.gprojetos.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.gestimoveis.gprojetos.model.Empreendimento;
import br.com.gestimoveis.gprojetos.model.StatusImovel;

public interface EmpreendimentoRepositorio  extends JpaRepository<Empreendimento,Long>{

   
    List<Empreendimento> findByStatus(StatusImovel status);;
    
}
