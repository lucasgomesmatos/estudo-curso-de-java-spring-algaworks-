package algafood.api.dtos.output;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoOutputDTO {

    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private Boolean ativo;


}
