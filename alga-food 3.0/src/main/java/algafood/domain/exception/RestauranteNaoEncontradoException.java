
package algafood.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

    public RestauranteNaoEncontradoException(String mensagem) {
        super( mensagem);
    }
    public RestauranteNaoEncontradoException(Long id) {
        this( String.format("Restaurante não econtrado para o id:", id));
    }
}
