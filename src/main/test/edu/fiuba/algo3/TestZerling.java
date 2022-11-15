package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.EdificioProtoss.Pilon;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.UnidadesZerg.Zerling;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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

        Coordenada unaCoordenada = new Coordenada(0,0);
        Coordenada otraCoordenada = new Coordenada(0,1);

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarUnaUnidad(unZerling, unaCoordenada);
        elMapa.colocarUnaUnidad(ZerlingADaniar, otraCoordenada);

        assertDoesNotThrow(() -> elMapa.atacar(unaCoordenada, otraCoordenada));
    }

    /*@Test
    public void test03UnZerlingPuedeDaniarAUnPilon(){
        Zerling unZerling = new Zerling();
        Pilon pilon = new Pilon();

        Coordenada unaCoordenada = new Coordenada(0,0);
        Coordenada otraCoordenada = new Coordenada(0,1);

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarUnaUnidad(unZerling, unaCoordenada);
        elMapa.construirEdificio(pilon, otraCoordenada);

        assertDoesNotThrow(() -> elMapa.atacar(unaCoordenada, otraCoordenada));
    }*/
}
