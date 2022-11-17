package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Criadero;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.ReservaDeReproduccion;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoSePuedeConstruirEnEstaCasilla;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso6Test {

    @Test
    public void MohoAumentaEn1DespuesDe2TurnosDelCriaderoEstandoOperativoYSePuedeConstruirEdificioZergDentroDelRango() {
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        Coordenada coordenadaReserva = new Coordenada(6,0);

        Criadero criadero = new Criadero();

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();
        elMapa.construirEdificio(criadero, coordenadaCriadero);

        int turnosConstruccionCriadero = 4;
        for (int i = 0; i < turnosConstruccionCriadero; i++) {
            criadero.pasarTurno();
        }

        criadero.pasarTurno();
        criadero.pasarTurno();

        assertDoesNotThrow(() -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenadaReserva));
    }

    @Test
    public void MohoAumentaEn1DespuesDe2TurnosDelCriaderoEstandoOperativoYNoSePuedeConstruirEdificioZergFueraDelRango() {
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        Coordenada coordenadaReserva = new Coordenada(7,0);

        Criadero criadero = new Criadero();

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();
        elMapa.construirEdificio(criadero, coordenadaCriadero);

        int turnosConstruccionCriadero = 4;
        for (int i = 0; i < turnosConstruccionCriadero; i++) {
            criadero.pasarTurno();
        }

        criadero.pasarTurno();
        criadero.pasarTurno();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenadaReserva));
    }

    @Test
    public void MohoAumentaEn2DespuesDe4TurnosDelCriaderoEstandoOperativoYSePuedeConstruirEdificioZergDentroDelRango() {
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        Coordenada coordenadaReserva = new Coordenada(7,0);

        Criadero criadero = new Criadero();

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();
        elMapa.construirEdificio(criadero, coordenadaCriadero);

        int turnosConstruccionCriadero = 4;
        for (int i = 0; i < turnosConstruccionCriadero; i++) {
            criadero.pasarTurno();
        }

        criadero.pasarTurno();
        criadero.pasarTurno();
        criadero.pasarTurno();
        criadero.pasarTurno();

        assertDoesNotThrow(() -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenadaReserva));
    }

    @Test
    public void MohoAumentaEn2DespuesDe4TurnosDelCriaderoEstandoOperativoYNoSePuedeConstruirEdificioZergFueraDelRango() {
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        Coordenada coordenadaReserva = new Coordenada(8,0);

        Criadero criadero = new Criadero();

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();
        elMapa.construirEdificio(criadero, coordenadaCriadero);

        int turnosConstruccionCriadero = 4;
        for (int i = 0; i < turnosConstruccionCriadero; i++) {
            criadero.pasarTurno();
        }

        criadero.pasarTurno();
        criadero.pasarTurno();
        criadero.pasarTurno();
        criadero.pasarTurno();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenadaReserva));
    }

    @Test
    public void MohoNoAumentaEn1DespuesDe1TurnoDelCriaderoEstandoOperativoYNoSePuedeConstruirEdificioZergFueraDelRango() {
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        Coordenada coordenadaReserva = new Coordenada(6,0);

        Criadero criadero = new Criadero();

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();
        elMapa.construirEdificio(criadero, coordenadaCriadero);

        int turnosConstruccionCriadero = 4;
        for (int i = 0; i < turnosConstruccionCriadero; i++) {
            criadero.pasarTurno();
        }

        criadero.pasarTurno();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenadaReserva));
    }
}
