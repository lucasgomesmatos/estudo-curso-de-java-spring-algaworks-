package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
    List<Cozinha> findByNome(String nome);

    List<Cozinha> findByNomeContaining(String nome);

    @Query("from Cozinha where nome like %:nome%")
    List<Cozinha> listarPorNome(String nome);

}
