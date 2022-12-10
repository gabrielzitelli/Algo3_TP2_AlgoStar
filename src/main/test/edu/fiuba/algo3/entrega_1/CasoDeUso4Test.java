package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaCriadero;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaExtractor;
import edu.fiuba.algo3.modelo.Excepciones.ErrorExtratorNoPuedeTenerMasDe3ZanganosAlMismoTiempo;
import edu.fiuba.algo3.modelo.Imperio.*;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Mapa.*;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CasoDeUso4Test {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01ExtractorSinZanganosNoProduceGas() {
        Mapa mapa = Mapa.obtener();
        Zerg zerg = new Zerg();
        Coordenada coordenadasGas = new Coordenada(0,0);

        mapa.colocarMaterial(new GasRecolectable(), coordenadasGas);
        zerg.abastecerDeRecursos(new Mineral(300), new Gas(0));
        zerg.construirEdificio(new FabricaCriadero(), new Coordenada(1,1));

        for (int i = 0; i < 5; i++)
            zerg.terminarTurno();

        zerg.construirEdificio(new FabricaExtractor(), coordenadasGas);

        //Construimos el extractor
        for (int i = 0; i < 6; i++)
            zerg.terminarTurno();

        Edificio extractor = zerg.conseguirEdificio(coordenadasGas);
        //extractor.contratarUnidad(zangano);
        zerg.terminarTurno();

        assertTrue(zerg.tienesEstaCantidadDeGas(0));
    }

    @Test
    public void test02ExtractorConUnZanganoProduce10Gas() {
        Mapa mapa = Mapa.obtener();
        Zerg zerg = new Zerg();
        Coordenada coordenadasGas = new Coordenada(0,0);

        mapa.colocarMaterial(new GasRecolectable(), coordenadasGas);
        zerg.abastecerDeRecursos(new Mineral(300), new Gas(0));
        zerg.construirEdificio(new FabricaCriadero(), new Coordenada(1,1));

        for (int i = 0; i < 5; i++)
            zerg.terminarTurno();

        zerg.construirEdificio(new FabricaExtractor(), coordenadasGas);

        //Construimos el extractor
        for (int i = 0; i < 6; i++)
            zerg.terminarTurno();

        Edificio extractor = zerg.conseguirEdificio(coordenadasGas);
        extractor.contratarUnidad(new Zangano());
        zerg.terminarTurno();

        assertTrue(zerg.tienesEstaCantidadDeGas(10));
    }

    @Test
    public void test03ExtractorConDosZanganoProduce20Gas() {
        Mapa mapa = Mapa.obtener();
        Zerg zerg = new Zerg();
        Coordenada coordenadasGas = new Coordenada(0,0);

        mapa.colocarMaterial(new GasRecolectable(), coordenadasGas);
        zerg.abastecerDeRecursos(new Mineral(300), new Gas(0));
        zerg.construirEdificio(new FabricaCriadero(), new Coordenada(1,1));

        for (int i = 0; i < 5; i++)
            zerg.terminarTurno();

        zerg.construirEdificio(new FabricaExtractor(), coordenadasGas);

        //Construimos el extractor
        for (int i = 0; i < 6; i++)
            zerg.terminarTurno();

        Edificio extractor = zerg.conseguirEdificio(coordenadasGas);
        extractor.contratarUnidad(new Zangano());
        extractor.contratarUnidad(new Zangano());
        zerg.terminarTurno();

        assertTrue(zerg.tienesEstaCantidadDeGas(20));
    }

    @Test
    public void test04ExtractorConTresZanganoProduce30Gas() {
        Mapa mapa = Mapa.obtener();
        Zerg zerg = new Zerg();
        Coordenada coordenadasGas = new Coordenada(0,0);

        mapa.colocarMaterial(new GasRecolectable(), coordenadasGas);
        zerg.abastecerDeRecursos(new Mineral(300), new Gas(0));
        zerg.construirEdificio(new FabricaCriadero(), new Coordenada(1,1));

        for (int i = 0; i < 5; i++)
            zerg.terminarTurno();

        zerg.construirEdificio(new FabricaExtractor(), coordenadasGas);

        //Construimos el extractor
        for (int i = 0; i < 6; i++)
            zerg.terminarTurno();

        Edificio extractor = zerg.conseguirEdificio(coordenadasGas);
        extractor.contratarUnidad(new Zangano());
        extractor.contratarUnidad(new Zangano());
        extractor.contratarUnidad(new Zangano());
        zerg.terminarTurno();

        assertTrue(zerg.tienesEstaCantidadDeGas(30));
    }

    @Test
    public void test05ExtractorNoPuedeRecibir4Zanganos() {
        Mapa mapa = Mapa.obtener();
        Zerg zerg = new Zerg();
        Coordenada coordenadasGas = new Coordenada(0,0);

        mapa.colocarMaterial(new GasRecolectable(), coordenadasGas);
        zerg.abastecerDeRecursos(new Mineral(300), new Gas(0));
        zerg.construirEdificio(new FabricaCriadero(), new Coordenada(1,1));

        for (int i = 0; i < 5; i++)
            zerg.terminarTurno();

        zerg.construirEdificio(new FabricaExtractor(), coordenadasGas);

        //Construimos el extractor
        for (int i = 0; i < 6; i++)
            zerg.terminarTurno();

        Edificio extractor = zerg.conseguirEdificio(coordenadasGas);
        extractor.contratarUnidad(new Zangano());
        extractor.contratarUnidad(new Zangano());
        extractor.contratarUnidad(new Zangano());

        assertThrows(ErrorExtratorNoPuedeTenerMasDe3ZanganosAlMismoTiempo.class, () -> extractor.contratarUnidad(new Zangano()));
    }
}
