package algafood.api.dtos.output;

import lombok.*;

@Getter
@Setter
public class EnderecoOutputDTO {

    private String cep;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private Cidade cidade;


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Cidade {

        private Long id;

        private String nome;

        private Estado estado;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Estado {

        private Long id;

        private String nome;
    }
}
