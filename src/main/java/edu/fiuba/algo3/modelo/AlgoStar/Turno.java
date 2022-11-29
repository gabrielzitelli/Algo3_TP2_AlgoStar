package edu.fiuba.algo3.modelo.AlgoStar;

public interface Turno {

    Turno terminarTurno();

    boolean partidaTerminada();

    Jugador jugadorActual();
}
