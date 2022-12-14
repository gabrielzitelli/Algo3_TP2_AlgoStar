package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Asimilador;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.NexoMineral;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Extractor;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeColocarOcupableEnUnaCasillaOcupada;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Mapa.Casilla.GasRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.MineralRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.UnidadZerg;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;
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
        elMapa.colocarOcupable(asimilador, coordenadasAsimilador);

        assertThrows(ErrorNoSePuedeColocarOcupableEnUnaCasillaOcupada.class,
                () -> elMapa.colocarOcupable(otroAsimilador, coordenadasAsimilador));
    }

    @Test
    public void test02NoPuedoConstruirSobreUnVolcanConUnaEdificacionEnemigaYaExistente(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadasAsimilador = new Coordenada(2,0);
        Asimilador asimilador = new Asimilador(new Gas(0));
        Extractor unExtractor = new Extractor(new Gas(0));

        elMapa.colocarMaterial(new GasRecolectable(), coordenadasAsimilador);
        elMapa.colocarOcupable(asimilador, coordenadasAsimilador);

        assertThrows(ErrorNoSePuedeColocarOcupableEnUnaCasillaOcupada.class,
                () -> elMapa.colocarOcupable(unExtractor, coordenadasAsimilador));
    }

    @Test
    public void test03NoSePuedeConstruirUnNexoMineralSiHayUnZanganoTrabajandoEnElMineral() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);
        Mineral mineralDelImperio = new Mineral(0);
        UnidadZerg unZangano = new Zangano();

        elMapa.colocarMaterial(new MineralRecolectable(),coordenada);
        unZangano.setDepositoRecurso(mineralDelImperio);
        elMapa.colocarOcupable(unZangano, coordenada);

        assertThrows(ErrorNoSePuedeColocarOcupableEnUnaCasillaOcupada.class, () ->
                elMapa.colocarOcupable(new NexoMineral(mineralDelImperio), coordenada));
    }

    @Test
    public void test04NoSeMandarATrabajarUnZanganoSiHayUnNexoMineralEnElMineral() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);
        Mineral mineralDelImperio = new Mineral(0);
        UnidadZerg unZangano = new Zangano();

        elMapa.colocarMaterial(new MineralRecolectable(),coordenada);
        unZangano.setDepositoRecurso(mineralDelImperio);
        elMapa.colocarOcupable(new NexoMineral(mineralDelImperio), coordenada);

        assertThrows(ErrorNoSePuedeColocarOcupableEnUnaCasillaOcupada.class, () ->
                elMapa.colocarOcupable(unZangano, coordenada));
    }
}
