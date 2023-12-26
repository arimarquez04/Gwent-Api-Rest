package com.bewise.pasantia.local.repository;

import com.bewise.pasantia.local.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Integer> {
    @Query(value = "select * from jugador where apodo = ?1", nativeQuery = true)
    public Optional<Jugador> traerJugadorPorApodo(String apodo);
}
