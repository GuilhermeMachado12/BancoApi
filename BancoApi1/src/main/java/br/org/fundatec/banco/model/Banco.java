package br.org.fundatec.banco.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
public class Banco {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "banco_sequence")
    @SequenceGenerator(name = "banco_sequence", sequenceName = "banco_sequence", allocationSize = 1)
    private Long id;

    @NotBlank(message = "O código precisa ser informado" )
    @Size(max = 8, message = "O código precisa ter no máximo 8 caracteres ")
    private String codigo;

    @NotBlank(message = "O nome precisa ser informado" )
    @Size(min = 10, message = "O nome precisa ter no mínimo 10 caracteres")
    private String nome;

    @NotBlank(message = "O CNPJ precisa ser informado")
    @Size(max = 14, message = "O cnpj precisa ter no máximo 14 caracteres")
    private String cnpj;

    public Banco() {
    }

    public Banco(Long id, String codigo, String nome, String cnpj) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.cnpj = cnpj;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Banco banco)) return false;
        return Objects.equals(id, banco.id) && Objects.equals(codigo, banco.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo);
    }
}
