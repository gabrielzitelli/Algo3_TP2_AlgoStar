package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.EdificioZerg.Extractor;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Casilla.GasRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.UnidadesZerg.Zangano;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExtractorTest {

    //El Zangano se mete bajo tierra y aparece en el gas

    @Test
    public void test01PuedoCrearUnExtractor(){

        Recurso gasDelImperio = new Recurso(0);

        Extractor unExtractor = new Extractor(gasDelImperio);

        assertNotNull(unExtractor);
    }

    @Test
    public void test02NoPuedoExtraerElGasDeUnExtractorQueNoEstaConstruidoEn5Turnos(){
        Recurso gasDelImperio = new Recurso(0);

        Extractor unExtractor = new Extractor(gasDelImperio);

        for(int i = 0; i < 5; i++)
            unExtractor.pasarTurno();

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unExtractor.contratarZangano(new Zangano()));
    }

    @Test
    public void test03NoPuedoContratarUnZanganoEnUnExtractorQueNoEstaConstruidoEn5Turnos(){
        Recurso gasDelImperio = new Recurso(0);

        Extractor unExtractor = new Extractor(gasDelImperio);

        for(int i = 0; i < 5; i++)
            unExtractor.pasarTurno();

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unExtractor.contratarZangano(new Zangano()));
    }

    @Test
    public void test04PuedoContratarUnZanganoEnUnExtractorQueEstaConstruidoEn6Turnos(){
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
        assertDoesNotThrow( () -> extractor.contratarUnidad(new Zangano()));
    }
    @Test
    public void test05PuedoContratarUnZanganoEnUnExtractorYMextraeCadaTurno(){
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
    public void test06PuedoContratarTresZanganosEnUnExtractorYMextraeCadaTurno() {
        Zerg zerg = new Zerg();
        zerg.abastecerDeRecursos(new Recurso(150), new Recurso(0));
        Mapa mapa = Mapa.obtener();
        mapa.reiniciarMapa();
        Coordenada coordenadasGas = new Coordenada(0, 0);
        mapa.colocarMaterial(new GasRecolectable(), coordenadasGas);

        zerg.construirCriadero(new Coordenada(1, 1));
        for (int i = 0; i < 5; i++) {
            zerg.terminarTurno();
        }

        zerg.construirExtractor(coordenadasGas);

        //Construimos el extractor
        for (int i = 0; i < 6; i++) {
            zerg.terminarTurno();
        }
        Edificio extractor = zerg.conseguirEdificio(coordenadasGas);
        extractor.contratarUnidad(new Zangano());
        extractor.contratarUnidad(new Zangano());
        extractor.contratarUnidad(new Zangano());

        zerg.terminarTurno();

        assert (zerg.tienesEstaCantidadDeGas(30));
    }
}
