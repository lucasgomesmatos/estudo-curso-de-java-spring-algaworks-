
package algafood.domain.exception;

public class UsuarioException extends EntidadeNaoEncontradaException {

    public UsuarioException(String mensagem) {
        super( mensagem);
    }
    public UsuarioException(Long id) {
        this( String.format("Usuário não econtrado para o id: %d", id));
    }
}
