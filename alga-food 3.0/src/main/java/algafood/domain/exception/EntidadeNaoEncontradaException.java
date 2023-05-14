package algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

// @ResponseStatus(value = HttpStatus.NOT_FOUND ) // reason = "Entidade não encontada
public class EntidadeNaoEncontradaException extends ResponseStatusException {

    public EntidadeNaoEncontradaException(HttpStatusCode status, String mensagem) {
        super(status, mensagem);
    }

    public EntidadeNaoEncontradaException(String mensagem) {
        this(HttpStatus.NOT_FOUND, mensagem);
    }
}
