package br.org.fundatec.banco.service;

import br.org.fundatec.banco.exception.RegistroNaoEcontradoException;
import br.org.fundatec.banco.model.Cliente;
import br.org.fundatec.banco.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;



    public Cliente cadastrar(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
        if (cliente.getCpf() == null || cliente.getCpf().trim().isEmpty()) {
            throw new IllegalArgumentException("CPF é obrigatório.");
        }
        return clienteRepository.save(cliente);
    }

    public void remover(Long id) {
        Cliente cliente = buscarPorId(id);
        clienteRepository.delete(cliente);
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEcontradoException("Cliente não encontrado com ID: " + id));
    }

    public Cliente editar(Long id, Cliente novoCliente) {
        Cliente existente = buscarPorId(id);
        existente.setNome(novoCliente.getNome());
        existente.setCpf(novoCliente.getCpf());
        return clienteRepository.save(existente);
    }

    public List<Cliente> buscarPorNomeAproximado(String nome) {
        return clienteRepository.findByNomeContainingIgnoreCase(nome);
    }
}
