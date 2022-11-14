package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.EdificioProtoss.Acceso;
import edu.fiuba.algo3.modelo.danioYAtaque.Ataque;
import edu.fiuba.algo3.modelo.danioYAtaque.DanioBasico;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CasoDeUso12 {
    @Test
    public void test01PuedoDaniarElEscudoDeUnAccesoPentrandoLaVidaSeRegeneraYPuedoDaniarloDeVuelta(){
        Acceso unAcceso = new Acceso();
        Ataque unAtaque = new Ataque( new DanioBasico(500) );
        Ataque ataqueCasiLetal = new Ataque( new DanioBasico(999) );

        // Acceso tiene 500E / 500V
        unAcceso.aplicarAtaque(ataqueCasiLetal); //Queda con 1 de vida y 0 de escudo

        for (int i = 0; i < 7; i++)
            unAcceso.pasarTurno();

        assertDoesNotThrow( () -> unAcceso.aplicarAtaque(unAtaque) );
    }
}
