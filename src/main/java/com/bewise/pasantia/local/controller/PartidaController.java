package com.bewise.pasantia.local.controller;
import com.bewise.pasantia.local.model.*;
import com.bewise.pasantia.local.service.CartaService;
import com.bewise.pasantia.local.service.JugadorService;
import com.bewise.pasantia.local.service.PartidaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/partida")
public class PartidaController {
    private final PartidaService partidaService;
    private final JugadorService jugadorService;
    private final CartaService cartaService;

    public PartidaController(PartidaService partidaService, JugadorService jugadorService, CartaService cartaService) {
        this.partidaService = partidaService;
        this.jugadorService = jugadorService;
        this.cartaService = cartaService;
    }

    @GetMapping
    public List<Partida> traerTodasLasPartidas() {
        return partidaService.traerTodasLasPartida();

    }

    @GetMapping("/{id}")
    public Partida traerUnaPartidaPorId(@PathVariable int id) {
        Optional<Partida> partidaOptional = partidaService.traerPartidaPorId(id);
        if (partidaOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro :(");
        }
        return partidaOptional.get();
    }

    @PostMapping
    public Partida crearUnaPartida(@RequestParam(required = true) Integer jugadorUnoId, @RequestParam(required = true) Integer jugadorDosId) {
        if (jugadorUnoId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (jugadorDosId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Optional<Jugador> jugadorUnoOptional = jugadorService.traerJugadorPorId(jugadorUnoId);
        Optional<Jugador> jugadorDosOptional = jugadorService.traerJugadorPorId(jugadorDosId);
        if (jugadorUnoOptional.isEmpty() || jugadorDosOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Jugador jugadorUno = jugadorUnoOptional.get();
        Jugador jugadorDos = jugadorDosOptional.get();
        return partidaService.crearPartida(jugadorUno, jugadorDos);
    }

    @GetMapping("/{partidaId}/jugador/{jugadorId}")
    public List<Carta> traerTodasLasCartaPartidaJugadorDeUnJugadorEnPartida(@PathVariable int partidaId,
                                                                            @PathVariable int jugadorId) {
        Optional<Partida> partidaOptional = partidaService.traerPartidaPorId(partidaId);
        Optional<Jugador> jugadorOptional = jugadorService.traerJugadorPorId(jugadorId);
        if (partidaOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (jugadorOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Partida partida = partidaOptional.get();
        Jugador jugador = jugadorOptional.get();
        try {
            return partidaService.obtenerCartasPartidaJugadorDeUnJugadorEnPartida(partida, jugador)
                    .stream().map(cpj -> cpj.getCarta()).toList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{partidaId}/jugador/{jugadorId}/mazo")
    public List<Carta> traerCartaPartidaJugadorQueEstenEnElMazoDeUnJugadorEnPartida(@PathVariable int partidaId,
                                                                                    @PathVariable int jugadorId) {
        Optional<Partida> partidaOptional = partidaService.traerPartidaPorId(partidaId);
        Optional<Jugador> jugadorOptional = jugadorService.traerJugadorPorId(jugadorId);
        if (partidaOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (jugadorOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Partida partida = partidaOptional.get();
        Jugador jugador = jugadorOptional.get();
        try {
            return partidaService.obtenerCartasEnMazoDeUnJugadorEnPartida(partida, jugador);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{partidaId}/jugador/{jugadorId}/baraja")
    public List<Carta> traerCartaPartidaJugadorQueEstenEnLaBarajaDeUnJugadorEnPartida(@PathVariable int partidaId,
                                                                                      @PathVariable int jugadorId) {
        Optional<Partida> partidaOptional = partidaService.traerPartidaPorId(partidaId);
        Optional<Jugador> jugadorOptional = jugadorService.traerJugadorPorId(jugadorId);
        if (partidaOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (jugadorOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Partida partida = partidaOptional.get();
        Jugador jugador = jugadorOptional.get();
        try {
            return partidaService.obtenerCartasEnBarajaDeUnJugadorEnPartida(partida, jugador);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{partidaId}/jugador/{jugadorId}/cementerio")
    public List<Carta> traerCartaPartidaJugadorQueEstenEnElCementerioDeUnJugadorEnPartida(@PathVariable int partidaId,
                                                                                      @PathVariable int jugadorId) {
        Optional<Partida> partidaOptional = partidaService.traerPartidaPorId(partidaId);
        Optional<Jugador> jugadorOptional = jugadorService.traerJugadorPorId(jugadorId);
        if (partidaOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (jugadorOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Partida partida = partidaOptional.get();
        Jugador jugador = jugadorOptional.get();
        try {
            return partidaService.obtenerCartasEnCementerioDeUnJugadorEnPartida(partida,jugador);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/{partidaId}/jugador/{jugadorId}/mazo")
    public List<Carta> prepararCartasPartidaJugadorDeUnJugadorEnPartida(@PathVariable int partidaId,
                                                                        @PathVariable int jugadorId) {
        Optional<Partida> partidaOptional = partidaService.traerPartidaPorId(partidaId);
        Optional<Jugador> jugadorOptional = jugadorService.traerJugadorPorId(jugadorId);
        if (partidaOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (jugadorOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Partida partida = partidaOptional.get();
        Jugador jugador = jugadorOptional.get();
        try {
            return partidaService.prepararMazo(partida, jugador, jugador.getCartas());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{partidaId}/jugador/{jugadorId}/baraja")
    public List<Carta> descartarCartasDeLaBarajaDeUnJugador(@PathVariable int partidaId, @PathVariable int jugadorId,
                                                            @RequestParam(required = false) Integer cartaUnoId,
                                                            @RequestParam(required = false) Integer cartaDosId,
                                                            @RequestParam(required = false) Boolean descartar){

        Optional<Partida> partidaOptional = partidaService.traerPartidaPorId(partidaId);
        Optional<Jugador> jugadorOptional = jugadorService.traerJugadorPorId(jugadorId);

        if (cartaUnoId==null && cartaDosId==null && descartar==null){

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Optional<Carta> cartaUnoOptional = Optional.empty();
        Optional<Carta> cartaDosOptional = Optional.empty();

        if (partidaOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (jugadorOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Jugador jugador = jugadorOptional.get();
        Partida partida = partidaOptional.get();
        if (cartaUnoId==null && cartaDosId!=null){

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (cartaUnoId!=null) {
            cartaUnoOptional = cartaService.traerCartaPorId(cartaUnoId);
        }
        if(cartaDosId!=null){
            cartaDosOptional = cartaService.traerCartaPorId(cartaDosId);
        }

        try {
            return partidaService.descarte(partida, jugador, cartaUnoOptional, cartaDosOptional, descartar);
        }catch (Exception e){
            System.err.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{partidaId}/jugador/{jugadorId}/baraja/{cartaId}")
    public List<Carta> tirarCartaDeUnJugadorEnPartida(@PathVariable int partidaId, @PathVariable int jugadorId, @PathVariable int cartaId) {
        Optional<Partida> partidaOptional = partidaService.traerPartidaPorId(partidaId);
        Optional<Jugador> jugadorOptional = jugadorService.traerJugadorPorId(jugadorId);
        Optional<Carta> cartaOptional = cartaService.traerCartaPorId(cartaId);
        if (partidaOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (jugadorOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (cartaOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Carta carta = cartaOptional.get();
        Jugador jugador = jugadorOptional.get();
        Partida partida = partidaOptional.get();
        try {
            return partidaService.tirarCarta(partida, carta, jugador);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{partidaId}/jugador/{jugadorId}/turno")
    public Partida jugadorPasaSuTurno(@PathVariable int partidaId, @PathVariable int jugadorId){
        Optional<Partida> partidaOptional = partidaService.traerPartidaPorId(partidaId);
        Optional<Jugador> jugadorOptional = jugadorService.traerJugadorPorId(jugadorId);
        if (partidaOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (jugadorOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Partida partida = partidaOptional.get();
        Jugador jugador = jugadorOptional.get();
        try {
            return partidaService.jugadorPasaSuTurno(partida, jugador);
        }catch (Exception e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
