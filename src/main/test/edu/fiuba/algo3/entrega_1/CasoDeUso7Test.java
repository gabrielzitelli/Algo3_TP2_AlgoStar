package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Asimilador;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.NexoMineral;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Criadero;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Extractor;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.GasRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.MineralRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.UnidadZerg;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CasoDeUso7Test {

    @Test
    public void Test01MandoUnZanganoAExtraerUnMineralYRevisoQueDepositeEnElImperio(){
        Coordenada coordenada = new Coordenada(0,0);
        Recurso mineralDelImperio = new Recurso(0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();
        elMapa.colocarMaterial(new MineralRecolectable(),coordenada);

        UnidadZerg saraza = new Zangano();
        saraza.setDepositoRecurso(mineralDelImperio);
        elMapa.colocarUnidadZerg(saraza, coordenada);

        saraza.extraer();

        assertTrue(mineralDelImperio.tenesCantidadDeRecurso(10));
    }

    @Test
    public void Test02CreoUnAsimiladorYRevisoQueDepositeEnElImperio() {
        Coordenada coordenada = new Coordenada(0, 0);
        Recurso mineralDelImperio = new Recurso(0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();
        elMapa.colocarMaterial(new MineralRecolectable(), coordenada);
        Edificio nexoMineral = new NexoMineral(mineralDelImperio);
        elMapa.construirEdificio(nexoMineral, coordenada);
        for (int i = 0; i < 3; i++)
            nexoMineral.pasarTurno();
        nexoMineral.pasarTurno();
            assertTrue(mineralDelImperio.tenesCantidadDeRecurso(10));
    }

    @Test
    public void Test03CreoUnNexoMineralYRevisoQueDepositeEnElImperio() {
        Coordenada coordenada = new Coordenada(0, 0);
        Recurso gasDelImperio = new Recurso(0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();
        elMapa.colocarMaterial(new GasRecolectable(), coordenada);
        Edificio Asimilador = new Asimilador(gasDelImperio);

        elMapa.construirEdificio(Asimilador, coordenada);

        for (int i = 0; i < 5; i++)
            Asimilador.pasarTurno();
        Asimilador.pasarTurno();

        assertTrue(gasDelImperio.tenesCantidadDeRecurso(20));
    }

    @Test
    public void Test04CreoUnExtractorYRevisoQueDepositeEnElImperio() {
        Coordenada coordenadaCriadero = new Coordenada(0, 0);
        Coordenada coordenadaExtractor = new Coordenada(0,5);
        Recurso gasDelImperio = new Recurso(0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();
        Edificio criadero = new Criadero();
        elMapa.construirEdificio(criadero,coordenadaCriadero);
        for (int i = 0; i < 4; i++){
            criadero.pasarTurno();
        }
        elMapa.colocarMaterial(new GasRecolectable(), coordenadaExtractor);
        Edificio extractor = new Extractor(gasDelImperio);

        elMapa.construirEdificio(extractor, coordenadaExtractor);
        for (int i = 0; i < 6; i++)
            extractor.pasarTurno();
        extractor.contratarUnidad(new Zangano());
        extractor.contratarUnidad(new Zangano());
        extractor.contratarUnidad(new Zangano());


        extractor.pasarTurno();

        assertTrue(gasDelImperio.tenesCantidadDeRecurso(30));
    }


}