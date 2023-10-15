package algafood.api.dtos.input;

import algafood.domain.models.Restaurante;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParametrosRestauranteDTO {

    @NotBlank
    private String nome;

    @NotNull
    @PositiveOrZero
    private BigDecimal precoFrete;

    @Valid
    private Cozinha cozinha;

    @Valid
    @NotNull
    EnderecoDTO endereco;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Cozinha {

        @Valid
        @NotNull
        private Long id;
    }

    public ParametrosRestauranteDTO(Restaurante restaurante) {
        this.nome = restaurante.getNome();
        this.precoFrete = restaurante.getTaxaFrete();
        this.cozinha.id = restaurante.getCozinha().getId();
    }
}
