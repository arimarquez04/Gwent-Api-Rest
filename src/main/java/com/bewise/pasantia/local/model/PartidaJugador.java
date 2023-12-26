package com.bewise.pasantia.local.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "partida_jugador")
public class PartidaJugador {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "partida_id")
    @JsonBackReference
    private Partida partida;
    @Column(name = "descarto")
    private boolean descarto;
    @Column(name = "preparo_mazo")
    private boolean preparoMazo;
    @Column(name = "turno")
    private boolean turno;
    @Column(name = "paso")
    private boolean paso;
    @Column(name = "vida")
    private int vida;
    @OneToOne
    @JoinColumn(name = "jugador_id")
    private Jugador jugador;

    @JsonIgnore
    public int getJugadorId() {
        return jugador.getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public boolean isTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    public boolean isPaso() {
        return paso;
    }

    public void setPaso(boolean paso) {
        this.paso = paso;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public boolean isDescarto() {
        return descarto;
    }

    public void setDescarto(boolean descarto) {
        this.descarto = descarto;
    }

    public boolean isPreparoMazo() {
        return preparoMazo;
    }

    public void setPreparoMazo(boolean preparoMazo) {
        this.preparoMazo = preparoMazo;
    }

}
