package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoPuedeAtacarUnidadVoladora;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieAerea;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.UnidadesZerg.Mutalisco;
import edu.fiuba.algo3.modelo.UnidadesZerg.Zerling;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso19Test {

    // Verificar que todas las unidades que no tienen ataque de aire no puedan atacar a una unidad voladora

    // Zerling no puede atacar a unidad voladora
    // -> Zerling no puede atacar a Mutalisco
    // -> Zerling no puede atacar a Guardian
    // -> Zerling no puede atacar a Scout

    // Zealot no puede atacar a unidad voladora
    // -> Zealot no puede atacar a Mutalisco
    // -> Zealot no puede atacar a Guardian
    // -> Zealot no puede atacar a Scout

    @Test
    public void test01SiUnZerglingIntentaAtacarAUnMutaliscoSeLanzaExcepcion() {
        Zerling unZerling = new Zerling();
        Mutalisco unMutalisco = new Mutalisco();

        Coordenada coordenadaZerling = new Coordenada(0,0);
        Coordenada coordenadaMutalisco = new Coordenada(0,1);

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarSuperficie(new SuperficieTerrestre(), coordenadaZerling);
        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaMutalisco);

        elMapa.colocarUnaUnidad(unZerling, coordenadaZerling);
        elMapa.colocarUnaUnidad(unMutalisco, coordenadaMutalisco);

        assertThrows(ErrorNoPuedeAtacarUnidadVoladora.class,
                () -> elMapa.atacar(coordenadaZerling, coordenadaMutalisco));
    }
}
