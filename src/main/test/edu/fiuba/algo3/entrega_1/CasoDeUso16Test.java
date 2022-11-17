package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Asimilador;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.NexoMineral;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Extractor;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeConstruirEdificioSobreOtroEdificio;
import edu.fiuba.algo3.modelo.Excepciones.ErrorPosicionOcupada;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.GasRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.MineralRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.UnidadZerg;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso16Test {
    @Test
    public void test01NoPuedoConstruirSobreUnVolcanConUnaEdificacionPropiaYaExistente(){
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();
        Coordenada coordenadasAsimilador = new Coordenada(2,0);
        elMapa.colocarMaterial(new GasRecolectable(), coordenadasAsimilador);

        Asimilador asimilador = new Asimilador(new Recurso(0));
        elMapa.construirEdificio(asimilador, coordenadasAsimilador);

        Asimilador otroAsimilador = new Asimilador(new Recurso(0));
        assertThrows(ErrorNoSePuedeConstruirEdificioSobreOtroEdificio.class, () -> elMapa.construirEdificio(otroAsimilador, coordenadasAsimilador));
    }
    @Test
    public void test02NoPuedoConstruirSobreUnVolcanConUnaEdificacionEnemigaYaExistente(){
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();
        Coordenada coordenadasAsimilador = new Coordenada(2,0);
        elMapa.colocarMaterial(new GasRecolectable(), coordenadasAsimilador);

        Asimilador asimilador = new Asimilador(new Recurso(0));
        elMapa.construirEdificio(asimilador, coordenadasAsimilador);

        Extractor unExtractor = new Extractor(new Recurso(0));
        assertThrows(ErrorNoSePuedeConstruirEdificioSobreOtroEdificio.class, () -> elMapa.construirEdificio(unExtractor, coordenadasAsimilador));
    }
    @Test
    public void test03NoSePuedeConstruirUnNexoMineralSiHayUnZanganoTrabajandoEnElMineral() {
        Coordenada coordenada = new Coordenada(0,0);
        Recurso mineralDelImperio = new Recurso(0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();
        elMapa.colocarMaterial(new MineralRecolectable(),coordenada);

        UnidadZerg saraza = new Zangano();
        saraza.setDepositoRecurso(mineralDelImperio);
        elMapa.colocarUnidadZerg(saraza, coordenada);
        assertThrows(ErrorNoSePuedeConstruirEdificioSobreOtroEdificio.class, () ->
                elMapa.construirEdificio(new NexoMineral(mineralDelImperio), coordenada));
    }
    @Test
    public void test04NoSeMandarATrabajarUnZanganoSiHayUnNexoMineralEnElMineral() {
        Coordenada coordenada = new Coordenada(0,0);
        Recurso mineralDelImperio = new Recurso(0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();
        elMapa.colocarMaterial(new MineralRecolectable(),coordenada);

        UnidadZerg saraza = new Zangano();
        saraza.setDepositoRecurso(mineralDelImperio);

        elMapa.construirEdificio(new NexoMineral(mineralDelImperio), coordenada);

        assertThrows(ErrorPosicionOcupada.class, () ->
                elMapa.colocarUnidadZerg(saraza, coordenada));

    }
}
