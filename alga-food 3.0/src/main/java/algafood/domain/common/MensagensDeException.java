package algafood.domain.common;

public enum MensagensDeException {

    MENSAGEM_COZINHA_EM_USO("Cozinha de código %d não pode ser removido, pois está em uso"),

    MENSAGEM_ESTADO_EM_USO("Estado de código %d não pode ser removido, pois está em uso");


    private String mensagem;
    MensagensDeException(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
