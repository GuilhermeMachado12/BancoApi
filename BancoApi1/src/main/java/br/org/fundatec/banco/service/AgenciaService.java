package br.org.fundatec.banco.service;

import br.org.fundatec.banco.model.Agencia;
import br.org.fundatec.banco.model.Banco;
import br.org.fundatec.banco.repository.AgenciaRepository;
import br.org.fundatec.banco.repository.BancoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AgenciaService {

    @Autowired
    private AgenciaRepository agenciaRepository;

    @Autowired
    private BancoRepository bancoRepository;

    public Agencia cadastrar(Agencia agencia) {
        if (agencia.getNumero() == null) {
            throw new IllegalArgumentException("Número da agência é obrigatório.");
        }
        if (agencia.getNome() == null || agencia.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da agência é obrigatório.");
        }
        if (agencia.getBanco() == null || agencia.getBanco().getId() == null) {
            throw new IllegalArgumentException("Banco é obrigatório.");
        }

        Banco banco = bancoRepository.findById(Long.valueOf(agencia.getBanco().getId()))
                .orElseThrow(() -> new NoSuchElementException("Banco não encontrado."));
        agencia.setBanco(banco);

        return agenciaRepository.save(agencia);
    }

    public void remover(Long id) {
        Agencia agencia = buscarPorId(id);
        agenciaRepository.delete(agencia);
    }

    public Agencia buscarPorId(Long id) {
        return agenciaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Agência não encontrada com ID: " + id));
    }

    public Agencia editar(Long id, Agencia novaAgencia) {
        Agencia existente = buscarPorId(id);

        if (novaAgencia.getNumero() != null) {
            existente.setNumero(novaAgencia.getNumero());
        }

        if (novaAgencia.getNome() != null) {
            existente.setNome(novaAgencia.getNome());
        }

        if (novaAgencia.getBanco() != null && novaAgencia.getBanco().getId() != null) {
            Banco banco = bancoRepository.findById(Long.valueOf(novaAgencia.getBanco().getId()))
                    .orElseThrow(() -> new NoSuchElementException("Banco não encontrado."));
            existente.setBanco(banco);
        }

        return agenciaRepository.save(existente);
    }

    public List<Agencia> buscarPorBanco(Long bancoId) {
        return agenciaRepository.findByBancoId(bancoId);
    }

    public Agencia buscarPorNumero(Integer numero) {
        return agenciaRepository.findByNumero(numero)
                .orElseThrow(() -> new NoSuchElementException("Agência não encontrada com número: " + numero));
    }
}