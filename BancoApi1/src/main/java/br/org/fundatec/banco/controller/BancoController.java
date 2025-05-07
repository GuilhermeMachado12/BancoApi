package br.org.fundatec.banco.controller;


import br.org.fundatec.banco.model.Banco;
import br.org.fundatec.banco.service.BancoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="/bancos")
public class BancoController {

    @Autowired
    private BancoService bancoService;

    // GET http://localhost:8080/bancos/listar
    @GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Banco>> get() {
        return ResponseEntity.status(HttpStatus.OK).body(bancoService.listarTodos());
    }

    // GET http://localhost:8080/bancos/find-id/1
    @GetMapping(value = { "/find-id/{id}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Banco> get(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bancoService.buscarPorId(id));
    }

    // GET http://localhost:8080/bancos/consulta-nome?nome=brasil
    @GetMapping(value = { "/consulta-nome" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<Banco>> getPorNome(@RequestParam("nome") String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(bancoService.buscarPorNomeAproximado(nome));
    }

    // GET http://localhost:8080/bancos/consulta-codigo?codigo=001
    @GetMapping(value = { "/consulta-codigo" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Banco> getPorCodigo(@RequestParam("codigo") String codigo) {
        return ResponseEntity.status(HttpStatus.OK).body(bancoService.buscarPorCodigo(codigo));
    }

    // POST http://localhost:8080/bancos/cadastar
    @PostMapping(value = "/cadastrar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Banco> cadastar(@RequestBody @Valid Banco banco) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bancoService.cadastrar(banco));
    }

    // PUT http://localhost:8080/bancos/editar/1
    @PutMapping(value = { "/editar/{id}" }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Banco> editar(@PathVariable("id") Long id, @RequestBody @Valid Banco banco) {
        return ResponseEntity.status(HttpStatus.OK).body(bancoService.editar(id, banco));
    }

    // DELETE http://localhost:8080/bancos/remover/1
    @DeleteMapping(value = { "/remover{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
        bancoService.remover(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

