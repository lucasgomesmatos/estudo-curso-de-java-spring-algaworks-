package algafood.domain.repositories;

import algafood.domain.models.Produto;
import algafood.domain.models.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("FROM Produto WHERE restaurante.id = :restauranteId AND id = :produtoId")
    Optional<Produto> findById(Long restauranteId, Long produtoId);

    List<Produto> findByRestaurante(Restaurante restaurante);
}
