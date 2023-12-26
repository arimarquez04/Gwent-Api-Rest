package com.bewise.pasantia.local.repository;

import com.bewise.pasantia.local.model.Carta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartaRepository extends JpaRepository<Carta, Integer> {
    @Query(value = "SELECT * FROM carta WHERE jugador_id = ?1", nativeQuery = true)
    List<Carta> traerCartasDeUnJugador(Integer jugadorId);


}
