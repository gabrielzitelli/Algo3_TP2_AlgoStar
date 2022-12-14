package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Criadero;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasUnidades;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasUnidadesZangano;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CasoDeUso1Test {

    final FabricasDisponibles fabricasDisponibles = new FabricasDisponibles();

    @BeforeEach
    public void setupFabricasDisponibles() {
        ArrayList<FabricasUnidades> fabricasHabilitada = new ArrayList<>();
        fabricasHabilitada.add(new FabricasUnidadesZangano());
        fabricasDisponibles.aumentar(fabricasHabilitada);
    }

    @Test
    public void test01ConsumoUnaLarvaYLuegoVuelvoATenerLasTres(){
        Criadero unCriadero = new Criadero();
        unCriadero.asignarListaDeUnidades(fabricasDisponibles);
        unCriadero.asignarRecursos(new Mineral(150), new Gas(0));
        unCriadero.asignarSuministro(new Suministro(5));

        //Construyo el edificio
        for(int i = 0; i < 5; i++)
            unCriadero.pasarTurno();

        // Consumo una larva
        unCriadero.crearUnidad(new FabricasUnidadesZangano());

        // Paso un turno para tener 3 larvas de nuevo
        unCriadero.pasarTurno();

        // Consumo dos larvas
        unCriadero.crearUnidad(new FabricasUnidadesZangano());
        unCriadero.crearUnidad(new FabricasUnidadesZangano());

        // Al consumir la tercera larva no me tira error
        assertDoesNotThrow( () -> unCriadero.crearUnidad(new FabricasUnidadesZangano()));
    }

    @Test
    public void test02ConsumoDosLarvasYLuegoVuelvoATenerLasTres(){
        Criadero unCriadero = new Criadero();
        unCriadero.asignarListaDeUnidades(fabricasDisponibles);
        unCriadero.asignarRecursos(new Mineral(150), new Gas(0));
        unCriadero.asignarSuministro(new Suministro(5));

        //Construyo el edificio
        for(int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        // Consumo dos larvas
        unCriadero.crearUnidad(new FabricasUnidadesZangano());
        unCriadero.crearUnidad(new FabricasUnidadesZangano());

        // Paso dos turnos para tener 3 larvas de nuevo
        unCriadero.pasarTurno();
        unCriadero.pasarTurno();

        // Consumo dos larvas
        unCriadero.crearUnidad(new FabricasUnidadesZangano());
        unCriadero.crearUnidad(new FabricasUnidadesZangano());

        // Al consumir la tercera larva no me tira error
        assertDoesNotThrow( () -> unCriadero.crearUnidad(new FabricasUnidadesZangano()));

    }

    @Test
    public void test03ConsumoTresLarvasYLuegoVuelvoATenerLasTres(){
        Criadero unCriadero = new Criadero();
        unCriadero.asignarListaDeUnidades(fabricasDisponibles);
        unCriadero.asignarRecursos(new Mineral(150), new Gas(0));
        unCriadero.asignarSuministro(new Suministro(6));

        //Construyo el edificio
        for(int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        // Consumo tres larvas
        unCriadero.crearUnidad(new FabricasUnidadesZangano());
        unCriadero.crearUnidad(new FabricasUnidadesZangano());
        unCriadero.crearUnidad(new FabricasUnidadesZangano());

        // Paso tres turnos para tener 3 larvas de nuevo
        unCriadero.pasarTurno();
        unCriadero.pasarTurno();
        unCriadero.pasarTurno();

        // Consumo dos larvas
        unCriadero.crearUnidad(new FabricasUnidadesZangano());
        unCriadero.crearUnidad(new FabricasUnidadesZangano());

        // Al consumir la tercera larva no me tira error
        assertDoesNotThrow( () -> unCriadero.crearUnidad(new FabricasUnidadesZangano()));

    }
}
