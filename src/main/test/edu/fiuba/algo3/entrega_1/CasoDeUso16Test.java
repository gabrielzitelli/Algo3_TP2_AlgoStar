package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.*;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.*;
import edu.fiuba.algo3.modelo.Excepciones.*;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Mapa.*;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso16Test {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01NoPuedoConstruirSobreUnVolcanConUnaEdificacionPropiaYaExistente(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadasAsimilador = new Coordenada(2,0);
        Asimilador asimilador = new Asimilador(new Gas(0));
        Asimilador otroAsimilador = new Asimilador(new Gas(0));

        elMapa.colocarMaterial(new GasRecolectable(), coordenadasAsimilador);
        elMapa.construirEdificio(asimilador, coordenadasAsimilador);

        assertThrows(ErrorNoSePuedeConstruirEdificioSobreOtroEdificio.class,
                () -> elMapa.construirEdificio(otroAsimilador, coordenadasAsimilador));
    }

    @Test
    public void test02NoPuedoConstruirSobreUnVolcanConUnaEdificacionEnemigaYaExistente(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadasAsimilador = new Coordenada(2,0);
        Asimilador asimilador = new Asimilador(new Gas(0));
        Extractor unExtractor = new Extractor(new Gas(0));

        elMapa.colocarMaterial(new GasRecolectable(), coordenadasAsimilador);
        elMapa.construirEdificio(asimilador, coordenadasAsimilador);

        assertThrows(ErrorNoSePuedeConstruirEdificioSobreOtroEdificio.class,
                () -> elMapa.construirEdificio(unExtractor, coordenadasAsimilador));
    }

    @Test
    public void test03NoSePuedeConstruirUnNexoMineralSiHayUnZanganoTrabajandoEnElMineral() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);
        Recurso mineralDelImperio = new Mineral(0);
        UnidadZerg unZangano = new Zangano();

        elMapa.colocarMaterial(new MineralRecolectable(),coordenada);
        unZangano.setDepositoRecurso(mineralDelImperio);
        elMapa.colocarUnidadZerg(unZangano, coordenada);

        assertThrows(ErrorNoSePuedeConstruirEdificioSobreOtroEdificio.class, () ->
                elMapa.construirEdificio(new NexoMineral(mineralDelImperio), coordenada));
    }

    @Test
    public void test04NoSeMandarATrabajarUnZanganoSiHayUnNexoMineralEnElMineral() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);
        Recurso mineralDelImperio = new Mineral(0);
        UnidadZerg unZangano = new Zangano();

        elMapa.colocarMaterial(new MineralRecolectable(),coordenada);
        unZangano.setDepositoRecurso(mineralDelImperio);
        elMapa.construirEdificio(new NexoMineral(mineralDelImperio), coordenada);

        assertThrows(ErrorNoSePuedeColocarUnidadEnUnaCasillaOcupada.class, () ->
                elMapa.colocarUnidadZerg(unZangano, coordenada));
    }
}
