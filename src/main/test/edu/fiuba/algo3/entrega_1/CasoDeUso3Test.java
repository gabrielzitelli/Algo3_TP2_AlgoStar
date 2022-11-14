package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.EdificioProtoss.Asimilador;
import edu.fiuba.algo3.modelo.EdificioProtoss.Pilon;
import edu.fiuba.algo3.modelo.EdificioZerg.Criadero;
import edu.fiuba.algo3.modelo.EdificioZerg.Extractor;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoSePuedeConstruirEnEstaCasilla;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.GasRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.MineralRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso3Test {

    @Test
    public void test01PuedoConstruirUnExtractorDondeHayVolcanDeGas(){
        Coordenada coordenada = new Coordenada(0,0);
        Recurso gasDelImperio = new Recurso(0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarMaterial(new GasRecolectable(),coordenada);

        assertDoesNotThrow(() -> elMapa.construirEdificio(new Extractor(gasDelImperio), coordenada));
    }

    @Test
    public void test02PuedoConstruirUnAsimiladorDondeHayVolcanDeGas(){
        Coordenada coordenada = new Coordenada(0,0);
        Recurso gasDelImperio = new Recurso(0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarMaterial(new GasRecolectable(),coordenada);

        assertDoesNotThrow(() -> elMapa.construirEdificio(new Asimilador(gasDelImperio), coordenada));
    }

    @Test
    public void test03NoPuedoConstruirUnCriaderoDondeHayVolcanDeGas(){
        Coordenada coordenada = new Coordenada(0,0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarMaterial(new GasRecolectable(),coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Criadero(), coordenada));
    }

    @Test
    public void test04NoPuedoConstruirPilonDondeHayUnVolcanDeGas(){
        Coordenada coordenada = new Coordenada(0,0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarMaterial(new GasRecolectable(),coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Pilon(), coordenada));
    }


}
