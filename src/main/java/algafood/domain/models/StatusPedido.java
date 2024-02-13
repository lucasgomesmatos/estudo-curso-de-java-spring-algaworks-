package algafood.domain.models;

import lombok.Getter;

@Getter
public enum StatusPedido {
    CRIADO,
    CONFIRMADO,
    ENTREGUE,
    CANCELADO
}
