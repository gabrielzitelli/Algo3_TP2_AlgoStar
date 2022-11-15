package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.UnidadesZerg.Zerling;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestZerling {

    /*
    * Una unidad puede atacar y puede recibir danio
    * Una unidad puede ser terrestre o aerea
    * Una unidad puede atacar a una terrestre, a una aerea o a ambos
    * */

    @Test
    public void test01PuedoCrearUnZerling(){
        Zerling unZerling = new Zerling();

        assertNotNull(unZerling);
    }

    @Test
    public void test02UnZerlingPuedeDaniarAOtroZerling(){
        Zerling unZerling = new Zerling();
        Zerling ZerlingADaniar = new Zerling();

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        Coordenada coordenadaDeAtaque = new Coordenada(0,1);
        elMapa.colocarUnidadZerg(ZerlingADaniar, coordenadaDeAtaque);

        unZerling.atacar(coordenadaDeAtaque);
    }
}
