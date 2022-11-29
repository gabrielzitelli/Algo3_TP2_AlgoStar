package edu.fiuba.algo3.modelo.AlgoStar;

import edu.fiuba.algo3.modelo.Ataque.Ocupable;
import edu.fiuba.algo3.modelo.Excepciones.ErrorJugadorNoPuedeAccederOcupableEnemigo;
import edu.fiuba.algo3.modelo.Imperio.Imperio;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;

import java.util.ArrayList;

public class AlgoStar {

    private AdministradorDeJugadores jugadores = new AdministradorDeJugadores();
    private ArrayList<Imperio> imperios = new ArrayList<>();
    private Turno turno;

    public void asignarJugador(String nombre, String color, Imperio imperio) {
        jugadores.agregarJugador(new Jugador(nombre, color, imperio));
        imperios.add(imperio);
    }

    public void empezarJuego() {
        inicializarBases();
        turno = new TurnoJugador(jugadores.conseguirSiguienteJugador(), jugadores);
    }

    private void inicializarBases() {
        jugadores.inicializarBases();
    }

    public void terminarTurno() {
        turno = turno.terminarTurno();
    }

    public boolean partidaTerminada() {
        return turno.partidaTerminada();
    }

    public Jugador conseguirJugadorActual() {
        return turno.jugadorActual();
    }

    public Ocupable conseguirOcupableEn(Coordenada coordenada) {
        Ocupable ocupable = Mapa.obtener().obtenerOcupable(coordenada);

        if (ocupable.perteneceAImperio(turno.jugadorActual().conseguirImperio()))
            return ocupable;

        throw new ErrorJugadorNoPuedeAccederOcupableEnemigo();
    }
}
