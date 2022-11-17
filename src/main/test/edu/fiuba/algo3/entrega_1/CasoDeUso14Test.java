package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Acceso;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Pilon;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.PuertoEstelar;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.*;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoSePuedeConstruirEnEstaCasilla;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.GasRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Ataque.Danio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
    public void test02IntentarConstruirPuertoEstelarEnRangoDePilonPeroSobreMohoLanzaExcepcion() {
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

    @Test
    public void test03SePuedeConstruirReservaDeReproduccionEnAreaEnergizadaPeroConMoho() {
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

        Coordenada coordenadaReserva = new Coordenada(1,0);
        ReservaDeReproduccion unaReserva = new ReservaDeReproduccion();

        assertDoesNotThrow(() -> elMapa.construirEdificio(unaReserva, coordenadaReserva));
    }

    @Test
    public void test04SePuedeConstruirGuaridaEnAreaEnergizadaPeroConMoho() {
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

        Coordenada coordenadaGuarida = new Coordenada(1,0);
        Guarida unaGuarida = new Guarida();

        assertDoesNotThrow(() -> elMapa.construirEdificio(unaGuarida, coordenadaGuarida));
    }

    @Test
    public void test05SePuedeConstruirEspiralEnAreaEnergizadaPeroConMoho() {
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

        Coordenada coordenadaEspiral = new Coordenada(1,0);
        Espiral unEspiral = new Espiral();

        assertDoesNotThrow(() -> elMapa.construirEdificio(unEspiral, coordenadaEspiral));
    }

    @Test
    public void test06SePuedeConstruirExtractorEnAreaEnergizadaPeroConMoho() {
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

        Coordenada coordenadaExtractor = new Coordenada(1,0);
        Recurso gasDelImperio = new Recurso(0);
        Extractor unExtractor = new Extractor(gasDelImperio);
        elMapa.colocarMaterial(new GasRecolectable(),coordenadaExtractor);

        assertDoesNotThrow(() -> elMapa.construirEdificio(unExtractor, coordenadaExtractor));
    }

    @Test
    public void test07MohoNoSeExpandeEnAreaOcupadaPorEdificio() {
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

        // Destruyo Pilon
        Ataque unAtaque = new Ataque(new Danio(600));
        unPilon.aplicarAtaque(unAtaque);

        ReservaDeReproduccion unaReserva = new ReservaDeReproduccion();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(unaReserva, coordenadaPilon));
    }
}
