package com.bewise.pasantia.local.exceptions;

public class CartaAJugarYaSeEncuentraJugadaException extends Exception{
    public CartaAJugarYaSeEncuentraJugadaException(int id){
        super("La carta con id: " + id + " ya se encuentra jugada");
    }
}
