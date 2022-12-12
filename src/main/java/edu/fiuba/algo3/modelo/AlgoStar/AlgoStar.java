package edu.fiuba.algo3.modelo.AlgoStar;

import edu.fiuba.algo3.modelo.Excepciones.ErrorJugadorNoPuedeAccederOcupableEnemigo;
import edu.fiuba.algo3.modelo.Imperio.Imperio;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Ocupable;

public class AlgoStar {

    private final AdministradorDeJugadores jugadores = new AdministradorDeJugadores();
    private Turno turno;

    private int cantidadDeTurnos = 0;
    private int interTurnoContador = 0;

    public void asignarJugador(String nombre, String color, Imperio imperio) {
        jugadores.agregarJugador(new Jugador(nombre, color, imperio));
    }

    public String[] jugadoresEnString(){
        return jugadores.jugadoresEnString();
    }

    public void empezarJuego() {
        inicializarBases();
        Logger.obtener().log("\nComienzo del juego, turno " + cantidadDeTurnos);
        turno = new TurnoJugador(jugadores.conseguirSiguienteJugador(), jugadores);
    }

    private void inicializarBases() {
        jugadores.inicializarBases();
    }

    public void terminarTurno() {
        turno = turno.terminarTurno();
        interTurnoContador++;

        if(interTurnoContador == 2){
            interTurnoContador = 0;
            cantidadDeTurnos++;
            Logger.obtener().log("\nComienzo del turno " + cantidadDeTurnos);
        }
    }

    public boolean partidaTerminada() {
        return turno.partidaTerminada();
    }

    public Jugador conseguirJugadorActual() {
        return turno.jugadorActual();
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
    public void revancha(){
        if(this.partidaTerminada()){
            this.prepararJugadores();
            Mapa elMapa = Mapa.obtener();
            elMapa.prepararMapaParaRevancha();
            this.empezarJuego();
        }
    }

    private void prepararJugadores() {
        jugadores.prepararParaRevancha();
    }
}
