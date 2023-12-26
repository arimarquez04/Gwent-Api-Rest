package com.bewise.pasantia.local.repository;


import com.bewise.pasantia.local.model.PartidaJugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidaJugadorRepository extends JpaRepository<PartidaJugador, Integer> {

    @Query(value = "SELECT * FROM `partida_jugador` WHERE `partida_id` = ?1", nativeQuery = true)
    List<PartidaJugador> traerPartidasJugadorDeUnaPartida(Integer partidaId);
}
