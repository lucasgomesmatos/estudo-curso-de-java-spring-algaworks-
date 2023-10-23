package algafood.domain.exception;

public class UsuarioExistenteException extends NegocioException {
    public UsuarioExistenteException(String mensagem) {
        super(mensagem);
    }
}
