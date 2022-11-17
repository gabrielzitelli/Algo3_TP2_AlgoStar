package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.*;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Ataque.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CasoDeUso12Test {

    @Test
    public void test01PuedoDaniarElEscudoDeUnAccesoPenetrandoLaVidaSeRegeneraYPuedoDaniarloDeVuelta(){
        Acceso unAcceso = new Acceso();
        Ataque ataqueCasiLetal = new Ataque( new Danio(999) );
        Ataque unAtaqueRompeEscudo = new Ataque( new Danio(500) );

        // Acceso tiene 500E / 500V
        unAcceso.aplicarAtaque(ataqueCasiLetal); //Queda con 1 de vida y 0 de escudo

        for (int i = 0; i < 7; i++)
            unAcceso.pasarTurno();

        assertDoesNotThrow( () -> unAcceso.aplicarAtaque(unAtaqueRompeEscudo) );
    }

    @Test
    public void test02PuedoDaniarElEscudoDeUnAsimiladorPenetrandoLaVidaSeRegeneraYPuedoDaniarloDeVuelta(){
        Recurso gasDelImperio = new Recurso(0);
        Asimilador unAsimilador = new Asimilador(gasDelImperio);
        Ataque ataqueCasiLetal = new Ataque( new Danio(899) );
        Ataque unAtaqueRompeEscudo = new Ataque( new Danio(450) );

        // Acceso tiene  450E / 450V
        unAsimilador.aplicarAtaque(ataqueCasiLetal); //Queda con 1 de vida y 0 de escudo

        for (int i = 0; i < 7; i++)
            unAsimilador.pasarTurno();

        assertDoesNotThrow( () -> unAsimilador.aplicarAtaque(unAtaqueRompeEscudo) );
    }

    @Test
    public void test03PuedoDaniarElEscudoDeUnNexoMineralPenetrandoLaVidaSeRegeneraYPuedoDaniarloDeVuelta(){
        Recurso mineralesDelImperio = new Recurso(0);
        NexoMineral unNexo = new NexoMineral(mineralesDelImperio);
        Ataque ataqueCasiLetal = new Ataque( new Danio(499) );
        Ataque unAtaqueRompeEscudo = new Ataque( new Danio(250) );

        // Acceso tiene  250E / 250V
        unNexo.aplicarAtaque(ataqueCasiLetal); //Queda con 1 de vida y 0 de escudo

        for (int i = 0; i < 7; i++)
            unNexo.pasarTurno();

        assertDoesNotThrow( () -> unNexo.aplicarAtaque(unAtaqueRompeEscudo) );
    }

    @Test
    public void test04PuedoDaniarElEscudoDeUnPilonPenetrandoLaVidaSeRegeneraYPuedoDaniarloDeVuelta(){
        Pilon unPilon = new Pilon();
        Ataque ataqueCasiLetal = new Ataque( new Danio(599) );
        Ataque unAtaqueRompeEscudo = new Ataque( new Danio(300) );

        // Acceso tiene  300E / 300V
        unPilon.aplicarAtaque(ataqueCasiLetal); //Queda con 1 de vida y 0 de escudo

        for (int i = 0; i < 7; i++)
            unPilon.pasarTurno();

        assertDoesNotThrow( () -> unPilon.aplicarAtaque(unAtaqueRompeEscudo) );
    }

    @Test
    public void test05PuedoDaniarElEscudoDeUnPuertoEstelarPenetrandoLaVidaSeRegeneraYPuedoDaniarloDeVuelta(){
        PuertoEstelar unPuertoEstelar = new PuertoEstelar();
        Ataque ataqueCasiLetal = new Ataque( new Danio(1199) );
        Ataque unAtaqueRompeEscudo = new Ataque( new Danio(599) );

        // Acceso tiene  600E / 600V
        unPuertoEstelar.aplicarAtaque(ataqueCasiLetal); //Queda con 1 de vida y 0 de escudo

        for (int i = 0; i < 7; i++)
            unPuertoEstelar.pasarTurno();

        assertDoesNotThrow( () -> unPuertoEstelar.aplicarAtaque(unAtaqueRompeEscudo) );
    }

}
