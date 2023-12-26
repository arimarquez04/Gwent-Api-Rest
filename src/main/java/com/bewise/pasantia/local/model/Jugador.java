package com.bewise.pasantia.local.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Jugador {
    @Id
    @Column
    private int id;
    @Column(name="apodo", unique = true)
    private String apodo;
    @OneToMany(mappedBy = "jugador", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Carta> cartas;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }
    public List<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }
}
