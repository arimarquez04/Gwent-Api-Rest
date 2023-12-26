package com.bewise.pasantia.local.exceptions;

public class CartasInsufucientesException extends  Exception{
    public CartasInsufucientesException(){
        super("El jugador no tiene la cantidad minima de cartas");
    }
    public CartasInsufucientesException(int id){
        super("No existe la carta solicitada con id: " + id);
    }
}
