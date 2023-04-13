package algafood.api.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RestauranteDTO {
    private String nome;
    private BigDecimal taxaFrete;
    private Long idCozinha;
}
