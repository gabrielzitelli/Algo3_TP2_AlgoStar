package edu.fiuba.algo3.modelo.AlgoStar;

import edu.fiuba.algo3.modelo.Excepciones.ErrorDosJugadoresNoPuedenTenerElMismoColor;
import edu.fiuba.algo3.modelo.Excepciones.ErrorDosJugadoresNoPuedenTenerElMismoImperio;
import edu.fiuba.algo3.modelo.Excepciones.ErrorDosJugadoresNoPuedenTenerElMismoNombre;
import edu.fiuba.algo3.modelo.Excepciones.ErrorELNombreDelJugadorDebeSerMayorA6Caracteres;
import edu.fiuba.algo3.modelo.Imperio.Imperio;

public class Jugador {

    private String nombre;
    private String color;
    private Imperio imperio;

    public Jugador(String nombre, String color, Imperio imperio) {
        verificarLargoDeNombre(nombre);
        this.nombre = nombre;
        this.color = color;
        this.imperio = imperio;
    }

    private void verificarLargoDeNombre(String nombre) {
        if (nombre.length() < 6){
            throw new ErrorELNombreDelJugadorDebeSerMayorA6Caracteres();
        }
    }

    public void verificarRespectoDe(Jugador jugador) {
        if (this.nombre.equals(jugador.nombre))
            throw new ErrorDosJugadoresNoPuedenTenerElMismoNombre();
        if (this.color.equals((jugador.color)))
            throw new ErrorDosJugadoresNoPuedenTenerElMismoColor();
        if (this.imperio.getClass().equals(jugador.imperio.getClass()))
            throw new ErrorDosJugadoresNoPuedenTenerElMismoImperio();
    }
}