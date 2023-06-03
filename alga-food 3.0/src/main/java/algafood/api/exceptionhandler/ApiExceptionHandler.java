package algafood.api.exceptionhandler;

import algafood.domain.exception.EntidadeEmUsoException;
import algafood.domain.exception.EntidadeNaoEncontradaException;
import algafood.domain.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEstadoNaoEncontadoException(EntidadeNaoEncontradaException ex, WebRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var detail = ex.getMessage();
        var problemType = ProblemType.ENTIDADE_NAO_ENCONTADA;
        var error = createErrorBuild(status, problemType, detail).build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        if (body == null) {
            body = ApiExceptionError.builder()
                    .title(ex.getMessage())
                    .status(statusCode.value())
                    .build();
        } else if (body instanceof String) {
            body = ApiExceptionError.builder()
                    .title((String) body)
                    .status(statusCode.value())
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    private ApiExceptionError.ApiExceptionErrorBuilder createErrorBuild(
            HttpStatus status, ProblemType problemType, String detail) {
        return ApiExceptionError.builder()
                .status(status.value())
                .title(problemType.getTitle())
                .type(problemType.getPath())
                .detail(detail);
    }


}
