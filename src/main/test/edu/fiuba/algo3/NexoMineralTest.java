package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.EdificioProtoss.NexoMineral;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.MineralBruto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NexoMineralTest {

    @Test
    public void test01PuedoCrearUnNexoMineral(){
        Recurso mineralesDelImperio = new Recurso(0);
        MineralBruto nodoMineral = new MineralBruto();

        NexoMineral unNexo = new NexoMineral(mineralesDelImperio, nodoMineral);

        assertNotNull(unNexo);
    }

    @Test
    public void test02NoPuedoExtraerElMineralDeUnNexoMineralQueNoEstaConstruidoEn3Turnos(){
        Recurso mineralesDelImperio = new Recurso(0);
        MineralBruto nodoMineral = new MineralBruto();

        NexoMineral unNexo = new NexoMineral(mineralesDelImperio, nodoMineral);

        for(int i = 0; i < 3; i++)
            unNexo.pasarTurno();

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unNexo.extraer());
    }

    @Test
    public void test03PuedoExtraerElMineralDeUnNexoMineralQueEstaConstruidoEn4Turnos(){
        Recurso mineralesDelImperio = new Recurso(0);
        MineralBruto nodoMineral = new MineralBruto();

        NexoMineral unNexo = new NexoMineral(mineralesDelImperio, nodoMineral);

        for(int i = 0; i < 4; i++)
            unNexo.pasarTurno();

        assertDoesNotThrow(() -> unNexo.extraer());
    }
}
