package com.bewise.pasantia.local.service;

import com.bewise.pasantia.local.model.Carta;
import com.bewise.pasantia.local.model.CartaPartidaJugador;
import com.bewise.pasantia.local.model.PartidaJugador;
import com.bewise.pasantia.local.model.UbicacionCarta;
import com.bewise.pasantia.local.repository.CartaPartidaJugadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaPartidaJugadorServiceImpl implements CartaPartidaJugadorService {
    private final CartaPartidaJugadorRepository cartaPartidaJugadorRepository;

    public CartaPartidaJugadorServiceImpl(CartaPartidaJugadorRepository partidaJugadorRepository) {
        this.cartaPartidaJugadorRepository = partidaJugadorRepository;
    }

    @Override
    public void saveCartasPartidaJugador(List<CartaPartidaJugador> cartaPartidaJugadores) {
        for (CartaPartidaJugador c : cartaPartidaJugadores) {
            cartaPartidaJugadorRepository.save(c);
        }
    }

    @Override
    public CartaPartidaJugador crearCartaPartidaJugador(PartidaJugador partidaJugador, Carta carta, UbicacionCarta ubicacionCarta) {
        CartaPartidaJugador cartaPartidaJugador = new CartaPartidaJugador();
        cartaPartidaJugador.setPartidaJugador(partidaJugador);
        cartaPartidaJugador.setCarta(carta);
        cartaPartidaJugador.setUbicacionCarta(ubicacionCarta);
        cartaPartidaJugadorRepository.save(cartaPartidaJugador);
        return cartaPartidaJugador;
    }

    @Override
    public void guardarCartaPartidaJugador(CartaPartidaJugador cartaPartidaJugador) {
        cartaPartidaJugadorRepository.save(cartaPartidaJugador);
    }

    @Override
    public void guardarCartaPartidaJugador(List<CartaPartidaJugador> cartaPartidaJugadores) {
        for (CartaPartidaJugador c : cartaPartidaJugadores){
            cartaPartidaJugadorRepository.save(c);
        }
    }

    @Override
    public List<CartaPartidaJugador> traerTodasLasCartaPartidaJugadorDeUnaPartidaJugador(PartidaJugador partidaJugador) {
        return cartaPartidaJugadorRepository.traerCartaPartidaJugadorDeUnaPartidaJugador(partidaJugador.getId());
    }
    @Override
    public CartaPartidaJugador traerCartaPartidaJugadorDeUnaCarta(Carta carta, PartidaJugador partidaJugador) {
        return cartaPartidaJugadorRepository.traerCartaPartidaJugadorDeUnaCarta(carta.getId(), partidaJugador.getId());
    }
    @Override
    public List<CartaPartidaJugador> traerCPJDeUnaPartidaJugadorEnUnaUbicacionDeterminada
            (PartidaJugador partidaJugador, UbicacionCarta ubicacionCarta){
        return cartaPartidaJugadorRepository.
                traerCartasPartidaJugadorEnUbicacionDeterminada(partidaJugador.getId(), ubicacionCarta.toString());
    }
}
