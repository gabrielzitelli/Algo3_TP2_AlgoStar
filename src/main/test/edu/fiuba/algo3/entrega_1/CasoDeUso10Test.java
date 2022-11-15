package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.EdificioZerg.*;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.danioYAtaque.Ataque;
import edu.fiuba.algo3.modelo.danioYAtaque.DanioBasico;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CasoDeUso10Test {

    @Test
    public void test01PuedoDaniarUnCriaderoYLaVidaSeRegeneraTotalmente(){
        Criadero unCriadero = new Criadero();
        Ataque unAtaque = new Ataque( new DanioBasico(499) );

        // Criadero 500V
        unCriadero.aplicarAtaque(unAtaque);

        for (int i = 0; i < 7; i++)
            unCriadero.pasarTurno();

        assertDoesNotThrow( () -> unCriadero.aplicarAtaque(unAtaque) );
    }

    @Test
    public void test02PuedoDaniarUnaReservaDeProduccionYLaVidaSeRegeneraTotalmente(){
        ReservaDeReproduccion unaReservaDeReproduccion = new ReservaDeReproduccion();
        Ataque unAtaque = new Ataque( new DanioBasico(999) );

        // Reserva de produccion 1000V
        unaReservaDeReproduccion.aplicarAtaque(unAtaque);

        for (int i = 0; i < 7; i++)
            unaReservaDeReproduccion.pasarTurno();

        assertDoesNotThrow( () -> unaReservaDeReproduccion.aplicarAtaque(unAtaque) );
    }

    @Test
    public void test03PuedoDaniarUnExtractorYLaVidaSeRegeneraTotalmente(){
        Recurso gasDelImperio = new Recurso(0);
        Extractor unExtractor = new Extractor(gasDelImperio);
        Ataque unAtaque = new Ataque( new DanioBasico(749) );

        // Extractor 750V
        unExtractor.aplicarAtaque(unAtaque);

        for (int i = 0; i < 7; i++)
            unExtractor.pasarTurno();

        assertDoesNotThrow( () -> unExtractor.aplicarAtaque(unAtaque) );
    }

    @Test
    public void test04PuedoDaniarUnGuaridaYLaVidaSeRegeneraTotalmente(){
        Guarida unaGuarida = new Guarida();
        Ataque unAtaque = new Ataque( new DanioBasico(1249) );

        // Guardia 1250V
        unaGuarida.aplicarAtaque(unAtaque);

        for (int i = 0; i < 7; i++)
            unaGuarida.pasarTurno();

        assertDoesNotThrow( () -> unaGuarida.aplicarAtaque(unAtaque) );
    }

    @Test
    public void test05PuedoDaniarUnaEspiralYLaVidaSeRegeneraTotalmente(){
        Espiral unaEspiral = new Espiral();
        Ataque unAtaque = new Ataque( new DanioBasico(1299) );

        // Espiral 1300V
        unaEspiral.aplicarAtaque(unAtaque);

        for (int i = 0; i < 7; i++)
            unaEspiral.pasarTurno();

        assertDoesNotThrow( () -> unaEspiral.aplicarAtaque(unAtaque) );
    }
}
