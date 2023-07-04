package algafood.api.dtos;

import algafood.core.TaxaFrete;
import algafood.domain.models.Restaurante;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteDTO {

//    @NotNull
//    @NotEmpty
    @NotBlank
    private String nome;

//    @DecimalMin("0")
    @NotNull
    // @PositiveOrZero //(message = "{TaxaFrete.invalida}")
    @TaxaFrete
    private BigDecimal taxaFrete;

    @NotNull
    private Long idCozinha;

    public RestauranteDTO(Restaurante restaurante) {
        this.nome = restaurante.getNome();
        this.taxaFrete = restaurante.getTaxaFrete();
        this.idCozinha = restaurante.getCozinha().getId();
    }
}
