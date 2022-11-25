package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.*;
import edu.fiuba.algo3.modelo.Edificios.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CasoDeUso1Test {

    FabricasDisponibles fabricasDisponibles = new FabricasDisponibles();

    @BeforeEach
    public void setupFabricasDisponibles() {
        ArrayList<Fabrica> fabricasHabilitada = new ArrayList<Fabrica>();
        fabricasHabilitada.add(new FabricaZangano());
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
        unCriadero.crearUnidad(new FabricaZangano());

        // Paso un turno para tener 3 larvas de nuevo
        unCriadero.pasarTurno();

        // Consumo dos larvas
        unCriadero.crearUnidad(new FabricaZangano());
        unCriadero.crearUnidad(new FabricaZangano());

        // Al consumir la tercera larva no me tira error
        assertDoesNotThrow( () -> unCriadero.crearUnidad(new FabricaZangano()));
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
        unCriadero.crearUnidad(new FabricaZangano());
        unCriadero.crearUnidad(new FabricaZangano());

        // Paso dos turnos para tener 3 larvas de nuevo
        unCriadero.pasarTurno();
        unCriadero.pasarTurno();

        // Consumo dos larvas
        unCriadero.crearUnidad(new FabricaZangano());
        unCriadero.crearUnidad(new FabricaZangano());

        // Al consumir la tercera larva no me tira error
        assertDoesNotThrow( () -> unCriadero.crearUnidad(new FabricaZangano()));

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
        unCriadero.crearUnidad(new FabricaZangano());
        unCriadero.crearUnidad(new FabricaZangano());
        unCriadero.crearUnidad(new FabricaZangano());

        // Paso tres turnos para tener 3 larvas de nuevo
        unCriadero.pasarTurno();
        unCriadero.pasarTurno();
        unCriadero.pasarTurno();

        // Consumo dos larvas
        unCriadero.crearUnidad(new FabricaZangano());
        unCriadero.crearUnidad(new FabricaZangano());

        // Al consumir la tercera larva no me tira error
        assertDoesNotThrow( () -> unCriadero.crearUnidad(new FabricaZangano()));

    }
}
