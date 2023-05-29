
package algafood.domain.exception;

public class CozinhaNaoEncontradoException extends EntidadeNaoEncontradaException {

    public CozinhaNaoEncontradoException(String mensagem) {
        super( mensagem);
    }
    public CozinhaNaoEncontradoException(Long id) {
        this( String.format("Cozinha não econtrada para o id:", id));
    }
}
