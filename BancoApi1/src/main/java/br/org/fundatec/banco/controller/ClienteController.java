package br.org.fundatec.banco.controller;

import br.org.fundatec.banco.model.Cliente;
import br.org.fundatec.banco.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // POST http://localhost:8080/clientes/cadastrar
    @PostMapping(value = "/cadastrar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> cadastrar(@RequestBody @Valid Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.cadastrar(cliente));
    }

    // DELETE http://localhost:8080/clientes/remover/1
    @DeleteMapping(value = "/remover/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
        clienteService.remover(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // GET http://localhost:8080/clientes/find-id/1
    @GetMapping(value = "/find-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.buscarPorId(id));
    }

    // PUT http://localhost:8080/clientes/editar/1
    @PutMapping(value = "/editar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> editar(@PathVariable("id") Long id, @RequestBody @Valid Cliente cliente) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.editar(id, cliente));
    }

    // GET http://localhost:8080/clientes/consulta-nome?nome=João
    @GetMapping(value = "/consulta-nome", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cliente>> buscarPorNome(@RequestParam("nome") String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.buscarPorNomeAproximado(nome));
    }
}

