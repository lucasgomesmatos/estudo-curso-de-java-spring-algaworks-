package algafood.api.dtos.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoOutputDTO {

    private String cep;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private CidadeSummaryOutputDTO cidade;
}
