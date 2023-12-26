package com.bewise.pasantia.local.service;

import com.bewise.pasantia.local.exceptions.*;
import com.bewise.pasantia.local.model.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface PartidaService {
    public List<Partida> traerTodasLasPartida();

    public Optional<Partida> traerPartidaPorId(int id);

    List<PartidaJugador> obtenerPartidasJugadorDeUnaPartida(Partida partida);



    List<CartaPartidaJugador> obtenerCartasPartidaJugadorDeUnJugadorEnPartida(Partida partida, Jugador jugador)
            throws JugadorNoPerteneceAPartidaException;

    List<Carta> obtenerCartasEnMazoDeUnJugadorEnPartida(Partida partida, Jugador jugador) throws JugadorNoPerteneceAPartidaException;

    List<Carta> obtenerCartasEnBarajaDeUnJugadorEnPartida(Partida partida, Jugador jugador)
            throws JugadorNoPerteneceAPartidaException;

    List<Carta> obtenerCartasEnCementerioDeUnJugadorEnPartida(Partida partida, Jugador jugador)
            throws JugadorNoPerteneceAPartidaException;

    public Partida crearPartida(Jugador jugadorUno, Jugador jugadorDos);

    List<Carta> tirarCarta(Partida partida, Carta carta, Jugador jugador)
            throws CartaNoPerteneceAJugadorException, CartaNoSeEncuentraEnElMazoException, JugadorNoLeTocaJugarException,
            JugadorNoJuegaPorquePasoException, JugadorNoPerteneceAPartidaException, PartidaNoEstaEnCursoException, CartaNoSeEncuentraEnBarajaException, NoSePuedeEnviarCartasAlCementerioPorqueUnJugadorNoPasoException, NoSePuedeEnviarCartasAlCementerioPorqueLosJugadoresNoPasaronException, PartidaRondaNoPerteneceAPartida, PartidaRondaNoCoincideConRondaDePartidaException;

    Partida verificarEmpezarPartida(Partida partida,Jugador jugador);

    public List<Carta> prepararMazo(Partida partida, Jugador jugador, List<Carta> cartas)
            throws CartasInsufucientesException, CartaNoPerteneceAJugadorException, PartidaNoEstaEnPreparandoMazoException, JugadorYaPreparoMazoException, JugadorNoPerteneceAPartidaException;

    List<Carta> noDescartarCartas(Partida partida, Jugador jugador) throws JugadorNoPerteneceAPartidaException, JugadorNoLeTocaJugarException;

    List<Carta> descarteDosCartas(Partida partida, Jugador jugador, Carta cartaUno, Carta cartaDos)
            throws CartaADescartarNoSeEncuentraEnBarajaException, JugadorNoPerteneceAPartidaException, CartaNoPerteneceAJugadorException;

    List<Carta> descarteUnaCarta(Partida partida, Jugador jugador, Carta carta)
            throws CartaADescartarNoSeEncuentraEnBarajaException, JugadorNoPerteneceAPartidaException,
            CartaNoPerteneceAJugadorException;


    Partida jugadorPasaSuTurno(Partida partida, Jugador jugador) throws JugadorNoPerteneceAPartidaException, JugadorNoLeTocaJugarException, PartidaNoEstaEnCursoException, PartidaRondaNoPerteneceAPartida, PartidaRondaNoCoincideConRondaDePartidaException, NoSePuedeEnviarCartasAlCementerioPorqueUnJugadorNoPasoException, NoSePuedeEnviarCartasAlCementerioPorqueLosJugadoresNoPasaronException;

    boolean jugadorPerteneceAPartida(Jugador jugador, Partida partida);

    PartidaJugador obtenerPartidaJugadorDeUnJugadorEnPartida(Partida partida, Jugador jugador);

    PartidaJugador obtenerPartidaJugadorDelOtroJugadorEnPartida(Partida partida, Jugador jugador);

    List<Carta> descarte(Partida partida, Jugador jugador, Optional<Carta> cartaUnoOptional, Optional<Carta> cartaDosOptional, Boolean noTirar) throws JugadorNoPerteneceAPartidaException, CartaADescartarNoSeEncuentraEnBarajaException, CartaNoPerteneceAJugadorException, PartidaNoEstaEnDescarteException, JugadorNoLeTocaJugarException, JugadorYaDescartoException;

    Partida enviarCartasAlCementerioYContarPuntajeDeRonda(Partida partida, Jugador jugador) throws PartidaNoEstaEnCursoException, NoSePuedeEnviarCartasAlCementerioPorqueLosJugadoresNoPasaronException, NoSePuedeEnviarCartasAlCementerioPorqueUnJugadorNoPasoException;

    Partida verficarGanador(Partida partida)
            throws PartidaRondaNoPerteneceAPartida, PartidaRondaNoCoincideConRondaDePartidaException;
}
