package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.excepciones.CantidadDeRecursoInsuficiente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso8Test {

    Tablero tablero = new Tablero(1,1);

    @Test
    public void IntentarConstruirCriaderoSinLosRecursosNecesariosLanzaExcepcion() {
        Zergs zergs = new Zergs(new Recurso(0), new Recurso(0));
        Zangano zangano = new Zangano(new Coordenadas(0,0), new Recurso());

        assertThrows(CantidadDeRecursoInsuficiente.class, () -> zergs.construirCriadero(zangano));
    }

    @Test
    public void IntentarConstruirExtractorSinLosRecursosNecesariosLanzaExcepcion() {
        Zergs zergs = new Zergs(new Recurso(0), new Recurso(0));
        Zangano zangano = new Zangano(new Coordenadas(0,0), new Recurso());

        assertThrows(CantidadDeRecursoInsuficiente.class, () -> zergs.construirExtractor(zangano));
    }

    @Test
    public void IntentarConstruirReservaDeReproduccionSinLosRecursosNecesariosLanzaExcepcion() {
        Zergs zergs = new Zergs(new Recurso(0), new Recurso(0));
        Zangano zangano = new Zangano(new Coordenadas(0,0), new Recurso());

        assertThrows(CantidadDeRecursoInsuficiente.class, () -> zergs.construirReservaDeReproduccion(zangano));
    }

    @Test
    public void IntentarConstruirGuaridaSinLosRecursosNecesariosLanzaExcepcion() {
        Zergs zergs = new Zergs(new Recurso(0), new Recurso(0));
        Zangano zangano = new Zangano(new Coordenadas(0,0), new Recurso());

        assertThrows(CantidadDeRecursoInsuficiente.class, () -> zergs.construirGuarida(zangano));
    }

    @Test
    public void IntentarConstruirEspiralSinLosRecursosNecesariosLanzaExcepcion() {
        Zergs zergs = new Zergs(new Recurso(0), new Recurso(0));
        Zangano zangano = new Zangano(new Coordenadas(0,0), new Recurso());

        assertThrows(CantidadDeRecursoInsuficiente.class, () -> zergs.construirEspiral(zangano));
    }

    @Test
    public void IntentarConstruirNexoMineralSinLosRecursosNecesariosLanzaExcepcion() {
        Protoss protoss = new Protoss(tablero, new Recurso(0), new Recurso(0));

        assertThrows(CantidadDeRecursoInsuficiente.class, () -> {
            protoss.construirNexoMineral(new Coordenadas(0, 0));
        });
    }

    @Test
    public void IntentarConstruirPilonSinLosRecursosNecesariosLanzaExcepcion() {
        Protoss protoss = new Protoss(tablero, new Recurso(0), new Recurso(0));

        assertThrows(CantidadDeRecursoInsuficiente.class, () -> protoss.construirPilon());
    }

    @Test
    public void IntentarConstruirAsimiladorSinLosRecursosNecesariosLanzaExcepcion() {
        Protoss protoss = new Protoss(tablero, new Recurso(0), new Recurso(0));
        assertThrows(CantidadDeRecursoInsuficiente.class, () -> protoss.construirAsimilador(new Coordenadas(0,0)));
    }

    @Test
    public void IntentarConstruirAccesoSinLosRecursosNecesariosLanzaExcepcion() {
        Protoss protoss = new Protoss(tablero, new Recurso(0), new Recurso(0));

        assertThrows(CantidadDeRecursoInsuficiente.class, () -> protoss.construirAcceso());
    }

    @Test
    public void IntentarConstruirPuertoEstelarSinLosRecursosNecesariosLanzaExcepcion() {
        Protoss protoss = new Protoss(tablero, new Recurso(0), new Recurso(0));

        assertThrows(CantidadDeRecursoInsuficiente.class, () -> protoss.construirPuertoEstelar());
    }
}
