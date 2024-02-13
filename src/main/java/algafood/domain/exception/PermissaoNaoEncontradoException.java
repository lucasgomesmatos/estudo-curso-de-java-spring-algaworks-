
package algafood.domain.exception;

public class PermissaoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public PermissaoNaoEncontradoException(String mensagem) {
        super( mensagem);
    }
    public PermissaoNaoEncontradoException(Long id) {
        this( String.format("Forma de Pagamento não econtrado para o id: %d", id));
    }
}
