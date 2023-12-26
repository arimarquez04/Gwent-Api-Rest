package com.bewise.pasantia.local.exceptions;

import com.bewise.pasantia.local.model.EstadoPartida;

public class PartidaNoEstaEnDescarteException extends Exception{
    public PartidaNoEstaEnDescarteException(int partidaId, EstadoPartida estadoPartida){
        super("La partida con id: " + partidaId + " no se encuentra en DESCARTE, estado partida: " + estadoPartida);
    }
}
