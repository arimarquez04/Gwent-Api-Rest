package com.bewise.pasantia.local.service;

import com.bewise.pasantia.local.exceptions.JugadorNoExisteException;
import com.bewise.pasantia.local.dto.CartaRequestDto;
import com.bewise.pasantia.local.model.Carta;
import com.bewise.pasantia.local.model.Jugador;

import java.util.List;
import java.util.Optional;

public interface CartaService {
    public List<Carta> traerTodasLasCartas();
    List<Carta>traerTodasLasCartasDeUnJugador(Jugador jugador);

    public Carta guardarCarta(CartaRequestDto carta, Jugador jugador) throws JugadorNoExisteException;
    public void eliminarCarta(Carta carta);
    public Optional<Carta> traerCarta(Carta carta);
    public Optional<Carta> traerCartaPorId(int id);
}
