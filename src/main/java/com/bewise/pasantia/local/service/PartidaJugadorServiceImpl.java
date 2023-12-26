package com.bewise.pasantia.local.service;

import com.bewise.pasantia.local.model.Jugador;
import com.bewise.pasantia.local.model.Partida;
import com.bewise.pasantia.local.model.PartidaJugador;
import com.bewise.pasantia.local.repository.PartidaJugadorRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartidaJugadorServiceImpl implements PartidaJugadorService {
    private final PartidaJugadorRepository partidaJugadorRepository;
    public PartidaJugadorServiceImpl(PartidaJugadorRepository partidaJugadorRepository){
        this.partidaJugadorRepository=partidaJugadorRepository;
    }
    @Override
    public PartidaJugador crearPartidaJugador(Partida partida, Jugador jugador, boolean turno) {
        PartidaJugador partidaJugador = new PartidaJugador();
        partidaJugador.setPartida(partida);
        partidaJugador.setJugador(jugador);
        partidaJugador.setPaso(false);
        partidaJugador.setTurno(turno);
        partidaJugador.setVida(2);
        partidaJugador.setDescarto(false);
        partidaJugador.setPreparoMazo(false);
        return partidaJugadorRepository.save(partidaJugador);
    }
    @Override
    public PartidaJugador guardarPartidaJugador(PartidaJugador partidaJugador){
        return partidaJugadorRepository.save(partidaJugador);
    }
    @Override
    public List<PartidaJugador> obtenerPartidasJugadorDeUnaPartida(Partida partida){
        return partidaJugadorRepository.traerPartidasJugadorDeUnaPartida(partida.getId());
    }
}
