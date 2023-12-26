package com.bewise.pasantia.local.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Partida {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name ="estado")
    @Enumerated(value = EnumType.STRING)
    private EstadoPartida estadoPartida;
    @Column(name="ronda")
    private int ronda;
    @Column(name="empate")
    private Boolean empate;
    @OneToMany(mappedBy = "partida")
    @JsonManagedReference
    private List<PartidaJugador> partidasJugador = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "ganador_id")
    @JsonBackReference
    private Jugador jugadorGanador;
    @OneToOne
    @JoinColumn(name = "perdedor_id")
    @JsonBackReference
    private Jugador jugadorPerdedor;

    @OneToMany(mappedBy = "partida")
    @JsonManagedReference
    private List<PartidaRonda> partidaRondas = new ArrayList<>();

    public Boolean isEmpate() {
        return empate;
    }

    public void setEmpate(Boolean empate) {
        this.empate = empate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public boolean termino(){
        return (ronda>=3 || partidaRondas.size()>=3 || jugadorGanador!=null|| jugadorPerdedor!=null);
    }
    public void agregarPartidaJugador(PartidaJugador partidaJugador){
        partidasJugador.add(partidaJugador);
    }
    public void agregarPartidaRonda(PartidaRonda partidaRonda){
        partidaRondas.add(partidaRonda);
    }
    public int getRonda() {
        return ronda;
    }

    public void setRonda(int ronda) {
        this.ronda = ronda;
    }

    public Jugador getJugadorGanador() {
        return jugadorGanador;
    }

    public void setJugadorGanador(Jugador jugadorGanador) {
        this.jugadorGanador = jugadorGanador;
    }

    public Jugador getJugadorPerdedor() {
        return jugadorPerdedor;
    }

    public void setJugadorPerdedor(Jugador jugadorPerdedor) {
        this.jugadorPerdedor = jugadorPerdedor;
    }

    public EstadoPartida getEstadoPartida() {
        return estadoPartida;
    }

    public void setEstadoPartida(EstadoPartida estadoPartida) {
        this.estadoPartida = estadoPartida;
    }

    public List<PartidaJugador> getPartidasJugador() {
        return partidasJugador;
    }

    public void setPartidasJugador(List<PartidaJugador> partidasJugador) {
        this.partidasJugador = partidasJugador;
    }

    public List<PartidaRonda> getPartidaRondas() {
        return partidaRondas;
    }

    public void setPartidaRondas(List<PartidaRonda> partidaRondas) {
        this.partidaRondas = partidaRondas;
    }
    public PartidaRonda obtenerPartidaRondaDeUnaRonda(int ronda){
        return partidaRondas.get(ronda-1);
    }
}
