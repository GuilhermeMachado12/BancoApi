package br.org.fundatec.banco.repository;


import br.org.fundatec.banco.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    Optional<Conta> findByNumero(Integer numero);
}

