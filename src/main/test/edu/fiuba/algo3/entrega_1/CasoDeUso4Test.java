package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Excepciones.ErrorExtratorNoPuedeTenerMasDe3ZanganosAlMismoTiempo;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Casilla.GasRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.UnidadesZerg.Zangano;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso4Test {
    @Test
    public void test01ExtractorSinZanganosNoProduceGas() {
        Zerg zerg = new Zerg();
        zerg.abastecerDeRecursos(new Recurso(150), new Recurso(0));
        Mapa mapa = Mapa.obtener();
        mapa.reiniciarMapa();
        Coordenada coordenadasGas = new Coordenada(0,0);
        mapa.colocarMaterial(new GasRecolectable(), coordenadasGas);

        zerg.construirCriadero(new Coordenada(1,1));
        for (int i = 0; i < 5; i++){
            zerg.terminarTurno();
        }

        zerg.construirExtractor(coordenadasGas);

        //Construimos el extractor
        for (int i = 0; i < 6; i++){
            zerg.terminarTurno();
        }
        Edificio extractor = zerg.conseguirEdificio(coordenadasGas);
        //extractor.contratarUnidad(zangano);
        zerg.terminarTurno();

        assert(zerg.tienesEstaCantidadDeGas(0));
    }
    @Test
    public void test02ExtractorConUnZanganoProduce10Gas() {
        Zerg zerg = new Zerg();
        zerg.abastecerDeRecursos(new Recurso(150), new Recurso(0));
        Mapa mapa = Mapa.obtener();
        mapa.reiniciarMapa();
        Coordenada coordenadasGas = new Coordenada(0,0);
        mapa.colocarMaterial(new GasRecolectable(), coordenadasGas);

        zerg.construirCriadero(new Coordenada(1,1));
        for (int i = 0; i < 5; i++){
            zerg.terminarTurno();
        }

        zerg.construirExtractor(coordenadasGas);

        //Construimos el extractor
        for (int i = 0; i < 6; i++){
            zerg.terminarTurno();
        }
        Edificio extractor = zerg.conseguirEdificio(coordenadasGas);
        extractor.contratarUnidad(new Zangano());
        zerg.terminarTurno();

        assert(zerg.tienesEstaCantidadDeGas(10));
    }
    @Test
    public void test03ExtractorConDosZanganoProduce20Gas() {
        Zerg zerg = new Zerg();
        zerg.abastecerDeRecursos(new Recurso(150), new Recurso(0));
        Mapa mapa = Mapa.obtener();
        mapa.reiniciarMapa();
        Coordenada coordenadasGas = new Coordenada(0,0);
        mapa.colocarMaterial(new GasRecolectable(), coordenadasGas);

        zerg.construirCriadero(new Coordenada(1,1));
        for (int i = 0; i < 5; i++){
            zerg.terminarTurno();
        }

        zerg.construirExtractor(coordenadasGas);

        //Construimos el extractor
        for (int i = 0; i < 6; i++){
            zerg.terminarTurno();
        }
        Edificio extractor = zerg.conseguirEdificio(coordenadasGas);
        extractor.contratarUnidad(new Zangano());
        extractor.contratarUnidad(new Zangano());
        zerg.terminarTurno();

        assert(zerg.tienesEstaCantidadDeGas(20));
    }
    @Test
    public void test04ExtractorConTresZanganoProduce30Gas() {
        Zerg zerg = new Zerg();
        zerg.abastecerDeRecursos(new Recurso(150), new Recurso(0));
        Mapa mapa = Mapa.obtener();
        mapa.reiniciarMapa();
        Coordenada coordenadasGas = new Coordenada(0,0);
        mapa.colocarMaterial(new GasRecolectable(), coordenadasGas);

        zerg.construirCriadero(new Coordenada(1,1));
        for (int i = 0; i < 5; i++){
            zerg.terminarTurno();
        }

        zerg.construirExtractor(coordenadasGas);

        //Construimos el extractor
        for (int i = 0; i < 6; i++){
            zerg.terminarTurno();
        }
        Edificio extractor = zerg.conseguirEdificio(coordenadasGas);
        extractor.contratarUnidad(new Zangano());
        extractor.contratarUnidad(new Zangano());
        extractor.contratarUnidad(new Zangano());
        zerg.terminarTurno();

        assert(zerg.tienesEstaCantidadDeGas(30));
    }
    @Test
    public void test05ExtractorNoPuedeRecibir4Zanganos() {
        Zerg zerg = new Zerg();
        zerg.abastecerDeRecursos(new Recurso(150), new Recurso(0));
        Mapa mapa = Mapa.obtener();
        mapa.reiniciarMapa();
        Coordenada coordenadasGas = new Coordenada(0,0);
        mapa.colocarMaterial(new GasRecolectable(), coordenadasGas);

        zerg.construirCriadero(new Coordenada(1,1));
        for (int i = 0; i < 5; i++){
            zerg.terminarTurno();
        }

        zerg.construirExtractor(coordenadasGas);

        //Construimos el extractor
        for (int i = 0; i < 6; i++){
            zerg.terminarTurno();
        }
        Edificio extractor = zerg.conseguirEdificio(coordenadasGas);
        extractor.contratarUnidad(new Zangano());
        extractor.contratarUnidad(new Zangano());
        extractor.contratarUnidad(new Zangano());
        assertThrows(ErrorExtratorNoPuedeTenerMasDe3ZanganosAlMismoTiempo.class, () -> extractor.contratarUnidad(new Zangano()));
    }

}
