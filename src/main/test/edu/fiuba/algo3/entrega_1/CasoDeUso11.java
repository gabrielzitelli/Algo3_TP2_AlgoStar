package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.EdificioProtoss.*;
import edu.fiuba.algo3.modelo.Excepciones.ErrorVidaLlegoACero;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
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

    @Test
    public void test02PuedoDaniarElEscudoDeUnNexoMineralSeRegeneraYPuedoDaniarloDeVuelta(){
        Recurso mineralesDelImperio = new Recurso(0);
        NexoMineral unNexoMineral = new NexoMineral(mineralesDelImperio);
        Ataque unAtaque = new Ataque( new DanioBasico(250) );
        Ataque ataqueCasiLetal = new Ataque( new DanioBasico(499) );

        // Nexo Mineral tiene 250E / 250V
        unNexoMineral.aplicarAtaque(unAtaque);

        for (int i = 0; i < 7; i++)
            unNexoMineral.pasarTurno();

        assertDoesNotThrow( () -> unNexoMineral.aplicarAtaque(ataqueCasiLetal) );
    }

    @Test
    public void test03PuedoDaniarElEscudoDeUnPilonYPuedoDaniarloDeVuelta(){
        Pilon unPilon = new Pilon();
        Ataque unAtaque = new Ataque( new DanioBasico(300) );
        Ataque ataqueCasiLetal = new Ataque( new DanioBasico(599) );

        // Pilon tiene 300E / 300V
        unPilon.aplicarAtaque(unAtaque);

        for (int i = 0; i < 7; i++)
            unPilon.pasarTurno();

        assertDoesNotThrow( () -> unPilon.aplicarAtaque(ataqueCasiLetal) );
    }

    @Test
    public void test04PuedoDaniarElEscudoDeUnAsimiladorYPuedoDaniarloDeVuelta(){
        Recurso gasDelImperio = new Recurso(0);
        Asimilador unAsimilador = new Asimilador(gasDelImperio);
        Ataque unAtaque = new Ataque( new DanioBasico(300) );
        Ataque ataqueCasiLetal = new Ataque( new DanioBasico(599) );

        // Asimilador tiene 450E / 450V
        unAsimilador.aplicarAtaque(unAtaque);

        for (int i = 0; i < 7; i++)
            unAsimilador.pasarTurno();

        assertDoesNotThrow( () -> unAsimilador.aplicarAtaque(ataqueCasiLetal) );
    }

    @Test
    public void test05PuedoDaniarElEscudoDeUnPuertoEstelarYPuedoDaniarloDeVuelta() {
        PuertoEstelar unPuertoEstelar = new PuertoEstelar();
        Ataque unAtaque = new Ataque(new DanioBasico(600));
        Ataque ataqueCasiLetal = new Ataque(new DanioBasico(1199));

        // Puerto estelar tiene 600E / 600V
        unPuertoEstelar.aplicarAtaque(unAtaque);

        for (int i = 0; i < 7; i++)
            unPuertoEstelar.pasarTurno();

        assertDoesNotThrow(() -> unPuertoEstelar.aplicarAtaque(ataqueCasiLetal));
    }
}
