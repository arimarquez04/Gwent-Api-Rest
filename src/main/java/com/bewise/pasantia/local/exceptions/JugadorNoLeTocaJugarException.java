package com.bewise.pasantia.local.exceptions;

public class JugadorNoLeTocaJugarException extends Exception{
    public JugadorNoLeTocaJugarException(){
        super("No es turno del jugador");
    }
    public JugadorNoLeTocaJugarException(int jugadorId){
        super("No es turno del jugador con id: " + jugadorId);}
}
