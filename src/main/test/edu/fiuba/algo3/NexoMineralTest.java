package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.EdificioProtoss.NexoMineral;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.MineralRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NexoMineralTest {

    @Test
    public void test01PuedoCrearUnNexoMineral(){
        Recurso mineralesDelImperio = new Recurso(0);

        NexoMineral unNexo = new NexoMineral(mineralesDelImperio);

        assertNotNull(unNexo);
    }

    @Test
    public void test02NoPuedoExtraerElMineralDeUnNexoMineralQueNoEstaConstruidoEn3Turnos(){
        Protoss protoss = new Protoss();
        protoss.abastecerDeRecursos(new Recurso(50), new Recurso(0));
        Mapa mapa = Mapa.obtener();
        mapa.reiniciarMapa();
        Coordenada coordenadasMIneral = new Coordenada(0,0);

        mapa.colocarMaterial(new MineralRecolectable(), coordenadasMIneral);
        protoss.construirNexoMineral(coordenadasMIneral);
        for (int i = 0; i < 3; i++){
            protoss.terminarTurno();
        }
        assert(protoss.tienesEstaCantidadDeMineral(0));
    }

    @Test
    public void test03PuedoExtraerElMineralDeUnNexoMineralQueEstaConstruidoEn4Turnos(){
        Protoss protoss = new Protoss();
        protoss.abastecerDeRecursos(new Recurso(50), new Recurso(0));
        Mapa mapa = Mapa.obtener();
        mapa.reiniciarMapa();
        Coordenada coordenadaMineral = new Coordenada(0,0);

        mapa.colocarMaterial(new MineralRecolectable(), coordenadaMineral);
        protoss.construirNexoMineral(coordenadaMineral);
        for (int i = 0; i < 4; i++){
            protoss.terminarTurno();
        }
        assert(protoss.tienesEstaCantidadDeMineral(10));
    }
}
