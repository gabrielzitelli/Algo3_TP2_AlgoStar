package edu.fiuba.algo3.testDeClases.edificiosTests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Criadero;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.FabricaZangano;
import edu.fiuba.algo3.modelo.Edificios.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Excepciones.ErrorCriaderoNoTieneMasLarvas;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CriaderoTest {

    FabricasDisponibles fabricasDisponibles = new FabricasDisponibles();

    @BeforeEach
    public void setup() {
        ArrayList<Fabrica> fabricasHabilitada = new ArrayList<Fabrica>();
        fabricasHabilitada.add(new FabricaZangano());
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

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unCriadero.crearUnidad(new FabricaZangano()));
    }

    @Test
    public void test03UnCriaderoSeConstruyeEn4Turnos(){
        Criadero unCriadero = new Criadero();
        unCriadero.asignarListaDeUnidades(fabricasDisponibles);
        unCriadero.asignarRecursos(new Mineral(1000), new Gas(100));

        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        assertDoesNotThrow(() -> unCriadero.crearUnidad(new FabricaZangano()));
    }

    @Test
    public void test04UnCriaderoNoPuedeCrear4Unidades(){
        //No se pueden crear 4 unidades porque se consumirian 4 larvas, y el criadero empieza solo con 3
        Criadero unCriadero = new Criadero();
        unCriadero.asignarListaDeUnidades(fabricasDisponibles);
        unCriadero.asignarRecursos(new Mineral(1000), new Gas(100));

        //Construyo el edificio
        for(int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        //Se consumen las 3 larvas creando unidades
        for(int i = 0; i < 3; i++)
            unCriadero.crearUnidad(new FabricaZangano());

        assertThrows(ErrorCriaderoNoTieneMasLarvas.class, () -> unCriadero.crearUnidad(new FabricaZangano()));
    }

    @Test
    public void test05UnCriaderoPuedeCrear3Unidades(){
        //No se pueden crear 4 unidades porque se consumirian 4 larvas, y el criadero empieza solo con 3
        Criadero unCriadero = new Criadero();
        unCriadero.asignarListaDeUnidades(fabricasDisponibles);
        unCriadero.asignarRecursos(new Mineral(1000), new Gas(100));

        //Construyo el edificio
        for(int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        unCriadero.crearUnidad(new FabricaZangano());
        unCriadero.crearUnidad(new FabricaZangano());

        assertDoesNotThrow( () -> unCriadero.crearUnidad(new FabricaZangano()));
    }

    @Test
    public void test06UnCriaderoPuedeCrearUnaUnidadDespuesDeGastar3LarvasYQuePaseUnTurno() {
        //No se pueden crear 4 unidades porque se consumirian 4 larvas, y el criadero empieza solo con 3
        Criadero unCriadero = new Criadero();
        unCriadero.asignarListaDeUnidades(fabricasDisponibles);
        unCriadero.asignarRecursos(new Mineral(1000), new Gas(100));

        //Construyo el edificio
        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        //Se consumen las 3 larvas creando unidades
        for(int i = 0; i < 3; i++)
            unCriadero.crearUnidad(new FabricaZangano());

        //Regenero una larva
        unCriadero.pasarTurno();

        assertDoesNotThrow( () -> unCriadero.crearUnidad(new FabricaZangano()));
    }
}
