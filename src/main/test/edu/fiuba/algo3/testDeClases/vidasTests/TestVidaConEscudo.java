package edu.fiuba.algo3.testDeClases.vidasTests;

import edu.fiuba.algo3.modelo.Ataque.DanioTerrestre;
import edu.fiuba.algo3.modelo.Excepciones.ErrorVidaLlegoACero;
import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Ataque.Danio;
import edu.fiuba.algo3.modelo.Vida.Vida;
import edu.fiuba.algo3.modelo.Vida.VidaConEscudo;
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
        Ataque unAtaque = new Ataque(new DanioTerrestre(100));

        Vida unaVida = new VidaConEscudo(100, 100);

        assertDoesNotThrow(() -> unaVida.aplicarAtaque(unAtaque));
    }

    @Test
    public void test03CreoUnaVidaConEscudoYLeAplicoUnAtaqueMayorIgualALaVidaMasElEscudo(){
        Ataque unAtaque = new Ataque(new DanioTerrestre(200));

        Vida unaVida = new VidaConEscudo(100, 100);

        assertThrows(ErrorVidaLlegoACero.class, () -> unaVida.aplicarAtaque(unAtaque));
    }

    @Test
    public void test04CreoUnaVidaConEscudoYLeAplicoUnAtaqueMenorALaVidaMasElEscudo(){
        Ataque unAtaque = new Ataque(new DanioTerrestre(100));
        Ataque unAtaqueDebil = new Ataque(new DanioTerrestre(20));

        Vida unaVida = new VidaConEscudo(100, 20);

        unaVida.aplicarAtaque(unAtaque);
        unaVida.pasarTurno();

        assertDoesNotThrow(() -> unaVida.aplicarAtaque(unAtaqueDebil));

    }

    @Test
    public void test05UnEscudoNoSePuedeRegenerarMasDeSuMaximoImpuesto(){
        // Paso un turno con la vida con escudo entera y no supera ese maximo, porque se acaba cuando la ataco
        Ataque unAtaque = new Ataque(new DanioTerrestre(120));

        Vida unaVida = new VidaConEscudo(100, 20);

        unaVida.pasarTurno();

        assertThrows(ErrorVidaLlegoACero.class, () -> unaVida.aplicarAtaque(unAtaque));
    }
}