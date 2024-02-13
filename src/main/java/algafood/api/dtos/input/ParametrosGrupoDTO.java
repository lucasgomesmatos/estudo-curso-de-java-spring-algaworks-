package algafood.api.dtos.input;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParametrosGrupoDTO {

    @NotEmpty
    private String nome;
}
