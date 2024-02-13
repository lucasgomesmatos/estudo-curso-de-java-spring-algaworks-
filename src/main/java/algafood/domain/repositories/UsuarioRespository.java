package algafood.domain.repositories;

import algafood.domain.models.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRespository extends CustomJpaRepository<Usuario, Long>  {

    Optional<Usuario> findByEmail(String email);
}
