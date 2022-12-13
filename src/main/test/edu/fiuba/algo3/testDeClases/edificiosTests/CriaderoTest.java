package edu.fiuba.algo3.testDeClases.edificiosTests;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Criadero;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasUnidades;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasUnidadesZangano;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Excepciones.ErrorCriaderoNoTieneMasLarvas;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CriaderoTest {

    final FabricasDisponibles fabricasDisponibles = new FabricasDisponibles();

    @BeforeEach
    public void setup() {
        ArrayList<FabricasUnidades> fabricasHabilitada = new ArrayList<>();
        fabricasHabilitada.add(new FabricasUnidadesZangano());
        fabricasDisponibles.aumentar(fabricasHabilitada);
    }

    @Test
    public void test01PuedoCrearUnCriadero(){
        Criadero unCriadero = new Criadero();

        assertNotNull(unCriadero);
    }

    @Test
    public void test02UnCriaderoNoSeConstruyeEn3Turnos(){
        Criadero unCriadero = new Criadero();
        unCriadero.asignarListaDeUnidades(fabricasDisponibles);

        for (int i = 0; i < 3; i++)
            unCriadero.pasarTurno();

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unCriadero.crearUnidad(new FabricasUnidadesZangano()));
    }

    @Test
    public void test03UnCriaderoSeConstruyeEn4Turnos(){
        Criadero unCriadero = new Criadero();
        unCriadero.asignarListaDeUnidades(fabricasDisponibles);
        unCriadero.asignarRecursos(new Mineral(1000), new Gas(100));
        unCriadero.asignarSuministro(new Suministro(1));

        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        assertDoesNotThrow(() -> unCriadero.crearUnidad(new FabricasUnidadesZangano()));
    }

    @Test
    public void test04UnCriaderoNoPuedeCrear4Unidades(){
        //No se pueden crear 4 unidades porque se consumirian 4 larvas, y el criadero empieza solo con 3
        Criadero unCriadero = new Criadero();
        unCriadero.asignarListaDeUnidades(fabricasDisponibles);
        unCriadero.asignarRecursos(new Mineral(1000), new Gas(100));
        unCriadero.asignarSuministro(new Suministro(4));

        //Construyo el edificio
        for(int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        //Se consumen las 3 larvas creando unidades
        for(int i = 0; i < 3; i++)
            unCriadero.crearUnidad(new FabricasUnidadesZangano());

        assertThrows(ErrorCriaderoNoTieneMasLarvas.class, () -> unCriadero.crearUnidad(new FabricasUnidadesZangano()));
    }

    @Test
    public void test05UnCriaderoPuedeCrear3Unidades(){
        //No se pueden crear 4 unidades porque se consumirian 4 larvas, y el criadero empieza solo con 3
        Criadero unCriadero = new Criadero();
        unCriadero.asignarListaDeUnidades(fabricasDisponibles);
        unCriadero.asignarRecursos(new Mineral(1000), new Gas(100));
        unCriadero.asignarSuministro(new Suministro(3));

        //Construyo el edificio
        for(int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        unCriadero.crearUnidad(new FabricasUnidadesZangano());
        unCriadero.crearUnidad(new FabricasUnidadesZangano());

        assertDoesNotThrow( () -> unCriadero.crearUnidad(new FabricasUnidadesZangano()));
    }

    @Test
    public void test06UnCriaderoPuedeCrearUnaUnidadDespuesDeGastar3LarvasYQuePaseUnTurno() {
        //No se pueden crear 4 unidades porque se consumirian 4 larvas, y el criadero empieza solo con 3
        Criadero unCriadero = new Criadero();
        unCriadero.asignarListaDeUnidades(fabricasDisponibles);
        unCriadero.asignarRecursos(new Mineral(1000), new Gas(100));
        unCriadero.asignarSuministro(new Suministro(4));

        //Construyo el edificio
        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        //Se consumen las 3 larvas creando unidades
        for(int i = 0; i < 3; i++)
            unCriadero.crearUnidad(new FabricasUnidadesZangano());

        //Regenero una larva
        unCriadero.pasarTurno();

        assertDoesNotThrow( () -> unCriadero.crearUnidad(new FabricasUnidadesZangano()));
    }
}