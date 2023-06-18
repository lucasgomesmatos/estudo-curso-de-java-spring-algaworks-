package algafood.api.exceptionhandler;

import algafood.domain.exception.EntidadeEmUsoException;
import algafood.domain.exception.EntidadeNaoEncontradaException;
import algafood.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String MESSAGE_ERROR_GENERIC_FINAL_USER = "Ocorreu um erro interno inesperado no sistema. "
            + "Tente novamente e se o problema persistir, entre em contato "
            + "com o administrador do sistema.";

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        var problemType = ProblemType.ERRO_DE_SISTEMA;

        // Importante colocar o printStackTrace (pelo menos por enquanto, que não estamos
        // fazendo logging) para mostrar a stacktrace no console
        // Se não fizer isso, você não vai ver a stacktrace de exceptions que seriam importantes
        // para você durante, especialmente na fase de desenvolvimento
        ex.printStackTrace();

        var problem = createErrorBuild(status, problemType, MESSAGE_ERROR_GENERIC_FINAL_USER).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var detail = String.format("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");
        var problemType = ProblemType.DADOS_INVALIDOS;

        var bindingResult = ex.getBindingResult();

        var fields = bindingResult.getFieldErrors().stream().map(
                fieldError ->
                {
                    var message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());

                    return ApiExceptionError.Field.builder()
                            .name(fieldError.getField())
                            .userMessage(message)
                            .build();
                }

        ).toList();

        var error = createErrorBuild((HttpStatus) status, problemType, detail)
                .fields(fields)
                .build();

        return handleExceptionInternal(ex, error, headers, status, request);
    }


    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        var detail = String.format("O Resurso '%s', que você tentou acessar, é inexistente", ex.getRequestURL());
        var problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        var error = createErrorBuild((HttpStatus) status, problemType, detail).build();

        return handleExceptionInternal(ex, error, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        if (ex instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatch(
                    (MethodArgumentTypeMismatchException) ex, headers, (HttpStatus) status, request);
        }

        return super.handleTypeMismatch(ex, headers, status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        var detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', "
                        + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
                ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

        var problemType = ProblemType.PARAMETRO_INVALIDO;
        var error = createErrorBuild(status, problemType, detail).build();

        return handleExceptionInternal(ex, error, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        var rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
        }

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        }


        var detail = "O corpo da requisição está inválido, Verifique erro de sintaxe";
        var problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        var error = createErrorBuild((HttpStatus) status, problemType, detail).build();

        return handleExceptionInternal(ex, error, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        var path = joinPath(ex.getPath());
        var detail = String.format("A propriedade '%s' não existe. Corrija ou remova essa propriedade e tente novamente.", path);
        var problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;

        var error = createErrorBuild((HttpStatus) status, problemType, detail)
                .userMessage(MESSAGE_ERROR_GENERIC_FINAL_USER)
                .build();
        return handleExceptionInternal(ex, error, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        var path = joinPath(ex.getPath());
        var detail = String.format("A propriedade '%s' recebeu o valor '%s', " +
                "que é um tipo inválido. Corrija e informe um valor compatível com o tipo %s.", path, ex.getValue(), ex.getTargetType().getSimpleName());
        var problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        var error = createErrorBuild((HttpStatus) status, problemType, detail).build();
        return handleExceptionInternal(ex, error, headers, status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEstadoNaoEncontadoException(EntidadeNaoEncontradaException ex, WebRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var detail = ex.getMessage();
        var problemType = ProblemType.RECURSO_NAO_ENCONTADO;
        var error = createErrorBuild(status, problemType, detail).build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var detail = ex.getMessage();
        var problemType = ProblemType.ERRO_NEGOCIO;
        var error = createErrorBuild(status, problemType, detail).build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);

    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {
        var status = HttpStatus.CONFLICT;
        var detail = ex.getMessage();
        var problemType = ProblemType.ENTIDADE_EM_USO;
        var error = createErrorBuild(status, problemType, detail)
                .userMessage(detail).build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
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
                .timestamp(LocalDateTime.now())
                .detail(detail);
    }

    private String joinPath(List<JsonMappingException.Reference> references) {
        return references.stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));
    }


}
