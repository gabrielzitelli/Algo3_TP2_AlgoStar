package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.EdificioProtoss.Asimilador;
import edu.fiuba.algo3.modelo.EdificioZerg.Extractor;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeConstruirEdificioSobreOtroEdificio;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.GasRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
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
    //TODO hacer los casos con el zangano y el nexoMineral
}
