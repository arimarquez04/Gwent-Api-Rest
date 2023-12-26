package com.bewise.pasantia.local.exceptions;

public class NoSePuedeBorrarJugadorConCartasException extends Exception{
    public NoSePuedeBorrarJugadorConCartasException() {
        super("El jugador que se intenta borrar tiene cartas asignadas");
    }
    public NoSePuedeBorrarJugadorConCartasException(int id) {
        super("El jugador con id: " + id + " no se puede borrar porque tiene cartas asignadas");
    }
}
