package algafood.domain.repositories;

import algafood.domain.models.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

    List<Cozinha> findByNomeContaining(String nome);

    Optional<Cozinha> findByNome(String nome);

    boolean existsByNome(String nome);
}
