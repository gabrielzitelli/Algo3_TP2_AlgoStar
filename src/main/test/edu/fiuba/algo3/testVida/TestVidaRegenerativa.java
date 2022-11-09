package edu.fiuba.algo3.testVida;

import edu.fiuba.algo3.modelo.vida.VidaRegenerativa;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestVidaRegenerativa {

    /*
    @Test
    public void seCreaUnaVidaYTieneLaCantidadDeVidaEsperada(){
        Vida vida = new Vida(10);
        int vidaEsperada = 10;

        assertEquals(vida.getVida(), vidaEsperada);
    }

    @Test
    public void seCreaUnaVidaSeLaDaniaYLaCantidadDeVidaEsLaEsperada(){
        Vida vida = new Vida(10);
        vida.aplicarDanio(5);
        int vidaEsperada = 5;

        assertEquals(vida.getVida(), vidaEsperada);
    }
    */

    @Test
    public void SeCreaUnaVidaRegenerativaYTieneLaCantidadDeVidaEsperada(){
        VidaRegenerativa vidaRegenerativa = new VidaRegenerativa(15, 0.33);
        int vidaEsperada = 15;

        assertEquals(vidaEsperada, vidaRegenerativa.getVida());
    }

    @Test
    public void seCreaUnaVidaRegenerativaSeLaDaniaYLaCantidadDeVidaEsLaEsperada(){
        VidaRegenerativa vidaRegenerativa = new VidaRegenerativa(15, 0.33);
        vidaRegenerativa.aplicarDanio(5);
        int vidaEsperada = 10;

        assertEquals(vidaEsperada, vidaRegenerativa.getVida());
    }

    @Test
    public void seCreaUnaVidaRegenerativaSeLaDaniaYSePasanLaCantidadDeTurnosSuficientesParaQueSeLlene(){
        VidaRegenerativa vidaRegenerativa = new VidaRegenerativa(20, 0.10);
        vidaRegenerativa.aplicarDanio(4);
        vidaRegenerativa.accionDeTurno();
        vidaRegenerativa.accionDeTurno();
        int vidaEsperada = 20;

        assertEquals(vidaEsperada, vidaRegenerativa.getVida());

    }

    @Test
    public void seRegeneraUnaVidaRegenerativaMuchasMasVecesDeLaNecesariaYSuVidaNoSuperaLaMaxima(){
        VidaRegenerativa vidaRegenerativa = new VidaRegenerativa(20, 0.50);
        vidaRegenerativa.aplicarDanio(4);
        vidaRegenerativa.accionDeTurno();
        vidaRegenerativa.accionDeTurno();
        int vidaEsperada = 20;

        assertEquals(vidaEsperada, vidaRegenerativa.getVida());

    }

    @Test
    public void seRegeneraUnPocoLaVidaYElResultadoEsElEsperado(){
        VidaRegenerativa vidaRegenerativa = new VidaRegenerativa(10, 0.10);
        vidaRegenerativa.aplicarDanio(7);
        vidaRegenerativa.accionDeTurno();
        vidaRegenerativa.accionDeTurno();
        int vidaEsperada = 5;

        assertEquals(vidaEsperada, vidaRegenerativa.getVida());

    }

    @Test
    public void seRegeneraLaVidaConNumerosImparesEIgualSeLlegaALoEsperado(){
        VidaRegenerativa vidaRegenerativa = new VidaRegenerativa(15, 0.10);
        vidaRegenerativa.aplicarDanio(4);
        vidaRegenerativa.accionDeTurno();
        int vidaEsperada = 12;

        assertEquals(vidaEsperada, vidaRegenerativa.getVida());

    }

}
