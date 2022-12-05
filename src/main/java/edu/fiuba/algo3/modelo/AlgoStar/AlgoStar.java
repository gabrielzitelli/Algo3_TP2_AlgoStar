package edu.fiuba.algo3.modelo.AlgoStar;

import edu.fiuba.algo3.modelo.Unidades.Ocupable;
import edu.fiuba.algo3.modelo.Excepciones.ErrorJugadorNoPuedeAccederOcupableEnemigo;
import edu.fiuba.algo3.modelo.Imperio.Imperio;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;

public class AlgoStar {

    private AdministradorDeJugadores jugadores = new AdministradorDeJugadores();
    private Turno turno;

    private int cantidadDeTurnos = 0;

    public void asignarJugador(String nombre, String color, Imperio imperio) {
        jugadores.agregarJugador(new Jugador(nombre, color, imperio));
    }

    public String[] jugadoresEnString(){
        return jugadores.jugadoresEnString();
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
        cantidadDeTurnos++;
    }

    public boolean partidaTerminada() {
        return turno.partidaTerminada();
    }

    public Jugador conseguirJugadorActual() {
        return turno.jugadorActual();
    }

    public String conseguirStringJugadorActual() {
        return turno.jugadorActual().toString();
    }

    public Ocupable conseguirOcupableEn(Coordenada coordenada) {
        Ocupable ocupable = Mapa.obtener().obtenerOcupable(coordenada);

        if (!ocupable.perteneceAImperio(turno.jugadorActual().conseguirImperio()))
            throw new ErrorJugadorNoPuedeAccederOcupableEnemigo();

        return ocupable;
    }

    public int turnoActual() {
        return cantidadDeTurnos;
    }
}
