package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaAcceso;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaPilon;
import edu.fiuba.algo3.modelo.Excepciones.ErrorLaUnidadNoPuedeAtacarFueraDeSuRango;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Dragon;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zerling;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso23Test {
    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01UnZerlingNoPuedeAtacarUnEdificioQueNoEstaEnSuRango() {
        Protoss imperioProtoss = new Protoss();
        imperioProtoss.abastecerDeRecursos();
        Mapa elMapa = Mapa.obtener();
        Unidad zerling = new Zerling();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaEdificio = new Coordenada(0,2);

        elMapa.colocarOcupable(zerling, coordenadaAtacante);
        imperioProtoss.construirEdificio(new FabricaPilon(), new Coordenada(0,1));

        for (int i = 0; i < 5; i++)
            imperioProtoss.terminarTurno();

        imperioProtoss.construirEdificio(new FabricaAcceso(), coordenadaEdificio);
        assertThrows(ErrorLaUnidadNoPuedeAtacarFueraDeSuRango.class, () ->
                elMapa.atacar(coordenadaAtacante, coordenadaEdificio));
    }
    @Test
    public void test02UnZerlingPuedeAtacarUnEdificioQueEstaEnSuRango() {
        Protoss imperioProtoss = new Protoss();
        imperioProtoss.abastecerDeRecursos();
        Mapa elMapa = Mapa.obtener();
        Unidad zerling = new Zerling();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaEdificio = new Coordenada(1,0);

        elMapa.colocarOcupable(zerling, coordenadaAtacante);
        imperioProtoss.construirEdificio(new FabricaPilon(), new Coordenada(0,1));

        for (int i = 0; i < 5; i++)
            imperioProtoss.terminarTurno();

        imperioProtoss.construirEdificio(new FabricaAcceso(), coordenadaEdificio);
        assertDoesNotThrow(() -> elMapa.atacar(coordenadaAtacante, coordenadaEdificio));
    }
    @Test
    public void test03UnDragonPuedeAtacarUnZerlingQueEstaEnSuRango() {
        Mapa elMapa = Mapa.obtener();
        Unidad zerling = new Zerling();
        Unidad dragon = new Dragon();
        Coordenada coordenadaDragon = new Coordenada(0,0);
        Coordenada coordenadaZerling = new Coordenada(7,3);
        Coordenada coordenadaAtacada = new Coordenada(4,0);

        elMapa.colocarOcupable(zerling, coordenadaZerling);
        elMapa.colocarOcupable(dragon, coordenadaDragon);

        assertThrows(ErrorLaUnidadNoPuedeAtacarFueraDeSuRango.class, () ->
                elMapa.atacar(coordenadaDragon,coordenadaZerling));

        elMapa.moverUnidad(coordenadaZerling, coordenadaAtacada);

        assertDoesNotThrow(() -> elMapa.atacar(coordenadaDragon, coordenadaAtacada));
    }
}
