package edu.fiuba.algo3.modelo.AlgoStar;

import edu.fiuba.algo3.modelo.Excepciones.ErrorSoloPuedenJugarDosPersonasAlMismoTiempo;
import edu.fiuba.algo3.modelo.Imperio.Imperio;

import java.util.ArrayList;

public class AlgoStar {

    private ArrayList<Jugador> jugadores = new ArrayList<>();
    private ArrayList<Imperio> imperios = new ArrayList<>();
    private Boolean partidaFinalizada = false;

    public AlgoStar() {
    }

    public void asignarJugador(String nombre, String color, Imperio imperio) {
        if (jugadores.size() < 2){
            Jugador jugador = new Jugador(nombre,color,imperio);
            revisarJugador(jugador);
            jugadores.add(jugador);
            imperio.asignarJugadorAlImperio(jugador);
            imperios.add(imperio);
        } else
            throw new ErrorSoloPuedenJugarDosPersonasAlMismoTiempo();
    }

    private void revisarJugador(Jugador jugadorARevisar) {
        for (Jugador jugador : jugadores)
            jugadorARevisar.verificarRespectoDe(jugador);
    }

    public boolean partidaTerminada(){
        for (Imperio imperio : imperios) {
            if(imperio.partidaTerminada())
                this.partidaFinalizada = true;
        }

        return this.partidaFinalizada;
    }
}
