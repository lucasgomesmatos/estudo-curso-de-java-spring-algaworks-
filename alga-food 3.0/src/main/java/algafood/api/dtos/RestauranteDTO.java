package algafood.api.dtos;

import algafood.core.ValorZeroIncluiDescricao;
import algafood.domain.models.Restaurante;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@ValorZeroIncluiDescricao(valorField = "taxaFrete", descricaoField = "nome", descricaoObrigatorio = "Frete Grátis")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteDTO {

    //    @NotNull
//    @NotEmpty
    @NotBlank
    private String nome;


    @NotNull
    @PositiveOrZero //(message = "{TaxaFrete.invalida}")
    //@TaxaFrete
    //@Multiplo(numero = 5)
    //    @DecimalMin("0")
    private BigDecimal taxaFrete;

    @NotNull
    private Long idCozinha;

    public RestauranteDTO(Restaurante restaurante) {
        this.nome = restaurante.getNome();
        this.taxaFrete = restaurante.getTaxaFrete();
        this.idCozinha = restaurante.getCozinha().getId();
    }
}
