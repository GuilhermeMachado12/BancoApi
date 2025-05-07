package br.org.fundatec.banco.repository;

import br.org.fundatec.banco.model.Agencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AgenciaRepository extends JpaRepository<Agencia, Long> {

    List<Agencia> findByBancoId(Long bancoId);

    Optional<Agencia> findByNumero(Integer numero);
}
