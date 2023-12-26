package com.bewise.pasantia.local.exceptions;

public class CartaADescartarNoSeEncuentraEnBarajaException extends Exception{
    public CartaADescartarNoSeEncuentraEnBarajaException(){
        super("La/s carta/s a descartar no se encuentra/n en la baraja");
    }
}
