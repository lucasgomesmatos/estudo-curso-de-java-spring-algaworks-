package algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    PARAMETRO_INVALIDO("Parâmetro inválido", "/parametro-invalido"),
    MENSAGEM_INCOMPREENSIVEL("Mensagem incompreensível", "/mensagem-imcompreensível"),
    ENTIDADE_NAO_ENCONTADA("Entidade não encontrada", "/entidade-nao-encontada"),
    ENTIDADE_EM_USO("Entidade em uso", "/entidade-em-uso"),
    ERRO_NEGOCIO("Violação de regra de negócio", "/erro-negocio");

    private String title;
    private String path;

    ProblemType(String title, String path) {
        this.title = title;
        this.path = String.format("https://algafood.com.br%s", path);
    }


}
