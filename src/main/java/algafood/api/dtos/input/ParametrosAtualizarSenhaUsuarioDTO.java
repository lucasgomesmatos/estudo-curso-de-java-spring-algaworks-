package algafood.api.dtos.input;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParametrosAtualizarSenhaUsuarioDTO {

    @NotBlank
    private String senhaAtual;

    @NotBlank
    private String novaSenha;

}
