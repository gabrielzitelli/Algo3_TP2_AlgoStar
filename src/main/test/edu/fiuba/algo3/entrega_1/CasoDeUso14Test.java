package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.EdificioProtoss.Acceso;
import edu.fiuba.algo3.modelo.EdificioProtoss.Pilon;
import edu.fiuba.algo3.modelo.EdificioProtoss.PuertoEstelar;
import edu.fiuba.algo3.modelo.EdificioZerg.Criadero;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoSePuedeConstruirEnEstaCasilla;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso14Test {

    @Test
    public void test01IntentarConstruirAccesoEnRangoDePilonPeroSobreMohoLanzaExcepcion() {
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        Coordenada coordenadaPilon = new Coordenada(0,0);
        Pilon unPilon = new Pilon();
        elMapa.construirEdificio(unPilon, coordenadaPilon);
        int turnosParaConstruirPilon = 5;
        for (int i = 0; i < turnosParaConstruirPilon; i++) {
            unPilon.pasarTurno();
        }

        Coordenada coordenadaCriadero = new Coordenada(4,0);
        Criadero unCriadero = new Criadero();
        elMapa.construirEdificio(unCriadero, coordenadaCriadero);
        int turnosParaConstruirCriadero = 4;
        for (int i = 0; i < turnosParaConstruirCriadero; i++) {
            unCriadero.pasarTurno();
        }

        Coordenada coordenadaAcceso = new Coordenada(1,0);
        Acceso unAcceso = new Acceso();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(unAcceso, coordenadaAcceso));
    }


    @Test
    public void test01IntentarConstruirPuertoEstelarEnRangoDePilonPeroSobreMohoLanzaExcepcion() {
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        Coordenada coordenadaPilon = new Coordenada(0,0);
        Pilon unPilon = new Pilon();
        elMapa.construirEdificio(unPilon, coordenadaPilon);
        int turnosParaConstruirPilon = 5;
        for (int i = 0; i < turnosParaConstruirPilon; i++) {
            unPilon.pasarTurno();
        }

        Coordenada coordenadaCriadero = new Coordenada(4,0);
        Criadero unCriadero = new Criadero();
        elMapa.construirEdificio(unCriadero, coordenadaCriadero);
        int turnosParaConstruirCriadero = 4;
        for (int i = 0; i < turnosParaConstruirCriadero; i++) {
            unCriadero.pasarTurno();
        }

        Coordenada coordenadaAcceso = new Coordenada(1,0);
        PuertoEstelar unPuertoEstelar = new PuertoEstelar();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(unPuertoEstelar, coordenadaAcceso));
    }
}
