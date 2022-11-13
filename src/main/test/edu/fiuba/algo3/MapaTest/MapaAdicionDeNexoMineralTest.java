package edu.fiuba.algo3.MapaTest;

import edu.fiuba.algo3.modelo.EdificioProtoss.NexoMineral;
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

public class MapaAdicionDeNexoMineralTest {

    @Test
    public void testXXNoPuedoConstruirUnExtractorDondeHayNodoMineral(){
        Coordenada coordenada = new Coordenada(0,0);
        Recurso gasDelImperio = new Recurso(0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarMaterial(new MineralRecolectable(),coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Extractor(gasDelImperio), coordenada));
    }

    @Test
    public void testXXNoPuedoConstruirUnNexoMineralDondeHayUnVolcanDeGas(){
        Coordenada coordenada = new Coordenada(0,0);
        Recurso mineralesDelImperio = new Recurso(0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarMaterial(new GasRecolectable(),coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new NexoMineral(mineralesDelImperio), coordenada));
    }

    @Test
    public void testXXNoPuedoConstruirUnNexoMineralDondeHayUnaCasillaVacia(){
        Coordenada coordenada = new Coordenada(0,0);
        Recurso mineralesDelImperio = new Recurso(0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new NexoMineral(mineralesDelImperio), coordenada));
    }

    @Test
    public void testXXPuedoConstruirUnNexoMineralDondeHayUnNodoMineral(){
        Coordenada coordenada = new Coordenada(0,0);
        Recurso mineralesDelImperio = new Recurso(0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarMaterial(new MineralRecolectable(),coordenada);

        assertDoesNotThrow(() -> elMapa.construirEdificio(new NexoMineral(mineralesDelImperio), coordenada));
    }
}
