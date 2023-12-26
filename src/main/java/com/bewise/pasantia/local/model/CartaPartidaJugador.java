package com.bewise.pasantia.local.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "carta_partida_jugador")
public class CartaPartidaJugador {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JoinColumn(name = "partida_jugador_id")
    @ManyToOne
    @JsonBackReference
    private PartidaJugador partidaJugador;
    @JoinColumn(name = "carta_id")
    @ManyToOne
    private Carta carta;
    @Column(name = "ubicacion")
    @Enumerated(value = EnumType.STRING)
    private UbicacionCarta ubicacionCarta;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PartidaJugador getPartidaJugador() {
        return partidaJugador;
    }

    public void setPartidaJugador(PartidaJugador partidaJugador) {
        this.partidaJugador = partidaJugador;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }
    @JsonIgnore
    public int getCartaId(){
        return carta.getId();
    }
    public UbicacionCarta getUbicacionCarta() {
        return ubicacionCarta;
    }

    public void setUbicacionCarta(UbicacionCarta ubicacionCarta) {
        this.ubicacionCarta = ubicacionCarta;
    }
    public static CartaPartidaJugador createFromCarta(Carta carta, PartidaJugador partidaJugador, UbicacionCarta ubicacionCarta){
        CartaPartidaJugador cartaPartidaJugador = new CartaPartidaJugador();
        cartaPartidaJugador.setCarta(carta);
        cartaPartidaJugador.setUbicacionCarta(ubicacionCarta);
        cartaPartidaJugador.setPartidaJugador(partidaJugador);
        return cartaPartidaJugador;
    }

}
