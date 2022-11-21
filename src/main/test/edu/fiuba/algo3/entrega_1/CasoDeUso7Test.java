package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Edificios.*;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.*;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.*;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Mapa.*;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CasoDeUso7Test {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01MandoUnZanganoAExtraerUnMineralYRevisoQueDepositeEnElImperio(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);
        Recurso mineralDelImperio = new Mineral(0);
        UnidadZerg unZangano = new Zangano();

        elMapa.colocarMaterial(new MineralRecolectable(),coordenada);
        unZangano.setDepositoRecurso(mineralDelImperio);
        elMapa.colocarUnidadZerg(unZangano, coordenada);
        unZangano.extraer();

        assertTrue(mineralDelImperio.tenesCantidadDeRecurso(10));
    }

    @Test
    public void test02CreoUnAsimiladorYRevisoQueDepositeEnElImperio() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0, 0);
        Recurso mineralDelImperio = new Mineral(0);
        Edificio nexoMineral = new NexoMineral(mineralDelImperio);

        elMapa.colocarMaterial(new MineralRecolectable(), coordenada);
        elMapa.construirEdificio(nexoMineral, coordenada);

        for (int i = 0; i < 3; i++)
            nexoMineral.pasarTurno();

        nexoMineral.pasarTurno();

        assertTrue(mineralDelImperio.tenesCantidadDeRecurso(10));
    }

    @Test
    public void test03CreoUnNexoMineralYRevisoQueDepositeEnElImperio() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0, 0);
        Recurso gasDelImperio = new Mineral(0);
        Edificio Asimilador = new Asimilador(gasDelImperio);

        elMapa.colocarMaterial(new GasRecolectable(), coordenada);
        elMapa.construirEdificio(Asimilador, coordenada);

        for (int i = 0; i < 5; i++)
            Asimilador.pasarTurno();

        Asimilador.pasarTurno();

        assertTrue(gasDelImperio.tenesCantidadDeRecurso(20));
    }

    @Test
    public void test04CreoUnExtractorYRevisoQueDepositeEnElImperio() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0, 0);
        Coordenada coordenadaExtractor = new Coordenada(0,5);
        Recurso gasDelImperio = new Gas(0);
        Edificio criadero = new Criadero();
        Edificio extractor = new Extractor(gasDelImperio);

        elMapa.construirEdificio(criadero,coordenadaCriadero);

        for (int i = 0; i < 4; i++)
            criadero.pasarTurno();

        elMapa.colocarMaterial(new GasRecolectable(), coordenadaExtractor);
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