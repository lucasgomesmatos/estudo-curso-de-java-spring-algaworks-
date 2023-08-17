
package algafood.domain.exception;

public class FormaPagamentoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public FormaPagamentoNaoEncontradoException(String mensagem) {
        super( mensagem);
    }
    public FormaPagamentoNaoEncontradoException(Long id) {
        this( String.format("Forma de Pagamento não econtrado para o id: %d", id));
    }
}
