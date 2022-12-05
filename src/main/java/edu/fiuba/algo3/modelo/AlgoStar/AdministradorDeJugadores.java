package edu.fiuba.algo3.modelo.AlgoStar;

import edu.fiuba.algo3.modelo.Excepciones.ErrorSoloPuedenJugarDosPersonasAlMismoTiempo;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class AdministradorDeJugadores {

    private Queue<Jugador> jugadores = new LinkedList<>();
    private LinkedList<Jugador> todosLosJugadores =  new LinkedList<>();
    private final int MAXIMO_JUGADORES = 2;

    public void agregarJugador(Jugador jugadorNuevo) {
        if (jugadores.size() >= MAXIMO_JUGADORES)
            throw new ErrorSoloPuedenJugarDosPersonasAlMismoTiempo();

        revisarJugador(jugadorNuevo);
        jugadores.add(jugadorNuevo);
        todosLosJugadores.add(jugadorNuevo);
    }

    public String[] jugadoresEnString(){

        String[] jugadoresString = new String[jugadores.size()];
        int i = 0;

        for( Jugador jugador : jugadores){
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

        siguiente.conseguirImperio().revisarDestrucciones();
        if (siguiente.perdio()) {
            return conseguirSiguienteJugador();
        }

        jugadores.add(siguiente);
        return siguiente;
    }

    public void prepararParaRevancha() {
        for (Jugador jugador : jugadores) {
            jugador.prepararparaRevancha();
        }
    }
}
