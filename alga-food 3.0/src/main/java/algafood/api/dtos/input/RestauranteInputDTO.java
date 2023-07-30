package algafood.api.dtos.input;

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
public class RestauranteInputDTO {

    @NotBlank
    private String nome;

    @NotNull
    @PositiveOrZero
    private BigDecimal precoFrete;

    @NotNull
    private Long idCozinha;

    public RestauranteInputDTO(Restaurante restaurante) {
        this.nome = restaurante.getNome();
        this.precoFrete = restaurante.getTaxaFrete();
        this.idCozinha = restaurante.getCozinha().getId();
    }
}
