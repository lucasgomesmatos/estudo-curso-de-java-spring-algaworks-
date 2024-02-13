
package algafood.domain.exception;

public class CidadeNaoEncontradoException extends EntidadeNaoEncontradaException {

    public CidadeNaoEncontradoException(String mensagem) {
        super( mensagem);
    }
    public CidadeNaoEncontradoException(Long id) {
        this( String.format("Cidade não econtrado para o id: %d", id));
    }
}
