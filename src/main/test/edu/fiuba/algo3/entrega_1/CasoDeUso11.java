package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.EdificioProtoss.Acceso;
import edu.fiuba.algo3.modelo.Excepciones.ErrorVidaLlegoACero;
import edu.fiuba.algo3.modelo.danioYAtaque.Ataque;
import edu.fiuba.algo3.modelo.danioYAtaque.DanioBasico;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso11 {

    @Test
    public void test01PuedoDaniarElEscudoDeUnAccesoSeRegeneraYPuedoDaniarloDeVuelta(){
        Acceso unAcceso = new Acceso();
        Ataque unAtaque = new Ataque( new DanioBasico(500) );
        Ataque ataqueCasiLetal = new Ataque( new DanioBasico(999) );

        // Acceso tiene 500E / 500V

        unAcceso.aplicarAtaque(unAtaque);

        for (int i = 0; i < 7; i++)
            unAcceso.pasarTurno();

        assertDoesNotThrow( () -> unAcceso.aplicarAtaque(ataqueCasiLetal) );
    }
}
