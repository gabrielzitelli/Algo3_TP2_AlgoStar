package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Tablero.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
Objetivo:
Verificar que al dañar una construcción protoss sin quitarle todo el escudo, la misma
recupera su escudo por turnos hasta volver a tener el 100% del mismo
*/
public class CasoDeUso11Test {

    // Testeo NexoMineral

    @Test
    public void test01ConstruyoUnNexoMineralYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraDeManeraEsperada(){
        NodoCompatible nodoCompatibleNexoMineral = new NodoCompatible(new Neutro(), new NodoMineral());
        Recurso mineral = new Recurso(50);
        NexoMineral unNexoMineral = new NexoMineral(nodoCompatibleNexoMineral, mineral);

        // NexoMineral tiene 250E / 250 V
        unNexoMineral.recibirDanio(249);
        unNexoMineral.accionDeTurno();
        unNexoMineral.accionDeTurno();
        int vidaEsperada = 375;

        assertEquals(vidaEsperada, unNexoMineral.getVida());
    }

    @Test
    public void test02ConstruyoUnNexoMineralYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraTotalmenteAlPasarLosTurnosAdecuados(){
        NodoCompatible nodoCompatibleNexoMineral = new NodoCompatible(new Neutro(), new NodoMineral());
        Recurso mineral = new Recurso(100);
        NexoMineral unNexoMineral = new NexoMineral(nodoCompatibleNexoMineral, mineral);
        unNexoMineral.esCompatible(new Neutro(), new NodoMineral());

        // NexoMineral tiene 250E / 250 V
        unNexoMineral.recibirDanio(249);
        unNexoMineral.accionDeTurno();
        unNexoMineral.accionDeTurno();
        unNexoMineral.accionDeTurno();
        unNexoMineral.accionDeTurno();
        unNexoMineral.accionDeTurno();

        int vidaEsperada = 500;

        assertEquals(vidaEsperada, unNexoMineral.getVida());
    }

    @Test
    public void test03ConstruyoUnNexoMineralYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraTotalmenteYNoSeExcedeDelMaximo(){
        NodoCompatible nodoCompatibleNexoMineral = new NodoCompatible(new Neutro(), new NodoMineral());
        Recurso mineral = new Recurso(0);
        NexoMineral unNexoMineral = new NexoMineral(nodoCompatibleNexoMineral, mineral);
        unNexoMineral.esCompatible(new Neutro(), new NodoMineral());

        // NexoMineral tiene 250E / 250V
        unNexoMineral.recibirDanio(249);

        for(int i = 0; i < 10; i++)
            unNexoMineral.accionDeTurno();

        int vidaEsperada = 500;

        assertEquals(vidaEsperada, unNexoMineral.getVida());
    }

    // Testeo Pilon

    @Test
    public void test04ConstruyoUnPilonYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraDeManeraEsperada(){
        NodoCompatible nodoCompatiblePilon = new NodoCompatible(new Neutro(), new SinRecurso());
        Pilon unPilon = new Pilon(nodoCompatiblePilon);

        // Pilon tiene 300E / 300V
        unPilon.recibirDanio(299);
        unPilon.accionDeTurno();
        unPilon.accionDeTurno();
        int vidaEsperada = 451;

        assertEquals(vidaEsperada, unPilon.getVida());
    }

    @Test
    public void test05ConstruyoUnPilonYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraTotalmenteAlPasarLosTurnosAdecuados(){
        NodoCompatible nodoCompatiblePilon = new NodoCompatible(new Neutro(), new SinRecurso());
        Pilon unPilon = new Pilon(nodoCompatiblePilon);

        // Pilon tiene 300E / 300V
        unPilon.recibirDanio(299);

        unPilon.accionDeTurno();
        unPilon.accionDeTurno();
        unPilon.accionDeTurno();
        unPilon.accionDeTurno();

        int vidaEsperada = 600;

        assertEquals(vidaEsperada, unPilon.getVida());
    }

    @Test
    public void test06ConstruyoUnPilonYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraTotalmenteYNoSeExcedeDelMaximo(){
        NodoCompatible nodoCompatiblePilon = new NodoCompatible(new Neutro(), new SinRecurso());
        Pilon unPilon = new Pilon(nodoCompatiblePilon);

        // Pilon tiene 300E / 300V
        unPilon.recibirDanio(299);

        for(int i = 0; i < 10; i++)
            unPilon.accionDeTurno();

        int vidaEsperada = 600;

        assertEquals(vidaEsperada, unPilon.getVida());
    }

    // Testeo Asimilador

    @Test
    public void test07ConstruyoUnAsimiladorYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraDeManeraEsperada(){
        NodoCompatible nodoCompatibleAsimilador = new NodoCompatible(new Neutro(), new VolcanGasVespeno());
        Recurso gasVespeno = new Recurso(0);
        Asimilador unAsimilador = new Asimilador(nodoCompatibleAsimilador, gasVespeno);

        // Asimilador tiene 450E / 450V
        unAsimilador.recibirDanio(449);
        unAsimilador.accionDeTurno();
        unAsimilador.accionDeTurno();
        int vidaEsperada = 675;

        assertEquals(vidaEsperada, unAsimilador.getVida());
    }

    @Test
    public void test08ConstruyoUnAsimiladorYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraTotalmenteAlPasarLosTurnosAdecuados(){
        NodoCompatible nodoCompatibleAsimilador = new NodoCompatible(new Neutro(), new VolcanGasVespeno());
        Recurso gasVespeno = new Recurso(0);
        Asimilador unAsimilador= new Asimilador(nodoCompatibleAsimilador, gasVespeno);

        // Asimilador tiene 450E / 450V
        unAsimilador.recibirDanio(449);

        unAsimilador.accionDeTurno();
        unAsimilador.accionDeTurno();
        unAsimilador.accionDeTurno();
        unAsimilador.accionDeTurno();
        unAsimilador.accionDeTurno();

        int vidaEsperada = 900;

        assertEquals(vidaEsperada, unAsimilador.getVida());
    }

    @Test
    public void test09ConstruyoUnAsimiladorYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraTotalmenteYNoSeExcedeDelMaximo(){
        NodoCompatible nodoCompatibleAsimilador = new NodoCompatible(new Neutro(), new VolcanGasVespeno());
        Recurso gasVespeno = new Recurso(0);
        Asimilador unAsimilador = new Asimilador(nodoCompatibleAsimilador, gasVespeno);
        unAsimilador.esCompatible(new Neutro(), new VolcanGasVespeno());

        // Asimilador tiene 450E / 450V
        unAsimilador.recibirDanio(449);

        for(int i = 0; i < 10; i++)
            unAsimilador.accionDeTurno();

        int vidaEsperada = 900;

        assertEquals(vidaEsperada, unAsimilador.getVida());
    }

    // Testeo Acceso

    @Test
    public void test10ConstruyoUnAccesoYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraDeManeraEsperada(){
        NodoCompatible nodoCompatibleAcceso = new NodoCompatible(new Energia(), new SinRecurso());
        Acceso unAcceso = new Acceso(nodoCompatibleAcceso);

        // Acceso tiene 500E / 500V
        unAcceso.recibirDanio(499);
        unAcceso.accionDeTurno();
        unAcceso.accionDeTurno();
        int vidaEsperada = 751;

        assertEquals(vidaEsperada, unAcceso.getVida());
    }

    @Test
    public void test11ConstruyoUnAccesoYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraTotalmenteAlPasarLosTurnosAdecuados(){
        NodoCompatible nodoCompatibleAcceso = new NodoCompatible(new Energia(), new SinRecurso());
        Acceso unAcceso = new Acceso(nodoCompatibleAcceso);

        // Acceso tiene 500E / 500V
        unAcceso.recibirDanio(499);

        unAcceso.accionDeTurno();
        unAcceso.accionDeTurno();
        unAcceso.accionDeTurno();
        unAcceso.accionDeTurno();

        int vidaEsperada = 1000;

        assertEquals(vidaEsperada, unAcceso.getVida());
    }

    @Test
    public void test12ConstruyoUnAccesoYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraTotalmenteYNoSeExcedeDelMaximo(){
        NodoCompatible nodoCompatibleAcceso = new NodoCompatible(new Energia(), new SinRecurso());
        Acceso unAcceso = new Acceso(nodoCompatibleAcceso);

        // Acceso tiene 500E / 500V
        unAcceso.recibirDanio(499);

        for(int i = 0; i < 10; i++)
            unAcceso.accionDeTurno();

        int vidaEsperada = 1000;

        assertEquals(vidaEsperada, unAcceso.getVida());
    }

    // Testeo Puerto Estelar

    @Test
    public void test13ConstruyoUnPuertoEstelarYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraDeManeraEsperada(){
        NodoCompatible nodoCompatiblePuertoEstelar = new NodoCompatible(new Energia(), new SinRecurso());
        PuertoEstelar unPuertoEstelar = new PuertoEstelar(nodoCompatiblePuertoEstelar);

        // PuertoEstelar tiene 600E / 600V
        unPuertoEstelar.recibirDanio(599);
        unPuertoEstelar.accionDeTurno();
        unPuertoEstelar.accionDeTurno();
        int vidaEsperada = 901;

        assertEquals(vidaEsperada, unPuertoEstelar.getVida());
    }

    @Test
    public void test14ConstruyoUnPuertoEstelarYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraTotalmenteAlPasarLosTurnosAdecuados(){
        NodoCompatible nodoCompatiblePuertoEstelar = new NodoCompatible(new Energia(), new SinRecurso());
        PuertoEstelar unPuertoEstelar = new PuertoEstelar(nodoCompatiblePuertoEstelar);

        // PuertoEstelar tiene 600E / 600V
        unPuertoEstelar.recibirDanio(599);

        unPuertoEstelar.accionDeTurno();
        unPuertoEstelar.accionDeTurno();
        unPuertoEstelar.accionDeTurno();
        unPuertoEstelar.accionDeTurno();

        int vidaEsperada = 1200;

        assertEquals(vidaEsperada, unPuertoEstelar.getVida());
    }

    @Test
    public void test15ConstruyoUnPuertoEstelarYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraTotalmenteYNoSeExcedeDelMaximo(){
        NodoCompatible nodoCompatiblePuertoEstelar = new NodoCompatible(new Energia(), new SinRecurso());
        PuertoEstelar unPuertoEstelar = new PuertoEstelar(nodoCompatiblePuertoEstelar);

        // PuertoEstelar tiene 600E / 600V
        unPuertoEstelar.recibirDanio(599);

        for(int i = 0; i < 10; i++)
            unPuertoEstelar.accionDeTurno();

        int vidaEsperada = 1200;

        assertEquals(vidaEsperada, unPuertoEstelar.getVida());
    }
}
