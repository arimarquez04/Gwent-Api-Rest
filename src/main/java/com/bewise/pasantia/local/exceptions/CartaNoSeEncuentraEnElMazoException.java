package com.bewise.pasantia.local.exceptions;

public class CartaNoSeEncuentraEnElMazoException extends Exception{
    public CartaNoSeEncuentraEnElMazoException(){
        super("La carta a tirar no se encuentra en el mazo");
    }
}
