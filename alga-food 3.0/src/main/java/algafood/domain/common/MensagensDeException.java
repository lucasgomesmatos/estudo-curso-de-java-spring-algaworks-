package algafood.domain.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MensagensDeException {

    MENSAGEM_COZINHA_EM_USO("Cozinha de código %d não pode ser removido, pois está em uso"),

    MENSAGEM_ESTADO_EM_USO("Estado de código %d não pode ser removido, pois está em uso"),

    MENSAGEM_RESTAURANTE_EM_USO("Restaurante de código %d não pode ser removido, pois está em uso"),
    MENSAGEM_FORMA_PAGAMENTO_EM_USO("Forma de Pagamento de código %d não pode ser removido, pois está em uso");

    private String mensagem;

}
