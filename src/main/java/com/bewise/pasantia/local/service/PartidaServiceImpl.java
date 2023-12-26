package com.bewise.pasantia.local.service;

import com.bewise.pasantia.local.exceptions.*;
import com.bewise.pasantia.local.model.*;
import com.bewise.pasantia.local.repository.PartidaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PartidaServiceImpl implements PartidaService {
    private final PartidaRepository partidaRepository;
    private final PartidaJugadorService partidaJugadorService;
    private final CartaPartidaJugadorService cartaPartidaJugadorService;
    private final PartidaRondaService partidaRondaService;
    private static final  int CARTAS_DE_BARAJA = 10;
    private static final  int MINIMO_DE_CARTAS = 15;
    private static final  int DESCARTAR_DOS_CARTAS = 2;
    private static final  int DESCARTAR_UNA_CARTA = 1;

    public PartidaServiceImpl(PartidaRepository partidaRepository, PartidaJugadorService partidaJugadorService,
                              CartaPartidaJugadorService cartaPartidaJugadorService, PartidaRondaService partidaRondaService) {
        this.partidaRepository = partidaRepository;
        this.partidaJugadorService = partidaJugadorService;
        this.cartaPartidaJugadorService = cartaPartidaJugadorService;
        this.partidaRondaService = partidaRondaService;
    }

    @Override
    public List<Partida> traerTodasLasPartida() {
        return partidaRepository.findAll();
    }

    @Override
    public Optional<Partida> traerPartidaPorId(int id) {
        return partidaRepository.findById(id);
    }

    @Override
    public List<CartaPartidaJugador> obtenerCartasPartidaJugadorDeUnJugadorEnPartida(Partida partida, Jugador jugador)
            throws JugadorNoPerteneceAPartidaException {
        if (!jugadorPerteneceAPartida(jugador, partida)){
            throw new JugadorNoPerteneceAPartidaException(partida.getId(), jugador.getId());
        }
        PartidaJugador partidaJugador = obtenerPartidaJugadorDeUnJugadorEnPartida(partida, jugador);
        return cartaPartidaJugadorService.traerTodasLasCartaPartidaJugadorDeUnaPartidaJugador(partidaJugador);
    }
    @Override
    public List<Carta> obtenerCartasEnMazoDeUnJugadorEnPartida(Partida partida, Jugador jugador)
            throws JugadorNoPerteneceAPartidaException {

        List<CartaPartidaJugador> cartasPartidaJugador = obtenerCartasPartidaJugadorDeUnJugadorEnPartida(partida, jugador);

        return cartasPartidaJugador.stream().filter(cpj -> cpj.getUbicacionCarta().equals(UbicacionCarta.MAZO))
                .map(cpj -> cpj.getCarta()).toList();
    }
    @Override
    public List<Carta> obtenerCartasEnBarajaDeUnJugadorEnPartida(Partida partida, Jugador jugador)
            throws JugadorNoPerteneceAPartidaException {

        List<CartaPartidaJugador> cartasPartidaJugador = obtenerCartasPartidaJugadorDeUnJugadorEnPartida(partida, jugador);

        return cartasPartidaJugador.stream().filter(cpj -> cpj.getUbicacionCarta().equals(UbicacionCarta.BARAJA))
                .map(cpj -> cpj.getCarta()).toList();
    }
    @Override
    public List<Carta> obtenerCartasEnCementerioDeUnJugadorEnPartida(Partida partida, Jugador jugador)
            throws JugadorNoPerteneceAPartidaException {

        List<CartaPartidaJugador> cartasPartidaJugador = obtenerCartasPartidaJugadorDeUnJugadorEnPartida(partida, jugador);

        return cartasPartidaJugador.stream().filter(cpj -> cpj.getUbicacionCarta().equals(UbicacionCarta.CEMENTERIO))
                .map(cpj -> cpj.getCarta()).toList();
    }
    @Override
    public Partida crearPartida(Jugador jugadorUno, Jugador jugadorDos) {
        Partida partida = new Partida();
        partida.setEstadoPartida(EstadoPartida.PREPARANDO_MAZO);
        partida.setRonda(0);
        Random random = new Random();
        int numeroElegido = random.nextInt(2);
        PartidaJugador partidaJugadorUno = new PartidaJugador();
        PartidaJugador partidaJugadorDos = new PartidaJugador();
        if(numeroElegido==0){
            //crear partida_jugador para el jugador uno con turno true de comienzo
            partidaJugadorUno = partidaJugadorService.crearPartidaJugador(partida, jugadorUno, true);
            //crear partida_jugador para el jugador dos con turno false de comienzo
            partidaJugadorDos = partidaJugadorService.crearPartidaJugador(partida, jugadorDos, false);
        }
        if (numeroElegido==1){
            //crear partida_jugador para el jugador uno con turno false de comienzo
            partidaJugadorUno = partidaJugadorService.crearPartidaJugador(partida, jugadorUno, false);
            //crear partida_jugador para el jugador dos con turno true de comienzo
            partidaJugadorDos = partidaJugadorService.crearPartidaJugador(partida, jugadorDos, true);
        }
        partida.agregarPartidaJugador(partidaJugadorUno);
        partida.agregarPartidaJugador(partidaJugadorDos);

        partidaRepository.save(partida);
        return partida;
    }

    @Override
    public List<Carta> prepararMazo(Partida partida, Jugador jugador, List<Carta> cartas)
            throws CartasInsufucientesException, CartaNoPerteneceAJugadorException,
            PartidaNoEstaEnPreparandoMazoException, JugadorYaPreparoMazoException, JugadorNoPerteneceAPartidaException {

        if(!jugadorPerteneceAPartida(jugador, partida)){
            throw new JugadorNoPerteneceAPartidaException(partida.getId(), jugador.getId());
        }
        if (!partida.getEstadoPartida().equals(EstadoPartida.PREPARANDO_MAZO)) {
            throw new PartidaNoEstaEnPreparandoMazoException(partida.getId(), partida.getEstadoPartida());
        }
        boolean cartasNoPertenecen = cartas.stream().
                anyMatch(carta -> !carta.getJugador()
                        .equals(jugador));
        if (cartasNoPertenecen) {
            throw new CartaNoPerteneceAJugadorException();
        }
        PartidaJugador partidaJugador = obtenerPartidaJugadorDeUnJugadorEnPartida(partida, jugador);
        if (partidaJugador.isPreparoMazo()){
            throw new JugadorYaPreparoMazoException(jugador.getId(), partidaJugador.isPreparoMazo());
        }
        if (cartas.size() < MINIMO_DE_CARTAS) {
            throw new CartasInsufucientesException(jugador.getId());
        }

        Random random = new Random();
        List<Carta> cartasElegidas = new ArrayList<>();
        for (int i = 0; i < CARTAS_DE_BARAJA; i++) {
            int numeroElegido = random.nextInt(cartas.size());
            Carta cartaElegida = cartas.get(numeroElegido);
            cartasElegidas.add(cartaElegida);
            cartas.remove(cartaElegida);
        }

        List<CartaPartidaJugador> cartasPartidaJugador = new ArrayList<>();
        cartasPartidaJugador.addAll(cartas.stream()
                .map(c -> Carta.createFromCarta(c, partidaJugador, UbicacionCarta.MAZO))
                .toList());
        cartasPartidaJugador.addAll(cartasElegidas.stream()
                .map(c -> Carta.createFromCarta(c, partidaJugador, UbicacionCarta.BARAJA))
                .toList());


        cartaPartidaJugadorService.saveCartasPartidaJugador(cartasPartidaJugador);
        partidaJugador.setPreparoMazo(true);
        partidaJugadorService.guardarPartidaJugador(partidaJugador);

        PartidaJugador partidaJugadorDos = obtenerPartidaJugadorDelOtroJugadorEnPartida(partida,jugador);
        if (partidaJugador.isPreparoMazo() && partidaJugadorDos.isPreparoMazo() &&
                partida.getEstadoPartida().equals(EstadoPartida.PREPARANDO_MAZO)){

            partida.setEstadoPartida(EstadoPartida.DESCARTE);
            partidaRepository.save(partida);
        }

        return cartasElegidas;
    }

    @Override
    public List<Carta> descarte(Partida partida, Jugador jugador, Optional<Carta> cartaUnoOptional,
                                Optional<Carta> cartaDosOptional, Boolean descartar)
            throws JugadorNoPerteneceAPartidaException, CartaADescartarNoSeEncuentraEnBarajaException,
            CartaNoPerteneceAJugadorException, PartidaNoEstaEnDescarteException, JugadorYaDescartoException {
        if (!jugadorPerteneceAPartida(jugador, partida)){
            throw new JugadorNoPerteneceAPartidaException(partida.getId(), jugador.getId());
        }
        if (!partida.getEstadoPartida().equals(EstadoPartida.DESCARTE)){
            throw new PartidaNoEstaEnDescarteException(partida.getId(),partida.getEstadoPartida());
        }
        PartidaJugador partidaJugadorUno = obtenerPartidaJugadorDeUnJugadorEnPartida(partida, jugador);
        if (partidaJugadorUno.isDescarto()){
            throw new JugadorYaDescartoException(jugador.getId(), partidaJugadorUno.isDescarto());
        }
        PartidaJugador partidaJugadorDos = obtenerPartidaJugadorDelOtroJugadorEnPartida(partida, jugador);

        if (descartar!=Boolean.TRUE){
            return noDescartarCartas(partida, jugador);
        }

        if (cartaUnoOptional.isPresent() && cartaDosOptional.isEmpty()){
            Carta cartaUno = cartaUnoOptional.get();
            return descarteUnaCarta(partida, jugador, cartaUno);
        }
        if (cartaUnoOptional.isEmpty() && cartaDosOptional.isPresent()){
            Carta cartaDos = cartaDosOptional.get();
            return descarteUnaCarta(partida, jugador, cartaDos);
        }
        if (cartaUnoOptional.isEmpty()&&cartaDosOptional.isEmpty()){
            throw new CartaADescartarNoSeEncuentraEnBarajaException();
        }
        //llegado a este punto, ambas cartas se descartan
        Carta cartaUno = cartaUnoOptional.get();
        Carta cartaDos = cartaDosOptional.get();
        return descarteDosCartas(partida, jugador, cartaUno, cartaDos);
    }

    @Override
    public List<Carta> noDescartarCartas(Partida partida, Jugador jugador) {
        PartidaJugador partidaJugador = obtenerPartidaJugadorDeUnJugadorEnPartida(partida, jugador);
        partidaJugador.setDescarto(true);
        partidaJugadorService.guardarPartidaJugador(partidaJugador);

        verificarEmpezarPartida(partida, jugador);

        return cartaPartidaJugadorService.traerTodasLasCartaPartidaJugadorDeUnaPartidaJugador(partidaJugador)
                .stream().filter(cpj->cpj.getUbicacionCarta().equals(UbicacionCarta.BARAJA))
                .map(cpj->cpj.getCarta()).toList();
    }
    @Override
    public List<Carta> descarteDosCartas(Partida partida, Jugador jugador, Carta cartaUno, Carta cartaDos)
            throws CartaADescartarNoSeEncuentraEnBarajaException, CartaNoPerteneceAJugadorException {
        PartidaJugador partidaJugador = obtenerPartidaJugadorDeUnJugadorEnPartida(partida, jugador);
        List<CartaPartidaJugador> cartaPartidaJugadores = cartaPartidaJugadorService
                .traerTodasLasCartaPartidaJugadorDeUnaPartidaJugador(partidaJugador);

        List<CartaPartidaJugador> cartasEnBaraja = cartaPartidaJugadores.stream()
                .filter(cpj -> cpj.getUbicacionCarta().equals(UbicacionCarta.BARAJA))
                .collect(Collectors.toList());

        List<CartaPartidaJugador> cartasEnMazo = cartaPartidaJugadores.stream()
                .filter(cpj -> cpj.getUbicacionCarta().equals(UbicacionCarta.MAZO))
                .collect(Collectors.toList());

        boolean noEstan = cartaPartidaJugadores.stream()
                .filter(cpj -> cpj.getCarta().equals(cartaUno) || cpj.getCarta().equals(cartaDos))
                .count() != 2;
        if (noEstan) {
            throw new CartaADescartarNoSeEncuentraEnBarajaException();
        }
        if (!cartaUno.getJugador().equals(jugador)){
            throw new CartaNoPerteneceAJugadorException(jugador.getId(), cartaUno.getId());
        }
        if (!cartaDos.getJugador().equals(jugador)){
            throw new CartaNoPerteneceAJugadorException(jugador.getId(), cartaDos.getId());
        }

        Random random = new Random();
        for (int i = 0; i < DESCARTAR_DOS_CARTAS; i++) {
            int numeroElegido = random.nextInt(cartasEnMazo.size());
            CartaPartidaJugador cartaElegida = cartasEnMazo.get(numeroElegido);
            cartasEnMazo.remove(cartaElegida);
            cartaElegida.setUbicacionCarta(UbicacionCarta.BARAJA);
            cartasEnBaraja.add(cartaElegida);
        }

        CartaPartidaJugador cartaPartidaJugadorUno = cartaPartidaJugadorService.traerCartaPartidaJugadorDeUnaCarta(cartaUno, partidaJugador);
        CartaPartidaJugador cartaPartidaJugadorDos = cartaPartidaJugadorService.traerCartaPartidaJugadorDeUnaCarta(cartaDos, partidaJugador);
        //quitar ambas cartas de la baraja y agregarlas al mazo
        cartasEnBaraja.remove(cartaPartidaJugadorUno);
        cartasEnBaraja.remove(cartaPartidaJugadorDos);

        cartaPartidaJugadorUno.setUbicacionCarta(UbicacionCarta.MAZO);
        cartaPartidaJugadorDos.setUbicacionCarta(UbicacionCarta.MAZO);

        cartasEnMazo.add(cartaPartidaJugadorUno);
        cartasEnMazo.add(cartaPartidaJugadorDos);


        partidaJugador.setDescarto(true);
        partidaJugadorService.guardarPartidaJugador(partidaJugador);
        cartaPartidaJugadorService.guardarCartaPartidaJugador(cartasEnMazo);
        cartaPartidaJugadorService.guardarCartaPartidaJugador(cartasEnBaraja);

        verificarEmpezarPartida(partida, jugador);
        return cartasEnBaraja.stream().map(cpj-> cpj.getCarta()).toList();
    }
    @Override
    public List<Carta> descarteUnaCarta(Partida partida, Jugador jugador, Carta carta)
            throws CartaADescartarNoSeEncuentraEnBarajaException, CartaNoPerteneceAJugadorException {

        if (!carta.getJugador().equals(jugador)){
            throw new CartaNoPerteneceAJugadorException(jugador.getId(), carta.getId());
        }
        PartidaJugador partidaJugador = obtenerPartidaJugadorDeUnJugadorEnPartida(partida, jugador);

        List<CartaPartidaJugador> cartaPartidaJugadores = cartaPartidaJugadorService
                .traerTodasLasCartaPartidaJugadorDeUnaPartidaJugador(partidaJugador);

        List<CartaPartidaJugador> cartasEnBaraja = cartaPartidaJugadores.stream()
                .filter(cpj -> cpj.getUbicacionCarta().equals(UbicacionCarta.BARAJA))
                .collect(Collectors.toList());

        List<CartaPartidaJugador> cartasEnMazo = cartaPartidaJugadores.stream()
                .filter(cpj -> cpj.getUbicacionCarta().equals(UbicacionCarta.MAZO))
                .collect(Collectors.toList());

        boolean noEsta = cartaPartidaJugadores.stream()
                .filter(cpj -> cpj.getCarta().equals(carta))
                .count() != 1;
        if (noEsta) {
            throw new CartaADescartarNoSeEncuentraEnBarajaException();
        }
        CartaPartidaJugador cartaPartidaJugador = cartaPartidaJugadorService.traerCartaPartidaJugadorDeUnaCarta(carta,partidaJugador);

        //quitar la carta de la baraja y agregarla al mazo
        cartasEnBaraja.remove(cartaPartidaJugador);
        cartaPartidaJugador.setUbicacionCarta(UbicacionCarta.MAZO);
        cartasEnMazo.add(cartaPartidaJugador);

        Random random = new Random();
        int numeroElegido = random.nextInt(cartasEnMazo.size());
        CartaPartidaJugador cartaElegida = cartasEnMazo.get(numeroElegido);
        cartasEnMazo.remove(cartaElegida);
        cartaElegida.setUbicacionCarta(UbicacionCarta.BARAJA);
        cartasEnBaraja.add(cartaElegida);

        partidaJugador.setDescarto(true);
        partidaJugadorService.guardarPartidaJugador(partidaJugador);
        cartaPartidaJugadorService.guardarCartaPartidaJugador(cartasEnMazo);
        cartaPartidaJugadorService.guardarCartaPartidaJugador(cartasEnBaraja);
        verificarEmpezarPartida(partida, jugador);
        return cartasEnBaraja.stream().map(cpj-> cpj.getCarta()).toList();
    }

    @Override
    public List<Carta> tirarCarta(Partida partida, Carta carta, Jugador jugador)
            throws CartaNoPerteneceAJugadorException, JugadorNoLeTocaJugarException, JugadorNoJuegaPorquePasoException,
            JugadorNoPerteneceAPartidaException, PartidaNoEstaEnCursoException, CartaNoSeEncuentraEnBarajaException,
            NoSePuedeEnviarCartasAlCementerioPorqueUnJugadorNoPasoException,
            NoSePuedeEnviarCartasAlCementerioPorqueLosJugadoresNoPasaronException,
            PartidaRondaNoPerteneceAPartida, PartidaRondaNoCoincideConRondaDePartidaException {

        PartidaJugador partidaJudadorAJugar = obtenerPartidaJugadorDeUnJugadorEnPartida(partida, jugador);
        CartaPartidaJugador cartaPartidaJugador = cartaPartidaJugadorService.traerCartaPartidaJugadorDeUnaCarta(carta, partidaJudadorAJugar);

        if (!jugadorPerteneceAPartida(jugador, partida)){
            throw new JugadorNoPerteneceAPartidaException(partida.getId(), jugador.getId());
        }
        if (!partida.getEstadoPartida().equals(EstadoPartida.EN_CURSO)){
            throw new PartidaNoEstaEnCursoException(partida.getId(), partida.getEstadoPartida());
        }
        if(partidaJudadorAJugar.isPaso()){
            throw new JugadorNoJuegaPorquePasoException(jugador.getId());
        }
        if(!partidaJudadorAJugar.isTurno()){
            throw new JugadorNoLeTocaJugarException();
        }
        if (!carta.getJugador().equals(jugador)){
            throw new CartaNoPerteneceAJugadorException();
        }
        if (!cartaPartidaJugador.getUbicacionCarta().equals(UbicacionCarta.BARAJA)){
            throw new CartaNoSeEncuentraEnBarajaException(cartaPartidaJugador.getCartaId(), cartaPartidaJugador.getUbicacionCarta());
        }



        cartaPartidaJugador.setUbicacionCarta(UbicacionCarta.CAMPO);
        cartaPartidaJugadorService.guardarCartaPartidaJugador(cartaPartidaJugador);

        PartidaJugador partidaJugadorProximo = obtenerPartidaJugadorDelOtroJugadorEnPartida(partida, jugador);
        if (partidaJugadorProximo.isPaso()){
            partidaJudadorAJugar.setTurno(true);
        }
        if (!partidaJugadorProximo.isPaso()){
            partidaJudadorAJugar.setTurno(false);
            partidaJugadorProximo.setTurno(true);
        }
        partidaJugadorService.guardarPartidaJugador(partidaJudadorAJugar);
        partidaJugadorService.guardarPartidaJugador(partidaJugadorProximo);
        //si se queda sin cartas entonces setPaso(true)
        List<Carta> cartasEnBarajaDelJugador = cartaPartidaJugadorService
                .traerCPJDeUnaPartidaJugadorEnUnaUbicacionDeterminada(partidaJudadorAJugar, UbicacionCarta.BARAJA)
                .stream().map(cpj->cpj.getCarta()).collect(Collectors.toList());

        if (cartasEnBarajaDelJugador.isEmpty()){
            partidaJudadorAJugar.setPaso(true);
        }

        if(partidaJudadorAJugar.isPaso()&&partidaJugadorProximo.isPaso()){
            partida.setEstadoPartida(EstadoPartida.NUEVA_RONDA);
            partida = enviarCartasAlCementerioYContarPuntajeDeRonda(partida, jugador);
            partida = verficarGanador(partida);
            partidaRepository.save(partida);
        }

        return cartaPartidaJugadorService.traerTodasLasCartaPartidaJugadorDeUnaPartidaJugador(partidaJudadorAJugar)
                .stream().filter(cpj-> cpj.getUbicacionCarta().equals(UbicacionCarta.CAMPO))
                .map(cpj->cpj.getCarta()).toList();
    }

    @Override
    public Partida verificarEmpezarPartida(Partida partida, Jugador jugador){
        PartidaJugador partidaJugadorUno = obtenerPartidaJugadorDeUnJugadorEnPartida(partida, jugador);
        PartidaJugador partidaJugadorDos = obtenerPartidaJugadorDelOtroJugadorEnPartida(partida, jugador);
        if (partidaJugadorUno.isDescarto() && partidaJugadorDos.isDescarto()){
            partida.setEstadoPartida(EstadoPartida.EN_CURSO);
            partida.setRonda(1);

        }
        return partidaRepository.save(partida);
    }

    @Override
    public Partida jugadorPasaSuTurno(Partida partida, Jugador jugador) throws JugadorNoPerteneceAPartidaException,
            JugadorNoLeTocaJugarException, PartidaNoEstaEnCursoException, PartidaRondaNoPerteneceAPartida,
            PartidaRondaNoCoincideConRondaDePartidaException,
            NoSePuedeEnviarCartasAlCementerioPorqueUnJugadorNoPasoException,
            NoSePuedeEnviarCartasAlCementerioPorqueLosJugadoresNoPasaronException {
        if(!jugadorPerteneceAPartida(jugador, partida)){
            throw new JugadorNoPerteneceAPartidaException(partida.getId(), jugador.getId());
        }
        PartidaJugador partidaJugador = obtenerPartidaJugadorDeUnJugadorEnPartida(partida, jugador);
        if (!partidaJugador.isTurno()){
            throw new JugadorNoLeTocaJugarException(jugador.getId());
        }
        if (!partida.getEstadoPartida().equals(EstadoPartida.EN_CURSO)){
            throw new PartidaNoEstaEnCursoException(partida.getId(), partida.getEstadoPartida());
        }
        partidaJugador.setPaso(true);
        partidaJugador.setTurno(false);
        PartidaJugador partidaJugadorDos =  obtenerPartidaJugadorDelOtroJugadorEnPartida(partida, jugador);
        partidaJugadorDos.setTurno(true);
        partidaJugadorService.guardarPartidaJugador(partidaJugadorDos);
        partidaJugadorService.guardarPartidaJugador(partidaJugador);
        if (partidaJugador.isPaso()&&partidaJugadorDos.isPaso()){
            partida = enviarCartasAlCementerioYContarPuntajeDeRonda(partida, jugador);
            return verficarGanador(partida);
        }
        return partida;
    }
    @Override
    public boolean jugadorPerteneceAPartida(Jugador jugador, Partida partida){
        List<PartidaJugador> partidasJugador = partida.getPartidasJugador();
        for (PartidaJugador pj: partidasJugador){
            if (pj.getJugador().equals(jugador)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public List<PartidaJugador> obtenerPartidasJugadorDeUnaPartida(Partida partida){
        return partidaJugadorService.obtenerPartidasJugadorDeUnaPartida(partida);
    }

    @Override
    public PartidaJugador obtenerPartidaJugadorDeUnJugadorEnPartida(Partida partida, Jugador jugador){
        List<PartidaJugador> partidasJugador = obtenerPartidasJugadorDeUnaPartida(partida);
        PartidaJugador partidaJugador = new PartidaJugador();
        for (PartidaJugador pj : partidasJugador) {
            if (pj.getJugador().equals(jugador)) {
                partidaJugador = pj;
            }
        }
        return partidaJugador;
    }
    @Override
    public PartidaJugador obtenerPartidaJugadorDelOtroJugadorEnPartida(Partida partida, Jugador jugador){
        List<PartidaJugador> partidasJugador = obtenerPartidasJugadorDeUnaPartida(partida);
        PartidaJugador partidaJugador = new PartidaJugador();
        for (PartidaJugador pj : partidasJugador) {
            if (!pj.getJugador().equals(jugador)) {
                partidaJugador = pj;
            }
        }
        return partidaJugador;
    }

    @Override
    public Partida enviarCartasAlCementerioYContarPuntajeDeRonda(Partida partida, Jugador jugador) throws PartidaNoEstaEnCursoException,
            NoSePuedeEnviarCartasAlCementerioPorqueLosJugadoresNoPasaronException,
            NoSePuedeEnviarCartasAlCementerioPorqueUnJugadorNoPasoException {
        if (!partida.getEstadoPartida().equals(EstadoPartida.EN_CURSO)) {
            throw new PartidaNoEstaEnCursoException(partida.getId(), partida.getEstadoPartida());
        }
        PartidaJugador partidaJugadorUno = obtenerPartidaJugadorDeUnJugadorEnPartida(partida, jugador);
        PartidaJugador partidaJugadorDos = obtenerPartidaJugadorDelOtroJugadorEnPartida(partida, jugador);
        if (!partidaJugadorUno.isPaso() && !partidaJugadorDos.isPaso()){
            throw new NoSePuedeEnviarCartasAlCementerioPorqueLosJugadoresNoPasaronException(partida.getId());
        }
        if (!partidaJugadorUno.isPaso()){
            Jugador jugadorUno = partidaJugadorUno.getJugador();
            throw new NoSePuedeEnviarCartasAlCementerioPorqueUnJugadorNoPasoException(partida.getId(), jugadorUno.getId());
        }
        if (!partidaJugadorDos.isPaso()){
            Jugador jugadorDos = partidaJugadorDos.getJugador();
            throw new NoSePuedeEnviarCartasAlCementerioPorqueUnJugadorNoPasoException(partida.getId(), jugadorDos.getId());
        }

        List<CartaPartidaJugador> cpjEnCampoJugadorUno= cartaPartidaJugadorService
                .traerCPJDeUnaPartidaJugadorEnUnaUbicacionDeterminada(partidaJugadorUno, UbicacionCarta.CAMPO);
        //Obtener fuerzas de las cartas y sumarlas
        List<Carta> cartasEnCampoJugadorUno = new ArrayList<>();
        for(CartaPartidaJugador cpj : cpjEnCampoJugadorUno){
            cartasEnCampoJugadorUno.add(cpj.getCarta());
        }
        for (CartaPartidaJugador cpj :cpjEnCampoJugadorUno ){
            cpj.setUbicacionCarta(UbicacionCarta.CEMENTERIO);
        }
        cartaPartidaJugadorService.guardarCartaPartidaJugador(cpjEnCampoJugadorUno);

        List<CartaPartidaJugador> cpjEnCampoJugadorDos = cartaPartidaJugadorService
                .traerCPJDeUnaPartidaJugadorEnUnaUbicacionDeterminada(partidaJugadorDos, UbicacionCarta.CAMPO);
        List<Carta> cartasEnCampoJugadorDos = new ArrayList<>();
        for(CartaPartidaJugador cpj : cpjEnCampoJugadorDos){
            cartasEnCampoJugadorDos.add(cpj.getCarta());
        }
        for (CartaPartidaJugador cpj: cpjEnCampoJugadorDos ){
            cpj.setUbicacionCarta(UbicacionCarta.CEMENTERIO);
        }
        cartaPartidaJugadorService.guardarCartaPartidaJugador(cpjEnCampoJugadorDos);

        int fuerzaJugadorUno = 0;
        for (Carta c : cartasEnCampoJugadorUno){
            fuerzaJugadorUno += c.getFuerza();
        }
        int fuerzaJugadorDos = 0;
        for (Carta c : cartasEnCampoJugadorDos){
            fuerzaJugadorDos += c.getFuerza();
        }
        PartidaRonda partidaRonda = new PartidaRonda();
        partidaRonda.setPartida(partida);
        partidaRonda.setNumeroRonda(partida.getRonda());
        partidaRonda.setPartidaJugadorUno(partidaJugadorUno);
        partidaRonda.setPuntajeJugadorUno(fuerzaJugadorUno);
        partidaRonda.setPartidaJugadordos(partidaJugadorDos);
        partidaRonda.setPuntajeJugadorDos(fuerzaJugadorDos);
        partidaRondaService.guardarPartidaRonda(partidaRonda);
        partida.agregarPartidaRonda(partidaRonda);
        return partidaRepository.save(partida);
    }
    @Override
    public Partida verficarGanador(Partida partida)
            throws PartidaRondaNoPerteneceAPartida, PartidaRondaNoCoincideConRondaDePartidaException {

        int numeroRonda = partida.getRonda();
        PartidaRonda partidaRondaActual = partidaRondaService.traerPartidaRondaDeUnaPartida(partida, numeroRonda);
        PartidaJugador partidaJugadorUno = partidaRondaActual.getPartidaJugadorUno();
        PartidaJugador partidaJugadorDos = partidaRondaActual.getPartidaJugadordos();
        if (!partidaRondaActual.getPartida().equals(partida)){
            throw new PartidaRondaNoPerteneceAPartida(partidaRondaActual.getId(), partida.getId(), partidaRondaActual.getPartida().getId());
        }
        if (numeroRonda!=partidaRondaActual.getNumeroRonda()){
            throw new PartidaRondaNoCoincideConRondaDePartidaException();
        }

        int fuerzaJugadorUno = partidaRondaActual.getPuntajeJugadorUno();
        int fuerzaJugadorDos = partidaRondaActual.getPuntajeJugadorDos();

        //chequear quien gana la ronda o si se empata
        if (fuerzaJugadorUno==fuerzaJugadorDos){
            partidaRondaActual.setEmpate(true);
            partidaJugadorUno.setVida(partidaJugadorUno.getVida()-1);
            partidaJugadorDos.setVida(partidaJugadorDos.getVida()-1);
        }
        if (fuerzaJugadorUno>fuerzaJugadorDos){
            partidaRondaActual.setEmpate(false);
            partidaJugadorDos.setVida(partidaJugadorDos.getVida()-1);
        }
        if(fuerzaJugadorDos>fuerzaJugadorUno){
            partidaRondaActual.setEmpate(false);
            partidaJugadorUno.setVida(partidaJugadorUno.getVida()-1);
        }
        partidaRondaActual.setPuntajeJugadorUno(fuerzaJugadorUno);
        partidaRondaActual.setPuntajeJugadorDos(fuerzaJugadorDos);

        partidaRondaService.guardarPartidaRonda(partidaRondaActual);
        partidaJugadorService.guardarPartidaJugador(partidaJugadorUno);
        partidaJugadorService.guardarPartidaJugador(partidaJugadorDos);


        //se jugaron 2 ronda y ambas la gano un solo jugador
        if(partida.getRonda()==2 && partidaJugadorUno.getVida()==0 ){
            partida.setEstadoPartida(EstadoPartida.TERMINADO);
            partida.setJugadorPerdedor(partidaJugadorUno.getJugador());
            partida.setJugadorGanador(partidaJugadorDos.getJugador());
        }
        if (partida.getRonda()== 2 && partidaJugadorDos.getVida()==0){
            partida.setEstadoPartida(EstadoPartida.TERMINADO);
            partida.setJugadorPerdedor(partidaJugadorDos.getJugador());
            partida.setJugadorGanador(partidaJugadorUno.getJugador());
        }
        //se jugaron 2 rondas, y se empataron todas
        if (partida.getRonda()==2 && partidaJugadorUno.getVida()==0 && partidaJugadorDos.getVida()==0){
            partida.setEstadoPartida(EstadoPartida.TERMINADO);
            partida.setEmpate(true);
        }
        //se jugaron 3 rondas y se empato 1 y se gano las otras dos
        if (partida.getRonda()==3 && partidaJugadorUno.getVida()==1 && partidaJugadorDos.getVida()==0){
            partida.setEstadoPartida(EstadoPartida.TERMINADO);
            partida.setJugadorPerdedor(partidaJugadorUno.getJugador());
            partida.setJugadorGanador(partidaJugadorDos.getJugador());
        }
        if (partida.getRonda()==3 && partidaJugadorDos.getVida()==1 && partidaJugadorUno.getVida()==0){
            partida.setEstadoPartida(EstadoPartida.TERMINADO);
            partida.setJugadorPerdedor(partidaJugadorDos.getJugador());
            partida.setJugadorGanador(partidaJugadorUno.getJugador());
        }

        partida.setRonda(partida.getRonda()+1);

        partidaJugadorUno.setPaso(false);
        partidaJugadorDos.setPaso(false);
        if (fuerzaJugadorUno>fuerzaJugadorDos){
            partidaJugadorUno.setTurno(true);
            partidaJugadorDos.setTurno(false);
        }
        if (fuerzaJugadorDos>fuerzaJugadorUno){
            partidaJugadorUno.setTurno(false);
            partidaJugadorDos.setTurno(true);
        }
        if (fuerzaJugadorUno==fuerzaJugadorDos){
            Random random = new Random();
            int numeroElegido = random.nextInt(2);
            if(numeroElegido==0){
                partidaJugadorUno.setTurno(true);
                partidaJugadorDos.setTurno(false);
            }else {
               partidaJugadorUno.setTurno(false);
               partidaJugadorDos.setTurno(true);
            }
        }
        partidaJugadorService.guardarPartidaJugador(partidaJugadorUno);
        partidaJugadorService.guardarPartidaJugador(partidaJugadorDos);

        return partidaRepository.save(partida);
    }


}
