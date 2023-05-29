
package algafood.domain.exception;

public class CidadeNaoEncontradoException extends EntidadeNaoEncontradaException {

    public CidadeNaoEncontradoException(String mensagem) {
        super( mensagem);
    }
    public CidadeNaoEncontradoException(Long id) {
        this( String.format("Restaurante não econtrado para o id:", id));
    }
}
