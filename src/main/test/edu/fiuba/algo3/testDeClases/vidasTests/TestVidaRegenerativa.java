package edu.fiuba.algo3.testDeClases.vidasTests;


import edu.fiuba.algo3.modelo.Ataque.DanioTerrestre;
import edu.fiuba.algo3.modelo.Excepciones.ErrorVidaLlegoACero;
import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Edificios.Vida.Vida;
import edu.fiuba.algo3.modelo.Edificios.Vida.VidaRegenerativa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestVidaRegenerativa {

    @Test
    public void test01PuedoCrearUnaVidaRegenerativa(){
        Vida unaVida = new VidaRegenerativa(100);

        assertNotNull(unaVida);
    }

    @Test
    public void test02CreoUnaVidaRegenerativaYLePuedoAplicarDanio(){
        Ataque unAtaque = new Ataque(new DanioTerrestre(100));

        Vida unaVida = new VidaRegenerativa(200);

        assertDoesNotThrow(() -> unaVida.aplicarAtaque(unAtaque));
    }

    @Test
    public void test03CreoUnaVidaRegenerativaYLeAplicoUnAtaqueMayorIgualALaVida(){
        // La vida se queda menor o igual que cero con un ataque igual a la vida
        Ataque unAtaque = new Ataque(new DanioTerrestre(100));

        Vida unaVida = new VidaRegenerativa(100);

        assertThrows(ErrorVidaLlegoACero.class, () -> unaVida.aplicarAtaque(unAtaque));
    }

    @Test
    public void test04CreoUnaVidaRegenerativaYComprueboQueEstaSeRegenera(){
        // Primero le hago 100 de daño y deberia quedarle 20 de vida, pero como paso de turno se regenera y le queda mas que eso
        Ataque unAtaque = new Ataque(new DanioTerrestre(100));
        Ataque unAtaqueDebil = new Ataque(new DanioTerrestre(20));

        Vida unaVida = new VidaRegenerativa(120);

        unaVida.aplicarAtaque(unAtaque);
        unaVida.pasarTurno();

        assertDoesNotThrow(() -> unaVida.aplicarAtaque(unAtaqueDebil));

    }

    @Test
    public void test05UnaVidaNoSePuedeRegenerarMasDeSuMaximoImpuesto(){
        // Paso un turno con la vida regenerativa entera y no supera ese maximo, porque se acaba cuando la ataco
        Ataque unAtaque = new Ataque(new DanioTerrestre(100));

        Vida unaVida = new VidaRegenerativa(100);

        unaVida.pasarTurno();

        assertThrows(ErrorVidaLlegoACero.class, () -> unaVida.aplicarAtaque(unAtaque));
    }

}