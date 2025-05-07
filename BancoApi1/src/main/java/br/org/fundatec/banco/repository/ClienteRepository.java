package br.org.fundatec.banco.repository;

import br.org.fundatec.banco.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNomeContainingIgnoreCase(String nome);

    Optional<Cliente> findById(Long id);
}
