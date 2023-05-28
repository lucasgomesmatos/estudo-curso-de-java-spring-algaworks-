package algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST ) // reason = "Entidade não encontada
public class NegocioException extends RuntimeException {

    public NegocioException(String mensagem) {
        super( mensagem);
    }
    public NegocioException(String mensagem, Throwable causa) {
        super( mensagem, causa);
    }
}
