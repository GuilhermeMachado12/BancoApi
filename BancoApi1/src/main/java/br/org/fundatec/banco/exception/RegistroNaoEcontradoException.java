package br.org.fundatec.banco.exception;


public class RegistroNaoEcontradoException extends RuntimeException {

    public RegistroNaoEcontradoException(String mensagem) {
        super(mensagem);
    }
}
