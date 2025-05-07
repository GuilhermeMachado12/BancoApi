package br.org.fundatec.banco.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
public class Conta {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conta_sequence")
    @SequenceGenerator(name = "conta_sequence", sequenceName = "conta_sequence", allocationSize = 1)
    private Integer id;

    @NotNull(message = "O número precisa ser informado ")
    @Size(max = 8, message = "O número da conta precisa ter no máximo 8 caracteres")
    private Integer numero;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @NotBlank(message = "O cliente precisa ser informado")
    private Cliente cliente;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_agencia", referencedColumnName = "id")
    @NotBlank(message = "A agencia precisa ser informado")
    private Agencia agencia;
    private double saldo = 0.0;

    public Conta() {
    }

    public Conta(Integer id, Integer numero, Cliente cliente, Agencia agencia) {
        this.id = id;
        this.numero = numero;
        this.cliente = cliente;
        this.agencia = agencia;
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

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Conta conta)) return false;
        return Objects.equals(id, conta.id) && Objects.equals(numero, conta.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numero);
    }



}

