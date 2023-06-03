package algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    ENTIDADE_NAO_ENCONTADA("Entidade não encontrada", "/entidade-nao-encontada");

    private String title;
    private String path;

    ProblemType(String title, String path) {
        this.title = title;
        this.path = String.format("https://algafood.com.br%s", path);
    }


}
