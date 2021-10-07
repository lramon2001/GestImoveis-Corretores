package br.com.gestimoveis.gprojetos.repositorios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.gestimoveis.gprojetos.model.Corretor;

import java.util.List;
import java.util.Optional;

public interface CorretorRepositorio extends JpaRepository<Corretor,Long>{

    @Query("select c from Corretor c where c.usuario = :usuario")
    List<Corretor> buscarPorUsuario(String usuario);
    
    @Query("select c from Corretor c where c.usuario <> :usuario")
    List<Corretor> buscarPorUsuarioExceto(String usuario);

    List<Corretor> findByUsuario(String usuario);


    Optional<Corretor> findByEmail(String email);
    
    
}
