
package algafood.domain.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public EstadoNaoEncontradoException(String mensagem) {
        super( mensagem);
    }
    public EstadoNaoEncontradoException(Long id) {
        this( String.format("Estado não econtrada para o id:", id));
    }
}
