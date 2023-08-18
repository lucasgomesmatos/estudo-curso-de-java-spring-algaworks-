package algafood.api.dtos.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

// @ValorZeroIncluiDescricao(valorField = "taxaFrete", descricaoField = "nome", descricaoObrigatorio = "Frete Grátis")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteOutputDTO {

    private Long id;

    private String nome;

    private BigDecimal taxaFrete;

    private Long idCozinha;

    private Boolean ativo;

    private EnderecoOutputDTO endereco;

}
