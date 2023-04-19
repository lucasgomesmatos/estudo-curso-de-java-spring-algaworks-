package algafood.api.dtos;

import algafood.domain.models.Restaurante;
import lombok.Data;

import java.math.BigDecimal;

@Data
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
