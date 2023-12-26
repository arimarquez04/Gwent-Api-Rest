package com.bewise.pasantia.local.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "carta")
public class Carta {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fuerza")
    private int fuerza;
    @Column(name = "lema")
    private String lema;
    @Column(name = "posicion")
    @Enumerated(value = EnumType.STRING)
    private PosicionCarta posicionCarta;
    @ManyToOne
    @JoinColumn(name = "jugador_id")
    @JsonBackReference
    private Jugador jugador;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
    public void setPosicionCuerpoACuerpo() {
        this.posicionCarta = PosicionCarta.CUERPOACUERPO;
    }
    public void setPosicionDistancia() {
        this.posicionCarta = PosicionCarta.DISTANCIA;
    }
    public void setPosicionAsedio() {
        this.posicionCarta = PosicionCarta.ASEDIO;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    public static CartaPartidaJugador createFromCarta(Carta c, PartidaJugador partidaJugador, UbicacionCarta ubicacionCarta) {
        CartaPartidaJugador cartaPartidaJugador = new CartaPartidaJugador();
        cartaPartidaJugador.setPartidaJugador(partidaJugador);
        cartaPartidaJugador.setCarta(c);
        cartaPartidaJugador.setUbicacionCarta(ubicacionCarta);
        return cartaPartidaJugador;
    }
    public CartaPartidaJugador fromCarta(Carta c, PartidaJugador partidaJugador, UbicacionCarta ubicacionCarta) {
        CartaPartidaJugador cartaPartidaJugador = new CartaPartidaJugador();
        cartaPartidaJugador.setPartidaJugador(partidaJugador);
        cartaPartidaJugador.setCarta(c);
        cartaPartidaJugador.setUbicacionCarta(ubicacionCarta);
        return cartaPartidaJugador;
    }
}
