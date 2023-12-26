package com.bewise.pasantia.local.dto;
import com.bewise.pasantia.local.model.Jugador;

public class JugadorRequestDto {
    private String apodo;

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public Jugador convert(){
        Jugador jugador = new Jugador();
        jugador.setApodo(apodo);
        return jugador;
    }

}
