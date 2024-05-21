package br.com.luan.servicex.exceptios;

public class ExceptionDataIntegrityViolation extends RuntimeException{

    public ExceptionDataIntegrityViolation(String message) {
        super(message);
    }

}

