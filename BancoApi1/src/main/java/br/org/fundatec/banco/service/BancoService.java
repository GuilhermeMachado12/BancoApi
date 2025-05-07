package br.org.fundatec.banco.service;

import br.org.fundatec.banco.model.Banco;
import br.org.fundatec.banco.repository.BancoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BancoService {

    @Autowired
    private BancoRepository bancoRepository;

    public Banco cadastrar(Banco banco) {
        if (banco.getCodigo() == null || banco.getCodigo().trim().isEmpty()) {
            throw new IllegalArgumentException("Código do banco é obrigatório.");
        }
        if (banco.getNome() == null || banco.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do banco é obrigatório.");
        }
        if (banco.getCnpj() == null || banco.getCnpj().trim().isEmpty()) {
            throw new IllegalArgumentException("CNPJ do banco é obrigatório.");
        }

        if (bancoRepository.existsByCodigo(banco.getCodigo())) {
            throw new IllegalArgumentException("Já existe um banco com o código informado.");
        }

        if (bancoRepository.existsByCnpj(banco.getCnpj())) {
            throw new IllegalArgumentException("Já existe um banco com o CNPJ informado.");
        }

        return bancoRepository.save(banco);
    }
    public void remover(Long id) {
        if (!bancoRepository.existsById(id)) {
            throw new EntityNotFoundException("Banco com ID " + id + " não encontrado para remoção.");
        }
        bancoRepository.deleteById(id);
    }

    public Banco buscarPorId(Long id) {
        return bancoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Banco com ID " + id + " não encontrado."));
    }

    public List<Banco> buscarPorNomeAproximado(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome para busca não pode ser vazio.");
        }
        return bancoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Banco buscarPorCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("O código não pode ser vazio.");
        }
        return bancoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new EntityNotFoundException("Banco com código '" + codigo + "' não encontrado."));
    }

    public List<Banco> listarTodos() {
        return bancoRepository.findAll();
    }

    public Banco editar(Long id, Banco bancoAtualizado) {
        Banco banco = buscarPorId(id);

        if (bancoAtualizado.getCodigo() == null || bancoAtualizado.getCodigo().trim().isEmpty()) {
            throw new IllegalArgumentException("Código do banco é obrigatório.");
        }
        if (bancoAtualizado.getNome() == null || bancoAtualizado.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do banco é obrigatório.");
        }
        if (bancoAtualizado.getCnpj() == null || bancoAtualizado.getCnpj().trim().isEmpty()) {
            throw new IllegalArgumentException("CNPJ do banco é obrigatório.");
        }

        if (!banco.getCodigo().equals(bancoAtualizado.getCodigo()) && bancoRepository.existsByCodigo(bancoAtualizado.getCodigo())) {
            throw new IllegalArgumentException("Já existe outro banco com o código informado.");
        }

        if (!banco.getCnpj().equals(bancoAtualizado.getCnpj()) && bancoRepository.existsByCnpj(bancoAtualizado.getCnpj())) {
            throw new IllegalArgumentException("Já existe outro banco com o CNPJ informado.");
        }

        banco.setCodigo(bancoAtualizado.getCodigo());
        banco.setNome(bancoAtualizado.getNome());
        banco.setCnpj(bancoAtualizado.getCnpj());

        return bancoRepository.save(banco);
    }
}




