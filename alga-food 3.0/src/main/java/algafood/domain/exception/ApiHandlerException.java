package algafood.domain.exception;

public class ApiHandlerException extends RuntimeException{

    public ApiHandlerException(String mensagem) {
        super(mensagem);
    }
}
