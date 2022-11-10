package edu.fiuba.algo3.testVida;

import edu.fiuba.algo3.modelo.vida.VidaConEscudo;
import edu.fiuba.algo3.modelo.vida.VidaRegenerativa;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestVidaConEscudo {

    @Test
    public void SeCreaUnaVidaConEscudoYTieneLaCantidadDeVidaEsperada(){
        VidaConEscudo vidaConEscudo = new VidaConEscudo(15, 0.33, 5);
        int vidaEsperada = 20;

        assertEquals(vidaEsperada, vidaConEscudo.getVida());
    }

    @Test
    public void seCreaUnaVidaConEscudoSeLaDaniaYLaCantidadDeVidaEsLaEsperada(){
        VidaConEscudo vidaConEscudo = new VidaConEscudo(15, 0.33, 10);
        vidaConEscudo.aplicarDanio(5);
        int vidaEsperada = 20;

        assertEquals(vidaEsperada, vidaConEscudo.getVida());
    }

    @Test
    public void seCreaUnaVidaConEscudoSeLaDaniaYSePasanLaCantidadDeTurnosSuficientesParaQueSeLlene(){
        VidaConEscudo vidaConEscudo = new VidaConEscudo(20, 0.10, 20);
        vidaConEscudo.aplicarDanio(4);
        vidaConEscudo.accionDeTurno();
        vidaConEscudo.accionDeTurno();
        int vidaEsperada = 40;

        assertEquals(vidaEsperada, vidaConEscudo.getVida());

    }

    @Test
    public void seRegeneraUnaVidaConEscudoMuchasMasVecesDeLaNecesariaYSuVidaNoSuperaLaMaxima(){
        VidaConEscudo vidaConEscudo = new VidaConEscudo(20, 0.50, 100);
        vidaConEscudo.aplicarDanio(4);
        vidaConEscudo.accionDeTurno();
        vidaConEscudo.accionDeTurno();
        vidaConEscudo.accionDeTurno();
        int vidaEsperada = 120;

        assertEquals(vidaEsperada, vidaConEscudo.getVida());

    }

    @Test
    public void seRegeneraUnPocoElEscudoYElResultadoEsElEsperado(){
        VidaConEscudo vidaConEscudo = new VidaConEscudo(5, 0.10, 10);
        vidaConEscudo.aplicarDanio(7);
        vidaConEscudo.accionDeTurno();
        vidaConEscudo.accionDeTurno();
        int vidaEsperada = 10;

        assertEquals(vidaEsperada, vidaConEscudo.getVida());

    }

    @Test
    public void seRegeneraLaVidaConNumerosImparesEIgualSeLlegaALoEsperado(){
        VidaConEscudo vidaConEscudo = new VidaConEscudo(15, 0.33, 7);
        vidaConEscudo.aplicarDanio(4);
        vidaConEscudo.accionDeTurno();
        int vidaEsperada = 20;

        assertEquals(vidaEsperada, vidaConEscudo.getVida());

    }

    @Test
    public void seDaniaTodoElEscudoYUnPocoDeLaVidaYElResultadoEsElEsperado(){
        VidaConEscudo vidaConEscudo = new VidaConEscudo(5, 0.10, 10);
        vidaConEscudo.aplicarDanio(11);
        int vidaEsperada = 4;

        assertEquals(vidaEsperada, vidaConEscudo.getVida());
    }

    @Test
    public void seDaniaTodoElEscudoYUnPocoDeLaVidaYSeRegeneraTodoElEscudoYlaVidaEsLaEsperada() {
        VidaConEscudo vidaConEscudo = new VidaConEscudo(20, 0.50, 60);
        vidaConEscudo.aplicarDanio(70);
        vidaConEscudo.accionDeTurno();
        vidaConEscudo.accionDeTurno();
        int vidaEsperada = 70;

        assertEquals(vidaEsperada, vidaConEscudo.getVida());
    }

    @Test
    public void seDaniaTodoElEscudoYUnPocoDeLaVidaYSeRegeneraUnPocoElEscudoYlaVidaEsLaEsperada() {
        VidaConEscudo vidaConEscudo = new VidaConEscudo(20, 0.20, 60);
        vidaConEscudo.aplicarDanio(70);
        vidaConEscudo.accionDeTurno();
        vidaConEscudo.accionDeTurno();
        int vidaEsperada = 34;

        assertEquals(vidaEsperada, vidaConEscudo.getVida());
    }
}
