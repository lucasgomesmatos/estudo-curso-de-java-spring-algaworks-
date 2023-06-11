package algafood.api.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CozinhaDTO {

    @NotBlank
    private String nome;
}
