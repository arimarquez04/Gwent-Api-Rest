package com.bewise.pasantia.local.repository;

import com.bewise.pasantia.local.model.CartaPartidaJugador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartaPartidaJugadorRepository extends JpaRepository<CartaPartidaJugador, Integer> {
    @Query(value = "SELECT * FROM carta_partida_jugador WHERE partida_jugador_id = ?1", nativeQuery = true)
    List<CartaPartidaJugador> traerCartaPartidaJugadorDeUnaPartidaJugador(Integer partidaJugadorId);

    @Query(value = "SELECT * FROM carta_partida_jugador WHERE carta_id =?1 and partida_jugador_id = ?2", nativeQuery = true)
    CartaPartidaJugador traerCartaPartidaJugadorDeUnaCarta(Integer idCarta, Integer partidaJugadorId);

    @Query(value = "SELECT * FROM `carta_partida_jugador` WHERE partida_jugador_id =?1 AND ubicacion=?2 ", nativeQuery = true)
    List<CartaPartidaJugador> traerCartasPartidaJugadorEnUbicacionDeterminada(Integer partidaJugadorId, String ubicacionCarta);
}
