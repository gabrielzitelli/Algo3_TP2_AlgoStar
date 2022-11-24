package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.*;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.*;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoSePuedeConstruirEnEstaCasilla;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Mapa.*;
import edu.fiuba.algo3.modelo.Ataque.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso14Test {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01IntentarConstruirAccesoEnRangoDePilonPeroSobreMohoLanzaExcepcion() {
        Mapa elMapa = Mapa.obtener();
        Pilon unPilon = new Pilon();
        Criadero unCriadero = new Criadero();
        Acceso unAcceso = new Acceso();
        Coordenada coordenadaPilon = new Coordenada(0,0);
        Coordenada coordenadaCriadero = new Coordenada(4,0);
        Coordenada coordenadaAcceso = new Coordenada(1,0);
        unPilon.asignarSuministro(new Suministro(0));

        elMapa.construirEdificio(unPilon, coordenadaPilon);
        //Se construye el pilon
        for (int i = 0; i < 5; i++)
            unPilon.pasarTurno();

        elMapa.construirEdificio(unCriadero, coordenadaCriadero);
        //Se construye el criadero
        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(unAcceso, coordenadaAcceso));
    }

    @Test
    public void test02IntentarConstruirPuertoEstelarEnRangoDePilonPeroSobreMohoLanzaExcepcion() {
        Mapa elMapa = Mapa.obtener();
        Pilon unPilon = new Pilon();
        Criadero unCriadero = new Criadero();
        PuertoEstelar unPuertoEstelar = new PuertoEstelar();
        Coordenada coordenadaPilon = new Coordenada(0,0);
        Coordenada coordenadaCriadero = new Coordenada(4,0);
        Coordenada coordenadaAcceso = new Coordenada(1,0);
        unPilon.asignarSuministro(new Suministro(0));

        elMapa.construirEdificio(unPilon, coordenadaPilon);
        //Se construye el pilon
        for (int i = 0; i < 5; i++)
            unPilon.pasarTurno();

        elMapa.construirEdificio(unCriadero, coordenadaCriadero);
        //Se construye el criadero
        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(unPuertoEstelar, coordenadaAcceso));
    }

    @Test
    public void test03SePuedeConstruirReservaDeReproduccionEnAreaEnergizadaPeroConMoho() {
        Mapa elMapa = Mapa.obtener();
        Pilon unPilon = new Pilon();
        Criadero unCriadero = new Criadero();
        ReservaDeReproduccion unaReserva = new ReservaDeReproduccion();
        Coordenada coordenadaPilon = new Coordenada(0,0);
        Coordenada coordenadaCriadero = new Coordenada(4,0);
        Coordenada coordenadaReserva = new Coordenada(1,0);
        unPilon.asignarSuministro(new Suministro(0));

        elMapa.construirEdificio(unPilon, coordenadaPilon);
        //Se construye el pilon
        for (int i = 0; i < 5; i++)
            unPilon.pasarTurno();

        elMapa.construirEdificio(unCriadero, coordenadaCriadero);
        //Se construye el criadero
        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        assertDoesNotThrow(() -> elMapa.construirEdificio(unaReserva, coordenadaReserva));
    }

    @Test
    public void test04SePuedeConstruirGuaridaEnAreaEnergizadaPeroConMoho() {
        Mapa elMapa = Mapa.obtener();
        Pilon unPilon = new Pilon();
        Criadero unCriadero = new Criadero();
        Guarida unaGuarida = new Guarida();
        Coordenada coordenadaPilon = new Coordenada(0,0);
        Coordenada coordenadaCriadero = new Coordenada(4,0);
        Coordenada coordenadaGuarida = new Coordenada(1,0);
        unPilon.asignarSuministro(new Suministro(0));

        elMapa.construirEdificio(unPilon, coordenadaPilon);
        //Se construye el pilon
        for (int i = 0; i < 5; i++) {
            unPilon.pasarTurno();
        }

        elMapa.construirEdificio(unCriadero, coordenadaCriadero);
        //Se construye el criadero
        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        assertDoesNotThrow(() -> elMapa.construirEdificio(unaGuarida, coordenadaGuarida));
    }

    @Test
    public void test05SePuedeConstruirEspiralEnAreaEnergizadaPeroConMoho() {
        Mapa elMapa = Mapa.obtener();
        Pilon unPilon = new Pilon();
        Criadero unCriadero = new Criadero();
        Espiral unEspiral = new Espiral();
        Coordenada coordenadaPilon = new Coordenada(0,0);
        Coordenada coordenadaCriadero = new Coordenada(4,0);
        Coordenada coordenadaEspiral = new Coordenada(1,0);
        unPilon.asignarSuministro(new Suministro(0));

        elMapa.construirEdificio(unPilon, coordenadaPilon);
        //Se construye el pilon
        for (int i = 0; i < 5; i++)
            unPilon.pasarTurno();

        elMapa.construirEdificio(unCriadero, coordenadaCriadero);
        //Se construye el criadero
        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        assertDoesNotThrow(() -> elMapa.construirEdificio(unEspiral, coordenadaEspiral));
    }

    @Test
    public void test06SePuedeConstruirExtractorEnAreaEnergizadaPeroConMoho() {
        Mapa elMapa = Mapa.obtener();
        Pilon unPilon = new Pilon();
        Criadero unCriadero = new Criadero();
        Recurso gasDelImperio = new Gas(0);
        Extractor unExtractor = new Extractor(gasDelImperio);
        Coordenada coordenadaPilon = new Coordenada(0,0);
        Coordenada coordenadaCriadero = new Coordenada(4,0);
        Coordenada coordenadaExtractor = new Coordenada(1,0);
        unPilon.asignarSuministro(new Suministro(0));

        elMapa.construirEdificio(unPilon, coordenadaPilon);
        //Se construye el pilon
        for (int i = 0; i < 5; i++)
            unPilon.pasarTurno();

        elMapa.construirEdificio(unCriadero, coordenadaCriadero);
        //Se construye el criadero
        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        elMapa.colocarMaterial(new GasRecolectable(),coordenadaExtractor);

        assertDoesNotThrow(() -> elMapa.construirEdificio(unExtractor, coordenadaExtractor));
    }

    @Test
    public void test07MohoNoSeExpandeEnAreaOcupadaPorEdificio() {
        Mapa elMapa = Mapa.obtener();
        Pilon unPilon = new Pilon();
        Criadero unCriadero = new Criadero();
        ReservaDeReproduccion unaReserva = new ReservaDeReproduccion();
        Coordenada coordenadaPilon = new Coordenada(0,0);
        Coordenada coordenadaCriadero = new Coordenada(4,0);
        Ataque unAtaque = new Ataque(new Danio(600));
        unPilon.asignarSuministro(new Suministro(0));

        elMapa.construirEdificio(unPilon, coordenadaPilon);
        //Se construye el pilon
        for (int i = 0; i < 5; i++)
            unPilon.pasarTurno();

        elMapa.construirEdificio(unCriadero, coordenadaCriadero);
        //Se construye el criadero
        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        // Destruyo Pilon
        unPilon.recibirAtaque(unAtaque);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(unaReserva, coordenadaPilon));
    }
}
