package algafood.api.exceptionhandler;

import algafood.domain.exception.EntidadeEmUsoException;
import algafood.domain.exception.EntidadeNaoEncontradaException;
import algafood.domain.exception.NegocioException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> tratarEstadoNaoEncontadoException(EntidadeNaoEncontradaException e) {
        var erro = ApiExceptionError.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(e.getMessage())
                .build();


        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
    @ExceptionHandler(NegocioException.class)
    public  ResponseEntity<?> tratarNegocioException(NegocioException e) {
        var erro = ApiExceptionError.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public  ResponseEntity<?> tratarEntidadeEmUsoException(EntidadeEmUsoException e) {
        var erro = ApiExceptionError.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public  ResponseEntity<?> tratarDataIntegrityViolationException(DataIntegrityViolationException e) {
        var erro = ApiExceptionError.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public  ResponseEntity<?> tratarHttpMediaTypeNotSupportedException() {
        var erro = ApiExceptionError.builder()
                .dataHora(LocalDateTime.now())
                .mensagem("Tipo de mídia não é aceito")
                .build();
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(erro);
    }
}
