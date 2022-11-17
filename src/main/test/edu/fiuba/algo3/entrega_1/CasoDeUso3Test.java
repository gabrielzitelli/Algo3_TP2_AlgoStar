package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.*;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.*;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoSePuedeConstruirEnEstaCasilla;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.GasRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso3Test {

    @Test
    public void test01PuedoConstruirUnExtractorDondeHayVolcanDeGas(){
        // En esta prueba genero un criadero para poner moho sobre el volcan de gas y luego
        // construir el extractor
        Coordenada coordenada = new Coordenada(0,0);
        Recurso gasDelImperio = new Recurso(0);
        Coordenada coordenadaCriadero = new Coordenada(0,1);
        Criadero criadero = new Criadero();

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.construirEdificio(criadero, coordenadaCriadero);

        for (int i = 0; i < 5; i++)
            criadero.pasarTurno();

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

    @Test
    public void test05NoPuedoConstruirReservaDeProduccionDondeHayUnVolcanDeGas(){
        // En esta prueba genero un criadero para poner moho sobre el volcan de gas y luego
        // construir el reserva de produccion
        Coordenada coordenada = new Coordenada(0,0);
        Coordenada coordenadaCriadero = new Coordenada(0,1);
        Criadero criadero = new Criadero();

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.construirEdificio(criadero, coordenadaCriadero);

        for (int i = 0; i < 5; i++)
            criadero.pasarTurno();

        elMapa.colocarMaterial(new GasRecolectable(),coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenada));
    }

    @Test
    public void test06NoPuedoConstruirGuaridaDondeHayUnVolcanDeGas(){
        // En esta prueba genero un criadero para poner moho sobre el volcan de gas y luego
        // construir el guarida
        Coordenada coordenada = new Coordenada(0,0);
        Coordenada coordenadaCriadero = new Coordenada(0,1);
        Criadero criadero = new Criadero();

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.construirEdificio(criadero, coordenadaCriadero);

        for (int i = 0; i < 5; i++)
            criadero.pasarTurno();

        elMapa.colocarMaterial(new GasRecolectable(),coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Guarida(), coordenada));
    }

    @Test
    public void test07NoPuedoConstruirEspiralDondeHayUnVolcanDeGas(){
        // En esta prueba genero un criadero para poner moho sobre el volcan de gas y luego
        // construir la espiral
        Coordenada coordenada = new Coordenada(0,0);
        Coordenada coordenadaCriadero = new Coordenada(0,1);
        Criadero criadero = new Criadero();

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.construirEdificio(criadero, coordenadaCriadero);

        for (int i = 0; i < 5; i++)
            criadero.pasarTurno();

        elMapa.colocarMaterial(new GasRecolectable(),coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Espiral(), coordenada));
    }

    @Test
    public void test08NoPuedoConstruirUnNexoMineralDondeHayVolcanDeGas(){
        Coordenada coordenada = new Coordenada(0,0);
        Recurso mineralDelImperio = new Recurso(0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarMaterial(new GasRecolectable(),coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new NexoMineral(mineralDelImperio), coordenada));
    }

    @Test
    public void test09NoPuedoConstruirAccesoDondeHayUnVolcanDeGas(){
        // En esta prueba genero un criadero para poner moho sobre el volcan de gas y luego
        // construir el acceso
        Coordenada coordenada = new Coordenada(0,0);
        Coordenada coordenadaPilon = new Coordenada(0,1);
        Pilon pilon = new Pilon();

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.construirEdificio(pilon, coordenadaPilon);

        for (int i = 0; i < 5; i++)
            pilon.pasarTurno();

        elMapa.colocarMaterial(new GasRecolectable(),coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new Acceso(), coordenada));
    }

    @Test
    public void test10NoPuedoConstruirPuertoEstelarDondeHayUnVolcanDeGas(){
        // En esta prueba genero un criadero para poner moho sobre el volcan de gas y luego
        // construir la puerta estelar
        Coordenada coordenada = new Coordenada(0,0);
        Coordenada coordenadaPilon = new Coordenada(0,1);
        Pilon pilon = new Pilon();

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.construirEdificio(pilon, coordenadaPilon);

        for (int i = 0; i < 5; i++)
            pilon.pasarTurno();

        elMapa.colocarMaterial(new GasRecolectable(),coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.construirEdificio(new PuertoEstelar(), coordenada));
    }
}
