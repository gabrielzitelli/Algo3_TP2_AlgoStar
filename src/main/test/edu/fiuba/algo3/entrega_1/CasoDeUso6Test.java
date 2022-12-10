package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.*;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoSePuedeConstruirEnEstaCasilla;
import edu.fiuba.algo3.modelo.Mapa.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso6Test {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01MohoAumentaEn1DespuesDe2TurnosDelCriaderoEstandoOperativoYSePuedeConstruirEdificioZergDentroDelRango() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        Coordenada coordenadaReserva = new Coordenada(6,0);
        Criadero criadero = new Criadero();

        elMapa.colocarOcupable(criadero, coordenadaCriadero);

        for (int i = 0; i < 4; i++)
            criadero.pasarTurno();

        criadero.pasarTurno();
        criadero.pasarTurno();

        assertDoesNotThrow(() -> elMapa.colocarOcupable(new ReservaDeReproduccion(), coordenadaReserva));
    }

    @Test
    public void test02MohoAumentaEn1DespuesDe2TurnosDelCriaderoEstandoOperativoYNoSePuedeConstruirEdificioZergFueraDelRango() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        Coordenada coordenadaReserva = new Coordenada(7,0);
        Criadero criadero = new Criadero();

        elMapa.colocarOcupable(criadero, coordenadaCriadero);

        for (int i = 0; i < 4; i++)
            criadero.pasarTurno();

        criadero.pasarTurno();
        criadero.pasarTurno();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.colocarOcupable(new ReservaDeReproduccion(), coordenadaReserva));
    }

    @Test
    public void test03MohoAumentaEn2DespuesDe4TurnosDelCriaderoEstandoOperativoYSePuedeConstruirEdificioZergDentroDelRango() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        Coordenada coordenadaReserva = new Coordenada(7,0);
        Criadero criadero = new Criadero();

        elMapa.colocarOcupable(criadero, coordenadaCriadero);

        //Se construye el criadero
        for (int i = 0; i < 4; i++)
            criadero.pasarTurno();

        //Se expande 2 veces el moho
        for (int i = 0; i < 4; i++)
            criadero.pasarTurno();

        assertDoesNotThrow(() -> elMapa.colocarOcupable(new ReservaDeReproduccion(), coordenadaReserva));
    }

    @Test
    public void test04MohoAumentaEn2DespuesDe4TurnosDelCriaderoEstandoOperativoYNoSePuedeConstruirEdificioZergFueraDelRango() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        Coordenada coordenadaReserva = new Coordenada(8,0);
        Criadero criadero = new Criadero();

        elMapa.colocarOcupable(criadero, coordenadaCriadero);

        //Se construye el criadero
        for (int i = 0; i < 4; i++)
            criadero.pasarTurno();

        //Se expande 2 veces el moho
        for (int i = 0; i < 4; i++)
            criadero.pasarTurno();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.colocarOcupable(new ReservaDeReproduccion(), coordenadaReserva));
    }

    @Test
    public void test05MohoNoAumentaEn1DespuesDe1TurnoDelCriaderoEstandoOperativoYNoSePuedeConstruirEdificioZergFueraDelRango() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        Coordenada coordenadaReserva = new Coordenada(6,0);
        Criadero criadero = new Criadero();

        elMapa.colocarOcupable(criadero, coordenadaCriadero);

        for (int i = 0; i < 4; i++)
            criadero.pasarTurno();

        criadero.pasarTurno();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.colocarOcupable(new ReservaDeReproduccion(), coordenadaReserva));
    }
}
