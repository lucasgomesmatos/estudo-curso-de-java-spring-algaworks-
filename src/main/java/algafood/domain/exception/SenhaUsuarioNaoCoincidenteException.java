package algafood.domain.exception;

public class SenhaUsuarioNaoCoincidenteException extends NegocioException {
    public SenhaUsuarioNaoCoincidenteException(String mensagem) {
        super(mensagem);
    }
}
