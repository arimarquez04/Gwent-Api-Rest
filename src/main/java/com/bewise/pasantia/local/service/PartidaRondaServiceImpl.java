package com.bewise.pasantia.local.service;

import com.bewise.pasantia.local.model.Partida;
import com.bewise.pasantia.local.model.PartidaJugador;
import com.bewise.pasantia.local.model.PartidaRonda;
import com.bewise.pasantia.local.repository.PartidaRondaRepository;
import org.springframework.stereotype.Service;

@Service
public class PartidaRondaServiceImpl implements PartidaRondaService{
    PartidaRondaRepository partidaRondaRepository;
    public PartidaRondaServiceImpl(PartidaRondaRepository partidaRondaRepository){
        this.partidaRondaRepository=partidaRondaRepository;
    }
    @Override
    public PartidaRonda crearPartidaRonda(Partida partida, int numeroRonda, PartidaJugador partidaJugadorUno, PartidaJugador partidaJugadorDos){
        PartidaRonda partidaRonda = new PartidaRonda();
        partidaRonda.setPartida(partida);
        partidaRonda.setNumeroRonda(numeroRonda);
        partidaRonda.setPartidaJugadorUno(partidaJugadorUno);
        partidaRonda.setPartidaJugadordos(partidaJugadorDos);
        return partidaRondaRepository.save(partidaRonda);
    }
    @Override
    public PartidaRonda guardarPartidaRonda(PartidaRonda partidaRonda){
        return partidaRondaRepository.save(partidaRonda);
    }
    @Override
    public PartidaRonda traerPartidaRondaDeUnaPartida(Partida partida, int ronda) {
        return partidaRondaRepository.traerPartidaRondaDeUnaPartida(partida.getId(), ronda);
    }

}
