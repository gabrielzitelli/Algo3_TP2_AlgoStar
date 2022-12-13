package edu.fiuba.algo3.modelo.AlgoStar;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoPuedenJugarMasPersonasEnLaMismaPartida;

import java.util.LinkedList;
import java.util.Queue;

public class AdministradorDeJugadores {

    private Queue<Jugador> jugadores = new LinkedList<>();
    private final LinkedList<Jugador> todosLosJugadores =  new LinkedList<>();

    public void agregarJugador(Jugador jugadorNuevo) {
        int MAXIMO_JUGADORES = 2;
        if (jugadores.size() >= MAXIMO_JUGADORES)
            throw new ErrorNoPuedenJugarMasPersonasEnLaMismaPartida();

        revisarJugador(jugadorNuevo);
        jugadores.add(jugadorNuevo);
        todosLosJugadores.add(jugadorNuevo);
    }

    public String[] jugadoresEnString() {

        String[] jugadoresString = new String[jugadores.size()];
        int i = 0;

        for (Jugador jugador : jugadores) {
            jugadoresString[i] = jugador.toString();
            i++;
        }

        return jugadoresString;
    }

    private void revisarJugador(Jugador jugadorARevisar) {
        for (Jugador jugador : jugadores)
            jugadorARevisar.verificarRespectoDe(jugador);
    }

    public void inicializarBases() {
        for (Jugador jugador : todosLosJugadores) {
            jugador.inicializarAsentamientoPrimerTurno();
        }
    }

    public Jugador conseguirSiguienteJugador() {
        Jugador siguiente = jugadores.poll();

        siguiente.revisarDestrucciones();
        if (siguiente.perdio()) {
            return conseguirSiguienteJugador();
        }

        jugadores.add(siguiente);
        return siguiente;
    }

    public void prepararParaRevancha() {
        // Renuevo la cola de jugadores directamente desde una nueva
        jugadores = new LinkedList<>();
        for (Jugador jugador : todosLosJugadores) {
            jugador.prepararparaRevancha();
            jugadores.add(jugador);
        }
    }
}
