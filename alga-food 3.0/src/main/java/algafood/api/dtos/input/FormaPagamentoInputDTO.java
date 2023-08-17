package algafood.api.dtos.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FormaPagamentoInputDTO {

    @NotBlank
    private String descricao;


}
