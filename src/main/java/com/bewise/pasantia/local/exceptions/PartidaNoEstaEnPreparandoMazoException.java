package com.bewise.pasantia.local.exceptions;

import com.bewise.pasantia.local.model.EstadoPartida;

public class PartidaNoEstaEnPreparandoMazoException extends Exception{
    public PartidaNoEstaEnPreparandoMazoException(int partidaId, EstadoPartida estadoPartida){
        super("La partida con id: " + partidaId + " no se encuentra en PREPARANDO_MAZO, estado partida: " + estadoPartida);
    }
}
