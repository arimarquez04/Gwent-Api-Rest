package com.bewise.pasantia.local.controller;

import com.bewise.pasantia.local.exceptions.JugadorNoExisteException;
import com.bewise.pasantia.local.dto.CartaRequestDto;
import com.bewise.pasantia.local.model.Carta;
import com.bewise.pasantia.local.model.Jugador;
import com.bewise.pasantia.local.service.CartaService;
import com.bewise.pasantia.local.service.JugadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carta")
public class CartaController {
    CartaService cartaService;
    JugadorService jugadorService;
    public CartaController(CartaService cartaService, JugadorService jugadorService){
        this.cartaService=cartaService;
        this.jugadorService=jugadorService;
    }
    @GetMapping
    public List<Carta> traerTodasLasCartas(@RequestParam(required = false) Integer jugadorId){
        if (jugadorId==null){
            return cartaService.traerTodasLasCartas();
        }
        try {
            Jugador jugador = traerJugadorPorId(jugadorId);
            return cartaService.traerTodasLasCartasDeUnJugador(jugador);
        }catch (JugadorNoExisteException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }



    @GetMapping("/{id}")
    public Carta traerUnaCartaPorId(@PathVariable int id) {
        Optional<Carta> cartaOptional = cartaService.traerCartaPorId(id);
        if (cartaOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro :(");
        }
        return cartaOptional.get();

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUnaCarta(@PathVariable int id){
        Optional<Carta> cartaOptional = cartaService.traerCartaPorId(id);
        if (cartaOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro :(");
        }
        cartaService.eliminarCarta(cartaOptional.get());
        return ResponseEntity.ok("Carta con id " + id + " eliminada");
    }
    @PostMapping
    public Carta guardarCarta(@RequestBody CartaRequestDto cartaRequestDto){
        try {
            Jugador jugador = traerJugadorPorId(cartaRequestDto.getJugadorId());
            return cartaService.guardarCarta(cartaRequestDto, jugador);
        } catch (JugadorNoExisteException e) {
            System.err.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Carta> guardarCartaModificada(@RequestBody CartaRequestDto cartaRequestDto,
                                                        @PathVariable("id") int id ) {
        Optional<Carta> cartaOptional = cartaService.traerCartaPorId(id);
        if(cartaOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        try {
            Jugador jugador = traerJugadorPorId(cartaRequestDto.getJugadorId());
            cartaService.guardarCarta(cartaRequestDto, jugador);
        } catch (JugadorNoExisteException e) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        return ResponseEntity.ok(cartaOptional.get());
    }

    private Jugador traerJugadorPorId(Integer jugadorId) throws JugadorNoExisteException {
        Optional<Jugador> jugadorOptional = jugadorService.traerJugadorPorId(jugadorId);
        if (jugadorOptional.isEmpty()) {
            throw new JugadorNoExisteException(jugadorId);
        }
        return jugadorOptional.get();
    }
    
}
