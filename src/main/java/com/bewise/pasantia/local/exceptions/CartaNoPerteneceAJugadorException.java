package com.bewise.pasantia.local.exceptions;

public class CartaNoPerteneceAJugadorException extends Exception{
    public CartaNoPerteneceAJugadorException(){
        super("La carta no pertenece al mismo jugador");
    }
    public CartaNoPerteneceAJugadorException(int jugadorId, int  cartaid){
        super("La carta con id " + cartaid + " no pertenece al jugador con id " + jugadorId);
    }
}
