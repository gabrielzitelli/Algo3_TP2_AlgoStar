package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.EdificioZerg.Criadero;
import edu.fiuba.algo3.modelo.Excepciones.ErrorVidaLlegoACero;
import edu.fiuba.algo3.modelo.danioYAtaque.Ataque;
import edu.fiuba.algo3.modelo.danioYAtaque.DanioBasico;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso10 {

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
    public void test01PuedoDaniarUnCriaderoSeRegeneraYSeDestruye(){
        Criadero unCriadero = new Criadero();
        Ataque unAtaque = new Ataque( new DanioBasico(499) );
        Ataque ataqueLetal = new Ataque( new DanioBasico(500) );

        // Criadero 500V
        unCriadero.aplicarAtaque(unAtaque);

        for (int i = 0; i < 7; i++)
            unCriadero.pasarTurno();

        assertThrows(ErrorVidaLlegoACero.class, () -> unCriadero.aplicarAtaque(ataqueLetal) );
    }
}
