package com.bewise.pasantia.local.exceptions;

public class NoSePuedeEnviarCartasAlCementerioPorqueUnJugadorNoPasoException extends Exception {
    public NoSePuedeEnviarCartasAlCementerioPorqueUnJugadorNoPasoException(int idPartida, int idJugador){
        super("No se pueden enviar las cartas al cementerio de la partida con id: " + idPartida +
                " porque el jugador con id: " + idJugador + " no pasó");
    }
}
