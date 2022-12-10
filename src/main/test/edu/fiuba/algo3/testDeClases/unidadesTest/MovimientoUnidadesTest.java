package edu.fiuba.algo3.testDeClases.unidadesTest;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Criadero;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaZangano;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaCriadero;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaExtractor;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Casilla.GasRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovimientoUnidadesTest {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }
    @Test
    public void test01MuevoUnZanganoAUnExtractorYMeExtre(){
        Zerg imperio = new Zerg();
        imperio.abastecerDeRecursos(new Mineral(500), new Gas(0));

        imperio.construirEdificio(new FabricaCriadero(), new Coordenada(0,0));

        for (int i = 0; i < 5; i++) {
            imperio.terminarTurno();
        }
        Criadero criadero = (Criadero) Mapa.obtener().obtenerOcupable(new Coordenada(0,0));
        Mapa.obtener().colocarMaterial(new GasRecolectable(), new Coordenada(1,1));
        imperio.construirEdificio(new FabricaExtractor(), new Coordenada(1,1));
        criadero.crearUnidad(new FabricaZangano());

        for (int i = 0; i < 6; i++) {
            imperio.terminarTurno();
        }

        Unidad zangano = (Unidad) Mapa.obtener().obtenerOcupable(new Coordenada(0, 1));

        assertTrue(imperio.tienesEstaCantidadDeGas(0));

        zangano.moverA(new Coordenada(1,1));

        imperio.terminarTurno();

        assertTrue(imperio.tienesEstaCantidadDeGas(10));

    }
}
