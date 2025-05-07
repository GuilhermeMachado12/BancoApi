package br.org.fundatec.banco.controller;

import br.org.fundatec.banco.model.Conta;
import br.org.fundatec.banco.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    // POST  http://localhost:8080/contas/cadastrar
    @PostMapping(value = "/cadastrar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Conta> cadastrar(@RequestBody Conta conta) {
        Conta novaConta = contaService.cadastrar(conta);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaConta);
    }

    // DELETE http://localhost:8080/contas/remover/1
    @DeleteMapping(value = "/remover/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
        contaService.remover(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    // GET http://localhost:8080/contas/por-numero/1234
    @GetMapping(value = "/por-numero/{numero}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Conta> buscarPorNumero(@PathVariable("numero") Integer numero) {
        return ResponseEntity.status(HttpStatus.OK).body(contaService.buscarPorNumero(numero));
    }

    // PUT http://localhost:8080/contas/sacar?numero=1234&valor=100.0
    @PutMapping(value = "/sacar/{numero}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Conta> sacar(@PathVariable("numero") Integer numero,
                                       @RequestParam("valor") Double valor) {
        return ResponseEntity.status(HttpStatus.OK).body(contaService.sacar(numero, valor));
    }

    @PutMapping(value = "/depositar/{numero}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Conta> depositar(@PathVariable("numero") Integer numero,
                                           @RequestParam("valor") Double valor) {
        return ResponseEntity.status(HttpStatus.OK).body(contaService.depositar(numero, valor));
    }
}
