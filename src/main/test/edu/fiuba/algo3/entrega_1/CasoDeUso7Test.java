package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.MineralRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.UnidadesZerg.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CasoDeUso7Test {

    @Test
    public void Test01MandoUnZanganoAExtraerUnMineralYRevisoQueDepositeEnElImperio(){
        Coordenada coordenada = new Coordenada(0,0);
        Recurso mineralDelImperio = new Recurso(0);
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();
        elMapa.colocarMaterial(new MineralRecolectable(),coordenada);

        UnidadZerg saraza = new Zangano();
        saraza.setDepositoRecurso(mineralDelImperio);
        elMapa.colocarUnidadZerg(saraza, coordenada);

        saraza.extraer();

        assertTrue(mineralDelImperio.tenesCantidadDeRecurso(10));
    }
}
