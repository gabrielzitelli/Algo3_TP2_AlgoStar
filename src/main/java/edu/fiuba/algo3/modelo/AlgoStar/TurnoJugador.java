package edu.fiuba.algo3.modelo.AlgoStar;

import java.util.Objects;

public class TurnoJugador implements Turno {

    private final Jugador jugadorDeTurno;
    private final AdministradorDeJugadores jugadores;

    public TurnoJugador(Jugador jugadorDeTurno, AdministradorDeJugadores jugadores) {
        this.jugadorDeTurno = jugadorDeTurno;
        this.jugadores = jugadores;
    }

    @Override
    public Turno terminarTurno() {
        jugadorDeTurno.conseguirImperio().terminarTurno();

        String jugadorInfo = jugadorDeTurno.toString();
        Logger.obtener().log("Ha finalizado el turno del jugador " + jugadorDeTurno.conseguirNombre() +
                " que juega con el imperio " + obtenerAtributoDeString(jugadorInfo, "imperio") + ". Termina el turno con los siguientes atributos: " +
                obtenerAtributoDeString(jugadorInfo, "mineral") + " de minerales, " +  obtenerAtributoDeString(jugadorInfo, "gas") + " de gas, " +
                obtenerAtributoDeString(jugadorInfo, "poblacion") + " de poblacion, y " + obtenerAtributoDeString(jugadorInfo, "suministro") +
                " de suministro.");

        Jugador siguienteJugador;

        siguienteJugador = jugadores.conseguirSiguienteJugador();

        if (siguienteJugador.conseguirNombre().equals(jugadorDeTurno.conseguirNombre()))
            return new PartidaTerminada(jugadorDeTurno);

        return new TurnoJugador(siguienteJugador, jugadores);
    }

    @Override
    public boolean partidaTerminada() {
        return false;
    }

    @Override
    public Jugador jugadorActual() {
        return jugadorDeTurno;
    }

    protected String obtenerAtributoDeString(String stringFormateado, String tipoAtributo){
        String[] tokensJugador = stringFormateado.split(" ");
        String atributoDeseado = null;

        for (int i = 0; i < tokensJugador.length; i++) {
            if(Objects.equals(tokensJugador[i], tipoAtributo))
                atributoDeseado = tokensJugador[i + 1];
        }

        return atributoDeseado.substring(0, 1).toUpperCase() + atributoDeseado.substring(1);
    }
}
