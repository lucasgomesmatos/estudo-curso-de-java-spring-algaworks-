package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.infrastructure.RestauranteRepositoryQueries;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, RestauranteRepositoryQueries {

    List<Restaurante> findByTaxaFreteBetween (BigDecimal taxaInicial, BigDecimal taxaFinal);

}
