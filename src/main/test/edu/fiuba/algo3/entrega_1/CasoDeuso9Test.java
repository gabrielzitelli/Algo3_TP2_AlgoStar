package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.*;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.Fabrica;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricaDragon;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Excepciones.ErrorElEdificioNoTieneCarga;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.*;
import edu.fiuba.algo3.modelo.Ataque.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeuso9Test {

    FabricasDisponibles fabricasDisponibles = new FabricasDisponibles();

    @BeforeEach
    public void setupFabricasDisponibles() {
        ArrayList<Fabrica> fabricasHabilitada = new ArrayList<Fabrica>();
        fabricasHabilitada.add(new FabricaDragon());
        fabricasDisponibles.aumentar(fabricasHabilitada);
    }

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01UnEdificioSigueEnergizadoSiDosPilonesLeDanEnergia(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaAcceso = new Coordenada(0,2);
        Coordenada coordenadaPilon1 = new Coordenada(0,0);
        Coordenada coordenadaPilon2 = new Coordenada(0,4);
        Acceso unAcceso = new Acceso();
        unAcceso.asignarRecursos(new Mineral(1000), new Gas(1000));
        unAcceso.asignarListaDeUnidades(fabricasDisponibles);
        unAcceso.asignarSuministro(new Suministro(5));
        Pilon pilon1 = new Pilon();
        Pilon pilon2 = new Pilon();

        pilon1.asignarSuministro(new Suministro(0));
        pilon2.asignarSuministro(new Suministro(0));

        elMapa.colocarOcupable(pilon1, coordenadaPilon1);
        elMapa.colocarOcupable(pilon2, coordenadaPilon2);

        for (int i = 0; i < 5; i++) {
            pilon1.pasarTurno();
            pilon2.pasarTurno();
        }

        elMapa.colocarOcupable(unAcceso, coordenadaAcceso);

        for (int i = 0; i < 8; i++)
            unAcceso.pasarTurno();

        assertDoesNotThrow(() -> unAcceso.crearUnidad(new FabricaDragon()));
    }

    @Test
    public void test02UnEdificioSigueEnergizadoSiSeDestruyeUnoDeLosDosPilonesQueLeDanEnergia(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaAcceso = new Coordenada(0,2);
        Coordenada coordenadaPilon1 = new Coordenada(0,0);
        Coordenada coordenadaPilon2 = new Coordenada(0,4);
        Acceso unAcceso = new Acceso();
        unAcceso.asignarListaDeUnidades(fabricasDisponibles);
        unAcceso.asignarRecursos(new Mineral(1000), new Gas(1000));
        unAcceso.asignarSuministro(new Suministro(5));
        Pilon pilon1 = new Pilon();
        Pilon pilon2 = new Pilon();
        Ataque unAtaque = new Ataque(new DanioTerrestre(600));

        pilon1.asignarSuministro(new Suministro(0));
        pilon2.asignarSuministro(new Suministro(0));

        elMapa.colocarOcupable(pilon1, coordenadaPilon1);
        elMapa.colocarOcupable(pilon2, coordenadaPilon2);

        for (int i = 0; i < 5; i++) {
            pilon1.pasarTurno();
            pilon2.pasarTurno();
        }

        elMapa.colocarOcupable(unAcceso, coordenadaAcceso);

        for (int i = 0; i < 8; i++)
            unAcceso.pasarTurno();

        pilon2.recibirAtaque(unAtaque);

        unAcceso.pasarTurno();
        pilon1.pasarTurno();
        pilon2.pasarTurno();

        assertDoesNotThrow(() -> unAcceso.crearUnidad(new FabricaDragon()));
    }
    @Test
    public void test03UnEdificioNoSigueEnergizadoSiSeDestruyenTodosLosPilonesQueLeDanEnergia(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaAcceso = new Coordenada(0,2);
        Coordenada coordenadaPilon1 = new Coordenada(0,0);
        Coordenada coordenadaPilon2 = new Coordenada(0,4);
        Acceso unAcceso = new Acceso();
        unAcceso.asignarListaDeUnidades(fabricasDisponibles);
        Pilon pilon1 = new Pilon();
        Pilon pilon2 = new Pilon();
        Ataque unAtaque = new Ataque(new DanioTerrestre(600));

        pilon1.asignarSuministro(new Suministro(0));
        pilon2.asignarSuministro(new Suministro(0));

        elMapa.colocarOcupable(pilon1, coordenadaPilon1);
        elMapa.colocarOcupable(pilon2, coordenadaPilon2);

        for (int i = 0; i < 5; i++) {
            pilon1.pasarTurno();
            pilon2.pasarTurno();
        }

        elMapa.colocarOcupable(unAcceso, coordenadaAcceso);

        for (int i = 0; i < 8; i++)
            unAcceso.pasarTurno();

        pilon1.recibirAtaque(unAtaque);
        pilon2.recibirAtaque(unAtaque);

        unAcceso.pasarTurno();
        pilon1.pasarTurno();
        pilon2.pasarTurno();

        assertThrows(ErrorElEdificioNoTieneCarga.class, () -> unAcceso.crearUnidad(new FabricaDragon()));
    }
}
