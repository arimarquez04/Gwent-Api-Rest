package com.bewise.pasantia.local.exceptions;

public class NoSePuedeEnviarCartasAlCementerioPorqueLosJugadoresNoPasaronException extends Exception {
    public NoSePuedeEnviarCartasAlCementerioPorqueLosJugadoresNoPasaronException(int idPartida){
        super("No se pueden enviar las cartas al cementerio de la partida con id: " + idPartida + " porque los jugadores no pasaron");
    }
}
