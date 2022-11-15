package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.EdificioProtoss.Asimilador;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.GasRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.GasBruto;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AsimiladorTest {
    @Test
    public void test01PuedoCrearUnExtractor(){
        Recurso gasDelImperio = new Recurso(0);
        MaterialBruto volcanDeGas = new GasBruto();

        Asimilador unAsimilador = new Asimilador(gasDelImperio);

        assertNotNull(unAsimilador);
    }

    @Test
    public void test02NoPuedoExtraerElGasDeUnAsimiladorQueNoEstaConstruidoEn5Turnos(){
        Protoss protoss = new Protoss();
        protoss.abastecerDeRecursos(new Recurso(100), new Recurso(0));
        Mapa mapa = Mapa.obtener();
        mapa.reiniciarMapa();
        Coordenada coordenadasGas = new Coordenada(0,0);

        mapa.colocarMaterial(new GasRecolectable(), coordenadasGas);
        protoss.construirAsimilador(coordenadasGas);
        for (int i = 0; i < 5; i++){
            protoss.terminarTurno();
        }
        assert(protoss.tienesEstaCantidadDeMineral(0));
    }

    @Test
    public void test03PuedoExtraerElGasDeUnAsimiladorQueEstaConstruidoEn6Turnos(){
        Protoss protoss = new Protoss();
        protoss.abastecerDeRecursos(new Recurso(100), new Recurso(0));
        Mapa mapa = Mapa.obtener();
        mapa.reiniciarMapa();
        Coordenada coordenadasGas = new Coordenada(0,0);

        mapa.colocarMaterial(new GasRecolectable(), coordenadasGas);
        protoss.construirAsimilador(coordenadasGas);
        for (int i = 0; i < 6; i++){
            protoss.terminarTurno();
        }
        assert(protoss.tienesEstaCantidadDeGas(20));
    }
}
