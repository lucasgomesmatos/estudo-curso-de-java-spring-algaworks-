package algafood.api.dtos.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ParametrosFormaPagamentoDTO {

    @NotBlank
    private String descricao;


}
