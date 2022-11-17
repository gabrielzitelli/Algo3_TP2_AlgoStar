package edu.fiuba.algo3.modelo.AlgoStar;

import edu.fiuba.algo3.modelo.Excepciones.ErrorSoloPuedenJugarDosPersonasAlMismoTiempo;
import edu.fiuba.algo3.modelo.Imperio.Imperio;

import java.util.ArrayList;

public class AlgoStar {

    private ArrayList<Jugador> jugadores = new ArrayList<>();

    public AlgoStar() {
    }

    public void asignarJugador(String nombre, String color, Imperio imperio) {
        if (jugadores.size() < 2){
            Jugador jugador = new Jugador(nombre,color,imperio);
            revisarJugador(jugador);
            jugadores.add(jugador);
        } else {
            throw new ErrorSoloPuedenJugarDosPersonasAlMismoTiempo();
        }
    }

    private void revisarJugador(Jugador jugadorARevisar) {
        for (Jugador jugador : jugadores) {
            jugadorARevisar.verificarRespectoDe(jugador);
        }
    }
}
