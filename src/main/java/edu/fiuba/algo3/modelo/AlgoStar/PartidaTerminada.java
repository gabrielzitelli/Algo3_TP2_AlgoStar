package edu.fiuba.algo3.modelo.AlgoStar;

public class PartidaTerminada implements Turno {

    private final Jugador ganador;

    public PartidaTerminada(Jugador ganador) {
        this.ganador = ganador;

        Logger.obtener().log("Ha ganado la partida el jugador " + ganador.conseguirNombre() + ".");
    }

    @Override
    public Turno terminarTurno() {
        return this;
    }

    @Override
    public boolean partidaTerminada() {
        return true;
    }

    @Override
    public Jugador jugadorActual() {
        return ganador;
    }
}
