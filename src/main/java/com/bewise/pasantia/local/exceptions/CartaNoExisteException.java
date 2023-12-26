package com.bewise.pasantia.local.exceptions;

public class CartaNoExisteException extends Exception{
    public CartaNoExisteException(){
        super("No existe la carta solicitada");
    }
    public CartaNoExisteException(int id){
        super("No existe la carta solicitada con id: " + id);
    }
}
