package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
//    List<Cozinha> findByName(String nome);

//    List<Cozinha> listarPorNome(String nome);

}
