package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.*;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Ataque.*;

import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Dragon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CasoDeUso10Test {

    @Test
    public void test01PuedoDaniarUnCriaderoYLaVidaSeRegeneraTotalmente(){
        Criadero unCriadero = new Criadero();
        Ataque unAtaque = new Ataque(new DanioZealot(499));

        // Criadero 500V
        unCriadero.recibirAtaque(unAtaque);

        for (int i = 0; i < 7; i++)
            unCriadero.pasarTurno();

        assertDoesNotThrow( () -> unCriadero.recibirAtaque(unAtaque) );
    }

    @Test
    public void test02PuedoDaniarUnaReservaDeProduccionYLaVidaSeRegeneraTotalmente(){
        ReservaDeReproduccion unaReservaDeReproduccion = new ReservaDeReproduccion();
        Ataque unAtaque = new Ataque(new DanioZealot(999));

        // Reserva de produccion 1000V
        unaReservaDeReproduccion.recibirAtaque(unAtaque);

        for (int i = 0; i < 7; i++)
            unaReservaDeReproduccion.pasarTurno();

        assertDoesNotThrow( () -> unaReservaDeReproduccion.recibirAtaque(unAtaque) );
    }

    @Test
    public void test03PuedoDaniarUnExtractorYLaVidaSeRegeneraTotalmente(){
        Recurso gasDelImperio = new Gas(0);
        Extractor unExtractor = new Extractor(gasDelImperio);
        Ataque unAtaque = new Ataque(new DanioZealot(749));

        // Extractor 750V
        unExtractor.recibirAtaque(unAtaque);

        for (int i = 0; i < 7; i++)
            unExtractor.pasarTurno();

        assertDoesNotThrow( () -> unExtractor.recibirAtaque(unAtaque) );
    }

    @Test
    public void test04PuedoDaniarUnGuaridaYLaVidaSeRegeneraTotalmente(){
        Guarida unaGuarida = new Guarida();
        Ataque unAtaque = new Ataque(new DanioZealot(1249));

        // Guardia 1250V
        unaGuarida.recibirAtaque(unAtaque);

        for (int i = 0; i < 7; i++)
            unaGuarida.pasarTurno();

        assertDoesNotThrow( () -> unaGuarida.recibirAtaque(unAtaque) );
    }

    @Test
    public void test05PuedoDaniarUnaEspiralYLaVidaSeRegeneraTotalmente(){
        Espiral unaEspiral = new Espiral();
        Ataque unAtaque = new Ataque(new DanioZealot(1299));

        // Espiral 1300V
        unaEspiral.recibirAtaque(unAtaque);

        for (int i = 0; i < 7; i++)
            unaEspiral.pasarTurno();

        assertDoesNotThrow( () -> unaEspiral.recibirAtaque(unAtaque) );
    }
}
