package com.bewise.pasantia.local.service;

import com.bewise.pasantia.local.dto.CartaRequestDto;
import com.bewise.pasantia.local.model.Carta;
import com.bewise.pasantia.local.model.Jugador;
import com.bewise.pasantia.local.repository.CartaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartaServiceImpl implements CartaService {

    private final CartaRepository cartaRespository;


    public CartaServiceImpl(CartaRepository cartaRespository) {
        this.cartaRespository = cartaRespository;

    }


    @Override
    public List<Carta> traerTodasLasCartas() {
        return cartaRespository.findAll();
    }
    @Override
    public  List<Carta>traerTodasLasCartasDeUnJugador(Jugador jugador){
        return cartaRespository.traerCartasDeUnJugador(jugador.getId());
    }
    @Override
    public Carta guardarCarta(CartaRequestDto cartaRequestDto, Jugador jugador){
        Carta carta = cartaRequestDto.convert();
        carta.setJugador(jugador);
        return cartaRespository.save(carta);
    }

    @Override
    public void eliminarCarta(Carta carta) {
        cartaRespository.deleteById(carta.getId());
    }



    @Override
    public  Optional<Carta> traerCarta(Carta carta) {
        return traerCartaPorId(carta.getId());

    }
    @Override
    public Optional<Carta> traerCartaPorId(int id) {
        return cartaRespository.findById(id);

    }
}
