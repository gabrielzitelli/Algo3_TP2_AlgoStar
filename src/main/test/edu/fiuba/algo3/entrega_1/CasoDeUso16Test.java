package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Tablero.Moho;
import edu.fiuba.algo3.modelo.Tablero.Neutro;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.VolcanGasVespeno;
import edu.fiuba.algo3.modelo.excepciones.CasillaOcupada;
import edu.fiuba.algo3.modelo.excepciones.TerrenoNoCompatibleConEdificio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso16Test {


    @Test
    public void Test1noSePuedeConstruirSobreUnVolcanConUnaEdificioEncima(){
        Tablero tablero = new Tablero(1, 1);
        tablero.establecerRecurso(new VolcanGasVespeno(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Neutro(), new Coordenadas(0,0));

        Protoss protoss = new Protoss(tablero ,new Recurso(5000), new Recurso(0));
        Edificio asimilador = protoss.construirAsimilador(new Coordenadas(0, 0));

        assertThrows(CasillaOcupada.class, () -> protoss.construirAsimilador(new Coordenadas(0, 0)));
    }

    @Test
    public void Test2noSePuedeConstruirSobreUnVolcanConUnaEdificioEncimaEnemigo(){
        Tablero tablero = new Tablero(1, 1);
        tablero.establecerRecurso(new VolcanGasVespeno(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Moho(), new Coordenadas(0,0));

        Zergs zergs = new Zergs(tablero, new Recurso(6000), new Recurso(0));
        Zangano zangano = new Zangano(tablero, new Coordenadas(0,0), new Recurso());
        Edificio extractor = zergs.construirExtractor(zangano);

        Protoss protoss = new Protoss(tablero ,new Recurso(5000), new Recurso(0));
        assertThrows(TerrenoNoCompatibleConEdificio.class, () -> protoss.construirAsimilador(new Coordenadas(0, 0)));
    }
}
