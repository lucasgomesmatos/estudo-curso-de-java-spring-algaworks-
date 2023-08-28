package algafood.api.dtos.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInputDTO {

    private String cep;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private CidadeInputDTO cidade;
}
