package com.algaworks.algafoodjpa.domain.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;


@Getter
@Setter
@Entity
public class Restaurante {

    @Id
    private Long id;

    private String nome;

    @Column(name = "taxa_frete")
    private BigDecimal taxaFrete;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurante that = (Restaurante) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
