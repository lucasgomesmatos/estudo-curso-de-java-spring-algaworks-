package algafood.api.dtos.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CozinhaOutputDTO {

    private Long id;

    private String nome;
}
