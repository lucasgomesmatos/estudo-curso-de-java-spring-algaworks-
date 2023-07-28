package algafood.api.dtos.output;

import algafood.domain.models.Restaurante;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

// @ValorZeroIncluiDescricao(valorField = "taxaFrete", descricaoField = "nome", descricaoObrigatorio = "Frete Grátis")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteOutputDTO {


    @NotBlank
    private String nome;


    @NotNull
    @PositiveOrZero
    private BigDecimal taxaFrete;

    @NotNull
    private Long idCozinha;

    public RestauranteOutputDTO(Restaurante restaurante) {
        this.nome = restaurante.getNome();
        this.taxaFrete = restaurante.getTaxaFrete();
        this.idCozinha = restaurante.getCozinha().getId();
    }
}
