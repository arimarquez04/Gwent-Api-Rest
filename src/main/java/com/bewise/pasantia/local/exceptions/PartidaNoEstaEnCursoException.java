package com.bewise.pasantia.local.exceptions;

import com.bewise.pasantia.local.model.EstadoPartida;

public class PartidaNoEstaEnCursoException extends Exception{
    public PartidaNoEstaEnCursoException(int partidaId, EstadoPartida estadoPartida){
        super("La partida con id: " + partidaId + " no se encuentra EN_CURSO, estado partida: " + estadoPartida);
    }
}
