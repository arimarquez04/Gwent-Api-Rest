package com.bewise.pasantia.local.service;

import com.bewise.pasantia.local.model.Jugador;
import com.bewise.pasantia.local.model.Partida;
import com.bewise.pasantia.local.model.PartidaJugador;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PartidaJugadorService {


    PartidaJugador crearPartidaJugador(Partida partida, Jugador jugador, boolean turno);

    PartidaJugador guardarPartidaJugador(PartidaJugador partidaJugador);

    List<PartidaJugador> obtenerPartidasJugadorDeUnaPartida(Partida partida);
}
