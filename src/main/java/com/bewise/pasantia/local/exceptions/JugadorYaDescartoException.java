package com.bewise.pasantia.local.exceptions;

public class JugadorYaDescartoException extends Exception{
    public JugadorYaDescartoException(int id, boolean descarto){
        super("El jugador con id: " + id + " ya descarto isDescarto()= " + descarto);
    }
}
