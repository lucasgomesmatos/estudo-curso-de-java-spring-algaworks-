package algafood.api.dtos.output;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PermissaoDTO {

    private Long id;

    private String nome;

    private String descricao;

}
