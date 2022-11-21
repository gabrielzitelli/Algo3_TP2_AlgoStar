package edu.fiuba.algo3.testDeClases.edificiosTests;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.NexoMineral;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.MineralRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NexoMineralTest {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01PuedoCrearUnNexoMineral(){
        Recurso mineralesDelImperio = new Mineral(0);
        NexoMineral unNexo = new NexoMineral(mineralesDelImperio);

        assertNotNull(unNexo);
    }

    @Test
    public void test02NoPuedoExtraerElMineralDeUnNexoMineralQueNoEstaConstruidoEn3Turnos(){
        Mapa mapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        Coordenada coordenadasMIneral = new Coordenada(0,0);

        mapa.colocarMaterial(new MineralRecolectable(), coordenadasMIneral);
        protoss.abastecerDeRecursos(new Mineral(50), new Gas(0));
        protoss.construirNexoMineral(coordenadasMIneral);

        for (int i = 0; i < 3; i++)
            protoss.terminarTurno();

        assertTrue(protoss.tienesEstaCantidadDeMineral(0));
    }

    @Test
    public void test03PuedoExtraerElMineralDeUnNexoMineralQueEstaConstruidoEn4Turnos(){
        Mapa mapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        Coordenada coordenadaMineral = new Coordenada(0,0);

        mapa.colocarMaterial(new MineralRecolectable(), coordenadaMineral);
        protoss.abastecerDeRecursos(new Mineral(50), new Gas(0));
        protoss.construirNexoMineral(coordenadaMineral);

        for (int i = 0; i < 4; i++)
            protoss.terminarTurno();

        assertTrue(protoss.tienesEstaCantidadDeMineral(10));
    }
}
