package com.bewise.pasantia.local.exceptions;

public class PartidaRondaNoCoincideConRondaDePartidaException extends Exception{
    public PartidaRondaNoCoincideConRondaDePartidaException(){
       super("La ronda no coincide con el numero de ronda de la partida");
    }

}
