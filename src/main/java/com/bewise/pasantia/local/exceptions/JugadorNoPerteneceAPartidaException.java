package com.bewise.pasantia.local.exceptions;

public class JugadorNoPerteneceAPartidaException extends Exception{
    public JugadorNoPerteneceAPartidaException(int partidaId, int jugadorId){
        super("El jugador con id " + jugadorId + " no pertenece a la partida con id " + partidaId);
    }
}
