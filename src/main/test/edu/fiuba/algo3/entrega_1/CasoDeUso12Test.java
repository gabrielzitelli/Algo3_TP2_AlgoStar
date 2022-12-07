package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.*;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Ataque.*;

import edu.fiuba.algo3.modelo.Imperio.Suministro;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CasoDeUso12Test {

    @Test
    public void test01PuedoDaniarElEscudoDeUnAccesoPenetrandoLaVidaSeRegeneraYPuedoDaniarloDeVuelta(){
        Acceso unAcceso = new Acceso();
        Ataque ataqueCasiLetal = new Ataque(new DanioTerrestre(999));
        Ataque unAtaqueRompeEscudo = new Ataque(new DanioTerrestre(500));

        // Acceso tiene 500E / 500V
        unAcceso.recibirAtaque(ataqueCasiLetal); //Queda con 1 de vida y 0 de escudo

        for (int i = 0; i < 7; i++)
            unAcceso.pasarTurno();

        assertDoesNotThrow( () -> unAcceso.recibirAtaque(unAtaqueRompeEscudo) );
    }

    @Test
    public void test02PuedoDaniarElEscudoDeUnAsimiladorPenetrandoLaVidaSeRegeneraYPuedoDaniarloDeVuelta(){
        Recurso gasDelImperio = new Recurso(0);
        Asimilador unAsimilador = new Asimilador(gasDelImperio);
        Ataque ataqueCasiLetal = new Ataque(new DanioTerrestre(899));
        Ataque unAtaqueRompeEscudo = new Ataque(new DanioTerrestre(450));

        // Acceso tiene  450E / 450V
        unAsimilador.recibirAtaque(ataqueCasiLetal); //Queda con 1 de vida y 0 de escudo

        for (int i = 0; i < 7; i++)
            unAsimilador.pasarTurno();

        assertDoesNotThrow( () -> unAsimilador.recibirAtaque(unAtaqueRompeEscudo) );
    }

    @Test
    public void test03PuedoDaniarElEscudoDeUnNexoMineralPenetrandoLaVidaSeRegeneraYPuedoDaniarloDeVuelta(){
        Recurso mineralesDelImperio = new Recurso(0);
        NexoMineral unNexo = new NexoMineral(mineralesDelImperio);
        Ataque ataqueCasiLetal = new Ataque(new DanioTerrestre(499));
        Ataque unAtaqueRompeEscudo = new Ataque(new DanioTerrestre(250));

        // Acceso tiene  250E / 250V
        unNexo.recibirAtaque(ataqueCasiLetal); //Queda con 1 de vida y 0 de escudo

        for (int i = 0; i < 7; i++)
            unNexo.pasarTurno();

        assertDoesNotThrow( () -> unNexo.recibirAtaque(unAtaqueRompeEscudo) );
    }

    @Test
    public void test04PuedoDaniarElEscudoDeUnPilonPenetrandoLaVidaSeRegeneraYPuedoDaniarloDeVuelta(){
        Pilon unPilon = new Pilon();
        Ataque ataqueCasiLetal = new Ataque(new DanioTerrestre(599));
        Ataque unAtaqueRompeEscudo = new Ataque(new DanioTerrestre(300));
        unPilon.asignarSuministro(new Suministro(0));

        // Acceso tiene  300E / 300V
        unPilon.recibirAtaque(ataqueCasiLetal); //Queda con 1 de vida y 0 de escudo

        for (int i = 0; i < 7; i++)
            unPilon.pasarTurno();

        assertDoesNotThrow( () -> unPilon.recibirAtaque(unAtaqueRompeEscudo) );
    }

    @Test
    public void test05PuedoDaniarElEscudoDeUnPuertoEstelarPenetrandoLaVidaSeRegeneraYPuedoDaniarloDeVuelta(){
        PuertoEstelar unPuertoEstelar = new PuertoEstelar();
        Ataque ataqueCasiLetal = new Ataque(new DanioTerrestre(1199));
        Ataque unAtaqueRompeEscudo = new Ataque(new DanioTerrestre(599));

        // Acceso tiene  600E / 600V
        unPuertoEstelar.recibirAtaque(ataqueCasiLetal); //Queda con 1 de vida y 0 de escudo

        for (int i = 0; i < 7; i++)
            unPuertoEstelar.pasarTurno();

        assertDoesNotThrow( () -> unPuertoEstelar.recibirAtaque(unAtaqueRompeEscudo) );
    }

}
