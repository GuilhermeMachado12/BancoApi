package br.org.fundatec.banco.controller;

import br.org.fundatec.banco.model.Agencia;
import br.org.fundatec.banco.service.AgenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="/agencias")
public class AgenciaController {

    @Autowired
    private AgenciaService agenciaService;

    // POST /agencias/cadastrar
    @PostMapping(value = "/cadastrar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Agencia> cadastrar(@RequestBody @Valid Agencia agencia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agenciaService.cadastrar(agencia));
    }

    // DELETE /agencias/remover/1
    @DeleteMapping(value = "/remover/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
        agenciaService.remover(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // GET /agencias/find-id/1
    @GetMapping(value = "/find-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Agencia> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(agenciaService.buscarPorId(id));
    }

    // PUT /agencias/editar/1
    @PutMapping(value = "/editar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Agencia> editar(@PathVariable("id") Long id, @RequestBody @Valid Agencia agencia) {
        return ResponseEntity.status(HttpStatus.OK).body(agenciaService.editar(id, agencia));
    }

    // GET /agencias/por-banco/2
    @GetMapping(value = "/por-banco/{bancoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Agencia>> buscarPorBanco(@PathVariable("bancoId") Long bancoId) {
        return ResponseEntity.status(HttpStatus.OK).body(agenciaService.buscarPorBanco(bancoId));
    }

    // GET /agencias/por-numero/101
    @GetMapping(value = "/por-numero/{numero}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Agencia> buscarPorNumero(@PathVariable("numero") Integer numero) {
        return ResponseEntity.status(HttpStatus.OK).body(agenciaService.buscarPorNumero(numero));
    }
}
