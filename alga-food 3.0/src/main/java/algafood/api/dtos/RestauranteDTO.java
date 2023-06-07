package algafood.api.dtos;

import algafood.domain.models.Restaurante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteDTO {

    private String nome;
    private BigDecimal taxaFrete;
    private Long idCozinha;

    public RestauranteDTO(Restaurante restaurante) {
        this.nome = restaurante.getNome();
        this.taxaFrete = restaurante.getTaxaFrete();
        this.idCozinha = restaurante.getCozinha().getId();
    }
}
