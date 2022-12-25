package com.algaworks.algafoodjpa.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "tab_cozinhas")
public class Cozinha {

    @Id
    private Long id;

    @Column(name = "nom_cozinha")
    private String nome;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cozinha cozinha = (Cozinha) o;
        return id.equals(cozinha.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
