package algafood.api.exceptionhandler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class ApiExceptionError {

    private LocalDateTime dataHora;
    private String mensagem;
}
