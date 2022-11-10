package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.excepciones.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class CasoDeUso2Test {

    Tablero tablero = new Tablero(1, 1);
    Zergs zergs = new Zergs(tablero, new Recurso(3000), new Recurso(3000));
    Protoss protoss = new Protoss(tablero, new Recurso(3000), new Recurso(3000));
    Coordenadas origen = new Coordenadas(0,0);

    // Testeo Construcciones Zerg

    @Test
    public void Test1ConstruyoUnCriaderoEIntentoUsarloSinQuePasenTurnosParaConstruirse() {
        NodoCompatible nodo = new NodoCompatible(new Moho(), new SinRecurso());
        Criadero criadero = new Criadero(tablero, nodo, origen, zergs);

        assertThrows(EdificioEnConstruccion.class, () -> criadero.criarZangano());
    }

    @Test
    public void Test2ConstruyoUnCriaderoEIntentoUsarloLuegoDeUnTurno() {
        NodoCompatible nodo = new NodoCompatible(new Moho(), new SinRecurso());
        Criadero criadero = new Criadero(tablero, nodo, origen, zergs);
        criadero.accionDeTurno();
        assertThrows(EdificioEnConstruccion.class, () -> criadero.criarZangano());

    }

    @Test
    public void Test3ConstruyoUnCriaderoEIntentoUsarloLuegoDeDosTurnos() {
        NodoCompatible nodo = new NodoCompatible(new Moho(), new SinRecurso());
        Criadero criadero = new Criadero(tablero, nodo, origen, zergs);

        criadero.accionDeTurno();
        criadero.accionDeTurno();
        assertThrows(EdificioEnConstruccion.class, () -> criadero.criarZangano());

    }


    @Test
    public void Test4ConstruyoUnCriaderoEIntentoUsarloLuegoDeTresTurnos() {
        NodoCompatible nodo = new NodoCompatible(new Moho(), new SinRecurso());
        Criadero criadero = new Criadero(tablero, nodo, origen, zergs);

        criadero.accionDeTurno();
        criadero.accionDeTurno();
        criadero.accionDeTurno();
        assertThrows(EdificioEnConstruccion.class, () -> criadero.criarZangano());

    }

    @Test
    public void Test5ConstruyoUnCriaderoEIntentoUsarloLuegoDeCuatroTurnosCuandoYaSeConstruyo() {
        NodoCompatible nodo = new NodoCompatible(new Moho(), new SinRecurso());
        Criadero criadero = new Criadero(tablero, nodo, origen, zergs);

        criadero.accionDeTurno();
        criadero.accionDeTurno();
        criadero.accionDeTurno();
        criadero.accionDeTurno();

        assertDoesNotThrow(() -> criadero.criarZangano());

    }

    @Test
    public void Test6ConstruyoUnExtractorYNoPuedoUsarlo(){
        Zangano zangano = new Zangano(tablero, origen, new Recurso());
        NodoCompatible nodo = new NodoCompatible(new Moho(), new VolcanGasVespeno());
        tablero.establecerRecurso(new VolcanGasVespeno(), origen);
        tablero.establecerTerreno(new Moho(), origen);

        Edificio extractor = zergs.construirExtractor(zangano);

        assertThrows(EdificioEnConstruccion.class, () -> extractor.estaActiva());
    }

    @Test
    public void Test7ConstruyoUnExtractorYNoPuedoUsarloFaltaUnTurno(){
        Zangano zangano = new Zangano(tablero, origen, new Recurso());
        NodoCompatible nodo = new NodoCompatible(new Moho(), new VolcanGasVespeno());
        tablero.establecerRecurso(new VolcanGasVespeno(), origen);
        tablero.establecerTerreno(new Moho(), origen);

        Edificio extractor = zergs.construirExtractor(zangano);

        for(int i = 0; i < 5; i++)
            extractor.accionDeTurno();

        assertThrows(EdificioEnConstruccion.class, () -> extractor.estaActiva());
    }

    @Test
    public void Test8ConstruyoUnExtractorYPuedoUsarloEnElTurnoCorrecto(){
        Zangano zangano = new Zangano(tablero, origen, new Recurso());
        NodoCompatible nodo = new NodoCompatible(new Moho(), new VolcanGasVespeno());
        tablero.establecerRecurso(new VolcanGasVespeno(), origen);
        tablero.establecerTerreno(new Moho(), origen);

        Edificio extractor = zergs.construirExtractor(zangano);

        for(int i = 0; i < 6; i++)
            extractor.accionDeTurno();

        assertDoesNotThrow(() -> extractor.estaActiva());
    }

    @Test
    public void Test9ConstruyoUnaReservaDeProduccionYNoPuedoUsarlo(){
        Zangano zangano = new Zangano(tablero, origen, new Recurso());
        NodoCompatible nodo = new NodoCompatible(new Moho(), new SinRecurso());
        tablero.establecerRecurso(new SinRecurso(), origen);
        tablero.establecerTerreno(new Moho(), origen);

        Edificio reserva = zergs.construirReservaDeReproduccion(zangano);

        assertThrows(EdificioEnConstruccion.class, () -> reserva.estaActiva());
    }

    @Test
    public void Test10ConstruyoUnaReservaDeProduccionYNoPuedoUsarloFaltaUnTurno(){
        Zangano zangano = new Zangano(tablero, origen, new Recurso());
        NodoCompatible nodo = new NodoCompatible(new Moho(), new SinRecurso());
        tablero.establecerRecurso(new SinRecurso(), origen);
        tablero.establecerTerreno(new Moho(), origen);

        Edificio reserva = zergs.construirReservaDeReproduccion(zangano);

        for(int i = 0; i < 11; i++)
            reserva.accionDeTurno();

        assertThrows(EdificioEnConstruccion.class, () -> reserva.estaActiva());
    }

    @Test
    public void Test11ConstruyoUnExtractorYPuedoUsarloEnElTurnoCorrecto(){
        Zangano zangano = new Zangano(tablero, origen, new Recurso());
        NodoCompatible nodo = new NodoCompatible(new Moho(), new SinRecurso());
        tablero.establecerRecurso(new SinRecurso(), origen);
        tablero.establecerTerreno(new Moho(), origen);

        Edificio reserva = zergs.construirReservaDeReproduccion(zangano);

        for(int i = 0; i < 12; i++)
            reserva.accionDeTurno();

        assertDoesNotThrow(() -> reserva.estaActiva());
    }

    @Test
    public void Test12ConstruyoUnaGuaridaYNoPuedoUsarlo(){
        Zangano zangano = new Zangano(tablero, origen, new Recurso());
        NodoCompatible nodo = new NodoCompatible(new Moho(), new SinRecurso());
        tablero.establecerRecurso(new SinRecurso(), origen);
        tablero.establecerTerreno(new Moho(), origen);

        Edificio guarida = zergs.construirGuarida(zangano);

        assertThrows(EdificioEnConstruccion.class, () -> guarida.estaActiva());
    }

    @Test
    public void Test13ConstruyoUnaGuaridaYNoPuedoUsarloFaltaUnTurno(){
        Zangano zangano = new Zangano(tablero, origen, new Recurso());
        NodoCompatible nodo = new NodoCompatible(new Moho(), new SinRecurso());
        tablero.establecerRecurso(new SinRecurso(), origen);
        tablero.establecerTerreno(new Moho(), origen);

        Edificio guarida = zergs.construirGuarida(zangano);

        for(int i = 0; i < 11; i++)
            guarida.accionDeTurno();

        assertThrows(EdificioEnConstruccion.class, () -> guarida.estaActiva());
    }

    @Test
    public void Test14ConstruyoUnaGuaridaEnElTurnoCorrecto(){
        Zangano zangano = new Zangano(tablero, origen, new Recurso());
        NodoCompatible nodo = new NodoCompatible(new Moho(), new SinRecurso());
        tablero.establecerRecurso(new SinRecurso(), origen);
        tablero.establecerTerreno(new Moho(), origen);

        Edificio guarida = zergs.construirGuarida(zangano);

        for(int i = 0; i < 12; i++)
            guarida.accionDeTurno();

        assertDoesNotThrow(() -> guarida.estaActiva());
    }

    @Test
    public void Test15ConstruyoUnaGuaridaYNoPuedoUsarlo(){
        Zangano zangano = new Zangano(tablero, origen, new Recurso());
        NodoCompatible nodo = new NodoCompatible(new Moho(), new SinRecurso());
        tablero.establecerRecurso(new SinRecurso(), origen);
        tablero.establecerTerreno(new Moho(), origen);

        Edificio espiral = zergs.construirEspiral(zangano);

        assertThrows(EdificioEnConstruccion.class, () -> espiral.estaActiva());
    }

    @Test
    public void Test16ConstruyoUnaGuaridaYNoPuedoUsarloFaltaUnTurno(){
        Zangano zangano = new Zangano(tablero, origen, new Recurso());
        NodoCompatible nodo = new NodoCompatible(new Moho(), new SinRecurso());
        tablero.establecerRecurso(new SinRecurso(), origen);
        tablero.establecerTerreno(new Moho(), origen);

        Edificio espiral = zergs.construirEspiral(zangano);

        for(int i = 0; i < 9; i++)
            espiral.accionDeTurno();

        assertThrows(EdificioEnConstruccion.class, () -> espiral.estaActiva());
    }

    @Test
    public void Test17ConstruyoUnaGuaridaEnElTurnoCorrecto(){
        Zangano zangano = new Zangano(tablero, origen, new Recurso());
        NodoCompatible nodo = new NodoCompatible(new Moho(), new SinRecurso());
        tablero.establecerRecurso(new SinRecurso(), origen);
        tablero.establecerTerreno(new Moho(), origen);

        Edificio espiral = zergs.construirEspiral(zangano);

        for(int i = 0; i < 10; i++)
            espiral.accionDeTurno();

        assertDoesNotThrow(() -> espiral.estaActiva());
    }

    // Testeo Construcciones Protoss

    @Test
    public void Test18ConstruyoUnNexoMineralYNoPuedoUsarlo(){
        NodoCompatible nodo = new NodoCompatible(new Neutro(), new NodoMineral());
        tablero.establecerRecurso(new NodoMineral(), origen);
        tablero.establecerTerreno(new Neutro(), origen);

        Edificio nexoMineral = protoss.construirNexoMineral(origen);

        assertThrows(EdificioEnConstruccion.class, () -> nexoMineral.estaActiva());
    }

    @Test
    public void Test19ConstruyoUnNexoMineralYNoPuedoUsarloFaltaUnTurno(){
        NodoCompatible nodo = new NodoCompatible(new Neutro(), new NodoMineral());
        tablero.establecerRecurso(new NodoMineral(), origen);
        tablero.establecerTerreno(new Neutro(), origen);

        Edificio nexoMineral = protoss.construirNexoMineral(origen);

        for(int i = 0; i < 3; i++)
            nexoMineral.accionDeTurno();

        assertThrows(EdificioEnConstruccion.class, () -> nexoMineral.estaActiva());
    }

    @Test
    public void Test20ConstruyoUnNexoMineralEnElTurnoCorrecto(){
        NodoCompatible nodo = new NodoCompatible(new Neutro(), new NodoMineral());
        tablero.establecerRecurso(new NodoMineral(), origen);
        tablero.establecerTerreno(new Neutro(), origen);

        Edificio nexoMineral = protoss.construirNexoMineral(origen);

        for(int i = 0; i < 4; i++)
            nexoMineral.accionDeTurno();

        assertDoesNotThrow(() -> nexoMineral.estaActiva());
    }

    @Test
    public void Test21ConstruyoUnPilonYNoPuedoUsarlo(){
        NodoCompatible nodo = new NodoCompatible(new Neutro(), new SinRecurso());
        tablero.establecerRecurso(new SinRecurso(), origen);
        tablero.establecerTerreno(new Neutro(), origen);

        Edificio pilon = protoss.construirPilon(origen);

        assertThrows(EdificioEnConstruccion.class, () -> pilon.estaActiva());
    }

    @Test
    public void Test22ConstruyoUnPilonYNoPuedoUsarloFaltaUnTurno(){
        NodoCompatible nodo = new NodoCompatible(new Neutro(), new SinRecurso());
        tablero.establecerRecurso(new SinRecurso(), origen);
        tablero.establecerTerreno(new Neutro(), origen);

        Edificio pilon = protoss.construirPilon(origen);

        for(int i = 0; i < 4; i++)
            pilon.accionDeTurno();

        assertThrows(EdificioEnConstruccion.class, () -> pilon.estaActiva());
    }

    @Test
    public void Test23ConstruyoUnPilonEnElTurnoCorrecto(){
        NodoCompatible nodo = new NodoCompatible(new Neutro(), new SinRecurso());
        tablero.establecerRecurso(new SinRecurso(), origen);
        tablero.establecerTerreno(new Neutro(), origen);

        Edificio pilon = protoss.construirPilon(origen);

        for(int i = 0; i < 5; i++)
            pilon.accionDeTurno();

        assertDoesNotThrow(() -> pilon.estaActiva());
    }

    @Test
    public void Test24ConstruyoUnAsimiladorYNoPuedoUsarlo(){
        NodoCompatible nodo = new NodoCompatible(new Neutro(), new VolcanGasVespeno());
        tablero.establecerRecurso(new VolcanGasVespeno(), origen);
        tablero.establecerTerreno(new Neutro(), origen);

        Edificio asimilador = protoss.construirAsimilador(origen);

        assertThrows(EdificioEnConstruccion.class, () -> asimilador.estaActiva());
    }

    @Test
    public void Test25ConstruyoUnAsimiladorYNoPuedoUsarloFaltaUnTurno(){
        NodoCompatible nodo = new NodoCompatible(new Neutro(), new VolcanGasVespeno());
        tablero.establecerRecurso(new VolcanGasVespeno(), origen);
        tablero.establecerTerreno(new Neutro(), origen);

        Edificio asimilador = protoss.construirAsimilador(origen);

        for(int i = 0; i < 5; i++)
            asimilador.accionDeTurno();

        assertThrows(EdificioEnConstruccion.class, () -> asimilador.estaActiva());
    }

    @Test
    public void Test26ConstruyoUnAsimiladorEnElTurnoCorrecto(){
        NodoCompatible nodo = new NodoCompatible(new Neutro(), new VolcanGasVespeno());
        tablero.establecerRecurso(new VolcanGasVespeno(), origen);
        tablero.establecerTerreno(new Neutro(), origen);

        Edificio asimilador = protoss.construirAsimilador(origen);

        for(int i = 0; i < 6; i++)
            asimilador.accionDeTurno();

        assertDoesNotThrow(() -> asimilador.estaActiva());
    }

    @Test
    public void Test27ConstruyoUnAccesoYNoPuedoUsarlo(){
        NodoCompatible nodo = new NodoCompatible(new Energia(), new SinRecurso());
        tablero.establecerRecurso(new SinRecurso(), origen);
        tablero.establecerTerreno(new Energia(), origen);

        Edificio acceso = protoss.construirAcceso();
        tablero.construir(acceso, origen);

        assertThrows(EdificioEnConstruccion.class, () -> acceso.estaActiva());
    }

    @Test
    public void Test28ConstruyoUnAccesoYNoPuedoUsarloFaltaUnTurno(){
        NodoCompatible nodo = new NodoCompatible(new Energia(), new SinRecurso());
        tablero.establecerRecurso(new SinRecurso(), origen);
        tablero.establecerTerreno(new Energia(), origen);

        Edificio acceso = protoss.construirAcceso();
        tablero.construir(acceso, origen);

        for(int i = 0; i < 7; i++)
            acceso.accionDeTurno();

        assertThrows(EdificioEnConstruccion.class, () -> acceso.estaActiva());
    }

    @Test
    public void Test29ConstruyoUnAccesoEnElTurnoCorrecto(){
        NodoCompatible nodo = new NodoCompatible(new Energia(), new SinRecurso());
        tablero.establecerRecurso(new SinRecurso(), origen);
        tablero.establecerTerreno(new Energia(), origen);

        Edificio acceso = protoss.construirAcceso();
        tablero.construir(acceso, origen);

        for(int i = 0; i < 8; i++)
            acceso.accionDeTurno();

        assertDoesNotThrow(() -> acceso.estaActiva());
    }

    @Test
    public void Test30ConstruyoUnPuertoEstelarYNoPuedoUsarlo(){
        NodoCompatible nodo = new NodoCompatible(new Energia(), new SinRecurso());
        tablero.establecerRecurso(new SinRecurso(), origen);
        tablero.establecerTerreno(new Energia(), origen);

        Edificio puertoEstelar = protoss.construirPuertoEstelar();
        tablero.construir(puertoEstelar, origen);

        assertThrows(EdificioEnConstruccion.class, () -> puertoEstelar.estaActiva());
    }

    @Test
    public void Test31ConstruyoUnPuertoEstelarYNoPuedoUsarloFaltaUnTurno(){
        NodoCompatible nodo = new NodoCompatible(new Energia(), new SinRecurso());
        tablero.establecerRecurso(new SinRecurso(), origen);
        tablero.establecerTerreno(new Energia(), origen);

        Edificio puertoEstelar = protoss.construirPuertoEstelar();
        tablero.construir(puertoEstelar, origen);

        for(int i = 0; i < 9; i++)
            puertoEstelar.accionDeTurno();

        assertThrows(EdificioEnConstruccion.class, () -> puertoEstelar.estaActiva());
    }

    @Test
    public void Test32ConstruyoUnPuertoEstelarEnElTurnoCorrecto(){
        NodoCompatible nodo = new NodoCompatible(new Energia(), new SinRecurso());
        tablero.establecerRecurso(new SinRecurso(), origen);
        tablero.establecerTerreno(new Energia(), origen);

        Edificio puertoEstelar = protoss.construirPuertoEstelar();
        tablero.construir(puertoEstelar, origen);

        for(int i = 0; i < 10; i++)
            puertoEstelar.accionDeTurno();

        assertDoesNotThrow(() -> puertoEstelar.estaActiva());
    }
}
