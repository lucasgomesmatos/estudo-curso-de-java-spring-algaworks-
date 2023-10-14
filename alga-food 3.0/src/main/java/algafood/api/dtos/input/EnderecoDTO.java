package algafood.api.dtos.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
public class EnderecoDTO {

    @NotBlank
    private String cep;

    @NotBlank
    private String logradouro;

    @NotBlank
    private String numero;

    private String complemento;

    @NotBlank
    private String bairro;

    @Valid
    @NotNull
    private Cidade cidade;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Cidade {
        private Long id;
    }

}
