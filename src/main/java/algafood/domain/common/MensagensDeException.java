package algafood.domain.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MensagensDeException {

    MENSAGEM_COZINHA_EM_USO("Cozinha de código %d não pode ser removido, pois está em uso"),

    MENSAGEM_ESTADO_EM_USO("Estado de código %d não pode ser removido, pois está em uso"),

    MENSAGEM_RESTAURANTE_EM_USO("Restaurante de código %d não pode ser removido, pois está em uso"),

    MENSAGEM_FORMA_PAGAMENTO_EM_USO("Forma de Pagamento de código %d não pode ser removido, pois está em uso"),

    MENSAGEM_FORMA_GRUPO("Grupo de código %d não pode ser removido, pois está em uso"),

    MENSAGEM_USUARIO_EXISTENTE("Já existe um Usuário cadastrado com esse email %s !"),

    MENSAGEM_SENHA_USUARIO_NAO_COINCIDENTE("A sua senha informada não coincide com a senha do usuário");

    private final String mensagem;

}
