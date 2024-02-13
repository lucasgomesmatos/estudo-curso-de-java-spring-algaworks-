package algafood.domain.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    //@JsonIgnore
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCadastro;

    //@JsonIgnore
    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataAtualizacao;

    private Boolean ativo = true;

    private Boolean aberto = true;

    // @JsonIgnore
    // @JsonIgnoreProperties("hibernateLazyInitializer")
    @ManyToOne //(fetch = FetchType.LAZY)
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;

    @JsonIgnore
    @Embedded
    private Endereco endereco;

    @JsonIgnore
    @ManyToMany // (fetch = FetchType.EAGER)
    @JoinTable(name = "restaurante_forma_pagamento",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id")
    )
    @ToString.Exclude
    private Set<FormaPagamento> formaPagamentos = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "restaurante")
    @ToString.Exclude
    private List<Produto> produtos = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "restaurante_usuario_responsavel",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> usuarios = new HashSet<>();


    public void ativar() {
        setAtivo(true);
    }

    public void inativar() {
        setAtivo(false);
    }

    public void fechamento() {
        setAberto(false);
    }

    public void abertura() {
        setAberto(true);
    }

    public void associarFormaPagamento(FormaPagamento formaPagamento) {
        getFormaPagamentos().add(formaPagamento);
    }

    public void desassociarFormaPagamento(FormaPagamento formaPagamento) {
        getFormaPagamentos().remove(formaPagamento);
    }


    public void desassociarUsuario(Usuario usuario) {
        getUsuarios().remove(usuario);
    }

    public void associarUsuario(Usuario usuario) {
        getUsuarios().add(usuario);
    }
}
