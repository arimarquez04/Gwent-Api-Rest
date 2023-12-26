package com.bewise.pasantia.local.exceptions;

public class JugadorNoExisteException extends Exception {
    public JugadorNoExisteException() {
        super("No existe el jugador solicitado");
    }
    public JugadorNoExisteException(int id) {
        super("No existe el jugador solicitado con id: " + id);
    }
}
