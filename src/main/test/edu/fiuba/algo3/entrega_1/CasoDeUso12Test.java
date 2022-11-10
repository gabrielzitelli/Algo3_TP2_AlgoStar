package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.NexoMineral;
import edu.fiuba.algo3.modelo.NodoCompatible;
import edu.fiuba.algo3.modelo.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Moho;
import edu.fiuba.algo3.modelo.Tablero.SinRecurso;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CasoDeUso12Test {

    NodoCompatible nodo = new NodoCompatible(new Moho(), new SinRecurso());
    /*@Test
    public void test01PuedoDaniarUnNexoMineralYSeRegeneraSoloElEscudo{
        Recurso mineral = new Recurso(100);
        NexoMineral unNexoMineral = new NexoMineral(nodo, mineral);
        // 250E/250V

        unNexoMineral.recibirDanio(499);


        int vidaEsperada = 1;

        assertEquals( vidaEsperada, unNexoMineral.getVida() );
    }*/
}
