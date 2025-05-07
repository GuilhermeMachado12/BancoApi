package br.org.fundatec.banco.repository;

import br.org.fundatec.banco.model.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BancoRepository extends JpaRepository<Banco, Long> {
    List<Banco> findByNomeContainingIgnoreCase(String nome);
    Optional<Banco> findByCodigo(String codigo);
    Optional<Banco> findByCnpj(String cnpj);
    boolean existsByCodigo(String codigo);
    boolean existsByCnpj(String cnpj);
}

