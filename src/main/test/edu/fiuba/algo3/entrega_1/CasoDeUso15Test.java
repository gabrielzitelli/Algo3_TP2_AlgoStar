package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.excepciones.CriaderoSinLarvas;
import edu.fiuba.algo3.modelo.excepciones.MaximoZanganosAlcanzados;
import edu.fiuba.algo3.modelo.excepciones.RecursosAgotados;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso15Test {

    @Test
    public void Test1SeConstruyeUnNexoMineralSobreUnNodoMineralEIntentoRecolectarLuegoDeGastarlo(){
        Tablero tablero = new Tablero(1, 1);
        tablero.establecerRecurso(new NodoMineral(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Neutro(), new Coordenadas(0,0));

        Protoss protoss = new Protoss(tablero ,new Recurso(50), new Recurso(0));
        Edificio nexoMineral = protoss.construirNexoMineral(new Coordenadas(0, 0));

        nexoMineral.accionDeTurno();
        nexoMineral.accionDeTurno();
        nexoMineral.accionDeTurno();
        nexoMineral.accionDeTurno();
        for ( int i = 1; i <= 200 ; i++){
            nexoMineral.accionDeTurno();
        }

        assertThrows(RecursosAgotados.class, () -> nexoMineral.accionDeTurno());

    }

    @Test
    public void Test2SeConstruyeUnAsimiladorSobreVolcanGastoElGeiserEIntentoRecolectarDespuesDeGastarlo(){
        Tablero tablero = new Tablero(1, 1);
        tablero.establecerRecurso(new VolcanGasVespeno(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Neutro(), new Coordenadas(0,0));

        Protoss protoss = new Protoss(tablero ,new Recurso(100), new Recurso(0));
        Edificio asimilador = protoss.construirAsimilador(new Coordenadas(0,0));

        asimilador.accionDeTurno();
        asimilador.accionDeTurno();
        asimilador.accionDeTurno();
        asimilador.accionDeTurno();
        asimilador.accionDeTurno();
        asimilador.accionDeTurno();
        for ( int i = 1; i <= 250 ; i++){
            asimilador.accionDeTurno();
        }

        assertThrows(RecursosAgotados.class, () -> asimilador.accionDeTurno());

    }
    @Test
    public void Test3SeConstruyeUnExtractorSobreVolcanGastoElGeiserEIntentoRecolectarDespuesDeGastarlo(){
        Tablero tablero = new Tablero(1, 1);
        tablero.establecerRecurso(new VolcanGasVespeno(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Moho(), new Coordenadas(0,0));

        Zangano zangano1 = new Zangano(tablero, new Coordenadas(0,0), new Recurso());
        Zangano zangano2 = new Zangano(tablero, new Coordenadas(0,0), new Recurso());
        Zangano zangano3 = new Zangano(tablero, new Coordenadas(0,0), new Recurso());
        Zangano zangano4 = new Zangano(tablero, new Coordenadas(0,0), new Recurso());
        Zergs zergs = new Zergs(tablero ,new Recurso(100), new Recurso(0));

        NodoCompatible nodoCompatible = new NodoCompatible(new Moho(), new VolcanGasVespeno());
        Extractor extractor = new Extractor(nodoCompatible, new Recurso());
        tablero.construir(extractor,zangano1.getPosicion());

        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.contratarZangano(zangano2);
        extractor.contratarZangano(zangano3);
        extractor.contratarZangano(zangano4);

        for ( int i = 1; i <= 166 ; i++){
            extractor.accionDeTurno();
        }

        assertThrows(RecursosAgotados.class, () -> extractor.accionDeTurno());

    }



}
