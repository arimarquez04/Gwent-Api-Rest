package com.bewise.pasantia.local.exceptions;

public class JugadorYaPreparoMazoException extends Exception{
    public JugadorYaPreparoMazoException(int id, boolean preparoMazo){
        super("El jugador con id: " + id + " ya preparo mazo: preparo_mazo: " + preparoMazo);
    }
}
