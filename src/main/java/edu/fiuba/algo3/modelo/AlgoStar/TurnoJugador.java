package edu.fiuba.algo3.modelo.AlgoStar;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoHayMasJugadores;

public class TurnoJugador implements Turno {

    private Jugador jugadorDeTurno;
    private AdministradorDeJugadores jugadores;

    public TurnoJugador(Jugador jugadorDeTurno, AdministradorDeJugadores jugadores) {
        this.jugadorDeTurno = jugadorDeTurno;
        this.jugadores = jugadores;
    }

    @Override
    public Turno terminarTurno() {
        jugadorDeTurno.conseguirImperio().terminarTurno();

        Jugador siguienteJugador;

        try {
            siguienteJugador = jugadores.conseguirSiguienteJugador();
        } catch (ErrorNoHayMasJugadores e) {
            return new PartidaTerminada(jugadorDeTurno);
        }

        if (siguienteJugador.conseguirNombre().equals(jugadorDeTurno.conseguirNombre()))
            return new PartidaTerminada(jugadorDeTurno);

        return new TurnoJugador(siguienteJugador, jugadores);
    }

    @Override
    public boolean partidaTerminada() {
        return false;
    }

    @Override
    public Jugador jugadorActual() {
        return jugadorDeTurno;
    }
}
