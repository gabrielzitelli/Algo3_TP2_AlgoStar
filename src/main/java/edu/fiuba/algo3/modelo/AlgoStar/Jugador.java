package edu.fiuba.algo3.modelo.AlgoStar;

import edu.fiuba.algo3.modelo.Excepciones.ErrorDosJugadoresNoPuedenTenerElMismoColor;
import edu.fiuba.algo3.modelo.Excepciones.ErrorDosJugadoresNoPuedenTenerElMismoImperio;
import edu.fiuba.algo3.modelo.Excepciones.ErrorDosJugadoresNoPuedenTenerElMismoNombre;
import edu.fiuba.algo3.modelo.Excepciones.ErrorElNombreDelJugadorDebeSerMayorA6Caracteres;
import edu.fiuba.algo3.modelo.Imperio.Imperio;

public class Jugador {

    private final String nombre;
    private final String color;
    private final Imperio imperio;
    private final int largoMinimoNombre = 6;

    public Jugador(String nombre, String color, Imperio imperio) {
        verificarLargoDeNombre(nombre);
        this.nombre = nombre;
        this.color = color;
        this.imperio = imperio;
    }

    private void verificarLargoDeNombre(String nombre) {
        if (nombre.length() < largoMinimoNombre)
            throw new ErrorElNombreDelJugadorDebeSerMayorA6Caracteres();
    }

    public void verificarRespectoDe(Jugador jugador) {
        if (this.nombre.equals(jugador.nombre))
            throw new ErrorDosJugadoresNoPuedenTenerElMismoNombre();
        if (this.color.equals((jugador.color)))
            throw new ErrorDosJugadoresNoPuedenTenerElMismoColor();
        if (this.imperio.getClass().equals(jugador.imperio.getClass()))
            throw new ErrorDosJugadoresNoPuedenTenerElMismoImperio();
    }

    public Imperio conseguirImperio() {
        return imperio;
    }

    public boolean perdio() {
        return imperio.partidaTerminada();
    }

    public String conseguirNombre() {
        return nombre;
    }

    public String toString() {
        return "imperio " + imperio.toString() + " nombre " + nombre + " color " +  color;
    }

    public void inicializarAsentamientoPrimerTurno() {
        imperio.inicializarAsentamientoPrimerTurno();
    }

    public void prepararparaRevancha() {
        imperio.prepararParaRevancha();
    }
}