package algafood.api.dtos.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteOutputDTO {

    private Long id;

    private String nome;

    private BigDecimal taxaFrete;

    private CozinhaOutputDTO cozinha;

    private Boolean ativo;

    private Boolean aberto;

    private EnderecoOutputDTO endereco;

}
