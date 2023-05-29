package algafood.api.exceptionhandler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class ErrorApi {

    private LocalDateTime dataHora;
    private String mensagem;
}
