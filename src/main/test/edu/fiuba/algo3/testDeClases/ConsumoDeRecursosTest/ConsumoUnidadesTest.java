package edu.fiuba.algo3.testDeClases.ConsumoDeRecursosTest;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasUnidadesZangano;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaCriadero;
import edu.fiuba.algo3.modelo.Excepciones.ErrorCantidadDeRecursoInsuficiente;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConsumoUnidadesTest {
    @BeforeEach
    public void setup() {
        Mapa.obtener().reiniciarMapa();
    }
    @Test
    public void test01NoPuedoCrearUnZanganoSinTenerLosRecursosNecesarios() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        elMapa.colocarOcupable(new Zangano(), coordenadaCriadero);

        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(200), new Gas(0));

        imperioZerg.construirEdificio(new FabricaCriadero(), coordenadaCriadero);

        //Esperamos a que se construya el criadero
        for(int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        //obtenemos el edificio
        Edificio criadero = imperioZerg.conseguirEdificio(coordenadaCriadero);

        //No podemos crear la unidad porque no tenemos más recursos
        assertThrows(ErrorCantidadDeRecursoInsuficiente.class, () ->
                criadero.crearUnidad(new FabricasUnidadesZangano()));
    }
    @Test
    public void test02PuedoCrearUnZanganoConLosRecursosNecesarios() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        elMapa.colocarOcupable(new Zangano(), coordenadaCriadero);

        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(225), new Gas(0));

        imperioZerg.construirEdificio(new FabricaCriadero(), coordenadaCriadero);

        //Esperamos a que se construya el criadero
        for(int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        //obtenemos el edificio
        Edificio criadero = imperioZerg.conseguirEdificio(coordenadaCriadero);

        //Podemos crear la unidad porque tenemos los recursos
        assertDoesNotThrow( () -> criadero.crearUnidad(new FabricasUnidadesZangano()));
    }
}
