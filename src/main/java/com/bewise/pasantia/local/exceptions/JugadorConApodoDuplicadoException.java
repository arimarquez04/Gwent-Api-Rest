package com.bewise.pasantia.local.exceptions;

public class JugadorConApodoDuplicadoException extends Exception{
    public JugadorConApodoDuplicadoException(String apodo){
        super("Apodo:" + apodo + " duplicado.");
    }
}
