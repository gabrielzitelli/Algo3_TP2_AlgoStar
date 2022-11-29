package edu.fiuba.algo3.modelo.AlgoStar;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoHayMasJugadores;
import edu.fiuba.algo3.modelo.Excepciones.ErrorSoloPuedenJugarDosPersonasAlMismoTiempo;

import java.util.LinkedList;
import java.util.Queue;

public class AdministradorDeJugadores {

    private Queue<Jugador> jugadores = new LinkedList<>();
    private final int MAXIMO_JUGADORES = 2;

    public void agregarJugador(Jugador jugadorNuevo) {
        if (jugadores.size() >= MAXIMO_JUGADORES)
            throw new ErrorSoloPuedenJugarDosPersonasAlMismoTiempo();

        revisarJugador(jugadorNuevo);
        jugadores.add(jugadorNuevo);
    }

    private void revisarJugador(Jugador jugadorARevisar) {
        for (Jugador jugador : jugadores)
            jugadorARevisar.verificarRespectoDe(jugador);
    }

    public void inicializarBases() {
        for (Jugador jugador : jugadores) {
            jugador.conseguirImperio().inicializarAsentamientoPrimerTurno();
        }
    }

    public Jugador conseguirSiguienteJugador() {
        Jugador siguiente = jugadores.poll();

        if (siguiente == null)
            throw new ErrorNoHayMasJugadores();

        siguiente.conseguirImperio().revisarDestrucciones();
        if (siguiente.perdio()) {
            return conseguirSiguienteJugador();
        }

        jugadores.add(siguiente);
        return siguiente;
    }
}
