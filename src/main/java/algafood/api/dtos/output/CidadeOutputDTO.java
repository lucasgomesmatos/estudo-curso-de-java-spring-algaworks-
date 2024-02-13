package algafood.api.dtos.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeOutputDTO {

    private Long id;

    private String nome;

    private EstadoOutputDTO estado;

}
