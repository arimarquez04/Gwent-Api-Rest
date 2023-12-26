package com.bewise.pasantia.local.service;

import com.bewise.pasantia.local.model.Partida;
import com.bewise.pasantia.local.model.PartidaJugador;
import com.bewise.pasantia.local.model.PartidaRonda;
import org.springframework.stereotype.Service;

@Service
public interface PartidaRondaService {
    PartidaRonda crearPartidaRonda(Partida partida, int numeroRonda, PartidaJugador partidaJugadorUno, PartidaJugador partidaJugadorDos);

    PartidaRonda guardarPartidaRonda(PartidaRonda partidaRonda);

    PartidaRonda traerPartidaRondaDeUnaPartida(Partida partida, int ronda);
}
