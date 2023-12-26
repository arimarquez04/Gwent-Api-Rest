package com.bewise.pasantia.local.dto;

import com.bewise.pasantia.local.model.Carta;
import com.bewise.pasantia.local.model.PosicionCarta;

public class CartaRequestDto {

    private String nombre;

    private int fuerza;

    private String lema;

    private PosicionCarta posicionCarta;

    private int jugadorId;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public String getLema() {
        return lema;
    }

    public void setLema(String lema) {
        this.lema = lema;
    }

    public PosicionCarta getPosicion() {
        return posicionCarta;
    }

    public void setPosicion(PosicionCarta posicionCarta) {
        this.posicionCarta = posicionCarta;
    }

    public int getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(int jugadorId) {
        this.jugadorId = jugadorId;
    }

    public Carta convert(){
        Carta carta = new Carta();
        carta.setNombre(nombre);
        carta.setFuerza(fuerza);
        carta.setLema(lema);
        carta.setPosicion(posicionCarta);
        return carta;
    }
}
