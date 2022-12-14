package edu.fiuba.algo3.testDeClases.unidadesTest;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Criadero;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasUnidadesZangano;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaCriadero;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaExtractor;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Casilla.GasRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovimientoUnidadesTest {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }
    @Test
    public void test01MuevoUnZanganoAUnExtractorYMeExtre() {
        Zerg imperio = new Zerg();
        imperio.abastecerDeRecursos(new Mineral(500), new Gas(0));

        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0, 0);
        elMapa.colocarOcupable(new Zangano(), coordenadaCriadero);
        imperio.construirEdificio(new FabricaCriadero(), coordenadaCriadero);

        for (int i = 0; i < 5; i++) {
            imperio.terminarTurno();
        }

        Criadero criadero = (Criadero) elMapa.obtenerOcupable(new Coordenada(0, 0));
        elMapa.colocarMaterial(new GasRecolectable(), new Coordenada(1, 1));

        Coordenada coordenadaExtractor = new Coordenada(1,1);
        elMapa.colocarOcupable(new Zangano(), coordenadaExtractor);
        imperio.construirEdificio(new FabricaExtractor(), coordenadaExtractor);

        criadero.crearUnidad(new FabricasUnidadesZangano());

        for (int i = 0; i < 6; i++) {
            imperio.terminarTurno();
        }

        Unidad zangano = (Unidad) elMapa.obtenerOcupable(new Coordenada(0, 1));

        assertTrue(imperio.tienesEstaCantidadDeGas(0));

        zangano.moverA(new Coordenada(1,1));

        imperio.terminarTurno();

        assertTrue(imperio.tienesEstaCantidadDeGas(10));
    }
}
