package com.bewise.pasantia.local.exceptions;

public class PartidaNoExisteException extends Exception {
    public PartidaNoExisteException() {
        super("No existe la partida solicitada");
    }
    public PartidaNoExisteException(int id) {
        super("No existe la partida solicitada con id: " + id);
    }
}
