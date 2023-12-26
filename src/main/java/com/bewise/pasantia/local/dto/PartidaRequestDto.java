package com.bewise.pasantia.local.dto;

import com.bewise.pasantia.local.model.EstadoPartida;
import com.bewise.pasantia.local.model.Partida;

public class PartidaRequestDto {
    private EstadoPartida estadoPartida;
    private int ronda;
    private int ganadorId;
    private int perdedorId;

    public EstadoPartida getEstadoPartida() {
        return estadoPartida;
    }

    public void setEstadoPartida(EstadoPartida estadoPartida) {
        this.estadoPartida = estadoPartida;
    }

    public int getRonda() {
        return ronda;
    }

    public void setRonda(int ronda) {
        this.ronda = ronda;
    }

    public int getGanadorId() {
        return ganadorId;
    }

    public void setGanadorId(int ganadorId) {
        this.ganadorId = ganadorId;
    }

    public int getPerdedorId() {
        return perdedorId;
    }

    public void setPerdedorId(int perdedorId) {
        this.perdedorId = perdedorId;
    }
    public Partida convert(){
        Partida partida = new Partida();
        partida.setEstadoPartida(estadoPartida);
        partida.setRonda(ronda);
        return partida;
    }
}
