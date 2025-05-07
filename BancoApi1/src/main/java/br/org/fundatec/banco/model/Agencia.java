package br.org.fundatec.banco.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "agencia")
public class Agencia {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agencia_sequence")
    @SequenceGenerator(name = "agencia_sequence", sequenceName = "agencia_sequence", allocationSize = 1)
    private Integer id;

    @NotNull(message = "O número precisa ser informado")
    @Size(min = 5, message = "O número da agencia precisa ter no mínimo 5 caracteres")
    private Integer numero;

    @NotBlank(message = "O nome precisa ser informado")
    @Size(min = 10, message = "O nome precisa ter no mínimo 10 caracteres")
    private String nome;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_banco", referencedColumnName = "id")
    @NotBlank(message = "O banco precisa ser informado")
    private Banco banco;

    public Agencia() {
    }

    public Agencia(Integer id, Integer numero, String nome, Banco banco) {
        this.id = id;
        this.numero = numero;
        this.nome = nome;
        this.banco = banco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Agencia agencia)) return false;
        return Objects.equals(id, agencia.id) && Objects.equals(numero, agencia.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numero);
    }
}
