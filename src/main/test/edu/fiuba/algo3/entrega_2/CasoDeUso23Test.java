package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zerling;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CasoDeUso23Test {
    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
        Zerg imperioZerg = new Zerg();
        imperioZerg.abastecerDeRecursos();
    }

    @Test
    public void test01UnZerlingNoPuedeAtacarUnEdificioQueNoEstaEnSuRango() {
        Mapa elMapa = Mapa.obtener();
        Unidad zerling = new Zerling();
    }
}
