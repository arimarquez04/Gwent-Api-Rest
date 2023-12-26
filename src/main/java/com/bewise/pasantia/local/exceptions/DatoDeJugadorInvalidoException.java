package com.bewise.pasantia.local.exceptions;

public class DatoDeJugadorInvalidoException extends Exception{
    public DatoDeJugadorInvalidoException(){
        super("JugadorRequestDto recibio un dato invalido");
    }
}
