
package algafood.domain.exception;

public class GrupoException extends EntidadeNaoEncontradaException {

    public GrupoException(String mensagem) {
        super( mensagem);
    }
    public GrupoException(Long id) {
        this( String.format("Grupo não econtrado para o id: %d", id));
    }
}
