package edu.fiuba.algo3.testVida;

import edu.fiuba.algo3.modelo.Excepciones.ErrorVidaLlegoACero;
import edu.fiuba.algo3.modelo.danioYAtaque.Ataque;
import edu.fiuba.algo3.modelo.danioYAtaque.DanioBasico;
import edu.fiuba.algo3.modelo.vida.Vida;
import edu.fiuba.algo3.modelo.vida.VidaConEscudo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestVidaConEscudo {

    @Test
    public void test01PuedoCrearUnaVidaRegenerativa(){
        Vida unaVida = new VidaConEscudo(100, 100);

        assertNotNull(unaVida);
    }

    @Test
    public void test02CreoUnaVidaConEscudoYLePuedoAplicarDanio(){
        Ataque unAtaque = new Ataque(new DanioBasico(100));

        Vida unaVida = new VidaConEscudo(100, 100);

        assertDoesNotThrow(() -> unaVida.aplicarAtaque(unAtaque));
    }

    @Test
    public void test03CreoUnaVidaConEscudoYLeAplicoUnAtaqueMayorIgualALaVidaMasElEscudo(){
        Ataque unAtaque = new Ataque(new DanioBasico(200));

        Vida unaVida = new VidaConEscudo(100, 100);

        assertThrows(ErrorVidaLlegoACero.class, () -> unaVida.aplicarAtaque(unAtaque));
    }

    @Test
    public void test04CreoUnaVidaConEscudoYLeAplicoUnAtaqueMenorALaVidaMasElEscudo(){
        Ataque unAtaque = new Ataque(new DanioBasico(100));
        Ataque unAtaqueDebil = new Ataque(new DanioBasico(20));

        Vida unaVida = new VidaConEscudo(100, 20);

        unaVida.aplicarAtaque(unAtaque);
        unaVida.pasarTurno();

        assertDoesNotThrow(() -> unaVida.aplicarAtaque(unAtaqueDebil));

    }

    @Test
    public void test05UnEscudoNoSePuedeRegenerarMasDeSuMaximoImpuesto(){
        // Paso un turno con la vida con escudo entera y no supera ese maximo, porque se acaba cuando la ataco
        Ataque unAtaque = new Ataque(new DanioBasico(120));

        Vida unaVida = new VidaConEscudo(100, 20);

        unaVida.pasarTurno();

        assertThrows(ErrorVidaLlegoACero.class, () -> unaVida.aplicarAtaque(unAtaque));
    }
}