package com.bewise.pasantia.local.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "partida_ronda")
public class PartidaRonda {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "partida_id")
    @JsonBackReference
    private Partida partida;
    @Column(name = "empate")
    private boolean empate;
    @Column(name = "numero_ronda")
    private int numeroRonda;

    @OneToOne
    @JoinColumn(name = "partida_jugador_uno_id")
    @JsonBackReference
    private PartidaJugador partidaJugadorUno;
    @Column(name = "puntaje_jugador_uno")
    private int puntajeJugadorUno;
    @OneToOne
    @JoinColumn(name = "partida_jugador_dos_id")
    @JsonBackReference
    private PartidaJugador partidaJugadordos;
    @Column(name = "puntaje_jugador_dos")
    private int puntajeJugadorDos;


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

    public boolean isEmpate() {
        return empate;
    }

    public void setEmpate(boolean empate) {
        this.empate = empate;
    }

    public int getNumeroRonda() {
        return numeroRonda;
    }

    public void setNumeroRonda(int numeroRonda) {
        this.numeroRonda = numeroRonda;
    }

    public PartidaJugador getPartidaJugadorUno() {
        return partidaJugadorUno;
    }

    public void setPartidaJugadorUno(PartidaJugador partidaJugadorUno) {
        this.partidaJugadorUno = partidaJugadorUno;
    }

    public int getPuntajeJugadorUno() {
        return puntajeJugadorUno;
    }

    public void setPuntajeJugadorUno(int puntajeJugadorUno) {
        this.puntajeJugadorUno = puntajeJugadorUno;
    }

    public PartidaJugador getPartidaJugadordos() {
        return partidaJugadordos;
    }

    public void setPartidaJugadordos(PartidaJugador partidaJugadordos) {
        this.partidaJugadordos = partidaJugadordos;
    }

    public int getPuntajeJugadorDos() {
        return puntajeJugadorDos;
    }

    public void setPuntajeJugadorDos(int puntajeJugadorDos) {
        this.puntajeJugadorDos = puntajeJugadorDos;
    }
}
