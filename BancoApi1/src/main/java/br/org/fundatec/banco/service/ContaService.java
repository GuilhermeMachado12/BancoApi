package br.org.fundatec.banco.service;

import br.org.fundatec.banco.exception.RegistroNaoEcontradoException;
import br.org.fundatec.banco.model.Agencia;
import br.org.fundatec.banco.model.Cliente;
import br.org.fundatec.banco.model.Conta;
import br.org.fundatec.banco.repository.AgenciaRepository;
import br.org.fundatec.banco.repository.ClienteRepository;
import br.org.fundatec.banco.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AgenciaRepository agenciaRepository;

    public Conta cadastrar(Conta conta) {
        if (conta.getNumero() == null) {
            throw new IllegalArgumentException("Número da conta é obrigatório.");
        }

        if (conta.getCliente() == null || conta.getCliente().getId() == null) {
            throw new IllegalArgumentException("Cliente é obrigatório.");
        }

        if (conta.getAgencia() == null || conta.getAgencia().getId() == null) {
            throw new IllegalArgumentException("Agência é obrigatória.");
        }

        Cliente cliente = clienteRepository.findById(conta.getCliente().getId())
                .orElseThrow(() -> new RegistroNaoEcontradoException("Cliente não encontrado."));

        Agencia agencia = agenciaRepository.findById(Long.valueOf(conta.getAgencia().getId()))
                .orElseThrow(() -> new RegistroNaoEcontradoException("Agência não encontrada."));

        conta.setCliente(cliente);
        conta.setAgencia(agencia);
        conta.setSaldo(0.0);

        return contaRepository.save(conta);
    }

    public void remover(Long id) {
        Conta conta = buscarPorId(id);
        contaRepository.delete(conta);
    }

    public Conta buscarPorNumero(Integer numero) {
        return contaRepository.findByNumero(numero)
                .orElseThrow(() -> new RegistroNaoEcontradoException("Conta não encontrada com número: " + numero));
    }

    public Conta sacar(Integer numero, Double valor) {
        Conta conta = buscarPorNumero(numero);

        if (valor == null || valor <= 0) {
            throw new IllegalArgumentException("Valor de saque deve ser maior que zero.");
        }

        if (conta.getSaldo() < valor) {
            throw new IllegalArgumentException("Saldo insuficiente para saque.");
        }

        conta.setSaldo(conta.getSaldo() - valor);
        return contaRepository.save(conta);
    }

    public Conta depositar(Integer numero, Double valor) {
        Conta conta = buscarPorNumero(numero);

        if (valor == null || valor <= 0) {
            throw new IllegalArgumentException("Valor de depósito deve ser maior que zero.");
        }

        conta.setSaldo(conta.getSaldo() + valor);
        return contaRepository.save(conta);
    }

    private Conta buscarPorId(Long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEcontradoException("Conta não encontrada com ID: " + id));
    }
}