package com.bewise.pasantia.local.service;

import com.bewise.pasantia.local.dto.JugadorRequestDto;
import com.bewise.pasantia.local.exceptions.JugadorConApodoDuplicadoException;
import com.bewise.pasantia.local.exceptions.NoSePuedeBorrarJugadorConCartasException;
import com.bewise.pasantia.local.model.Jugador;

import java.util.List;
import java.util.Optional;

public interface JugadorService {
    List<Jugador> traerTodosLosJugadores();

    Jugador guardarJugador(JugadorRequestDto jugadorRequestDto) throws JugadorConApodoDuplicadoException;

    void eliminarJugador(Jugador jugador) throws NoSePuedeBorrarJugadorConCartasException;

    Optional<Jugador> traerJugador(Jugador jugador);

    Optional<Jugador> traerOptionarJugadorPorApodo(String apodo);

    Optional<Jugador> traerJugadorPorId(int id);
}
