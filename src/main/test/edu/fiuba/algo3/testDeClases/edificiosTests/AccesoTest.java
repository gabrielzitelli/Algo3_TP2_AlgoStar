package edu.fiuba.algo3.testDeClases.edificiosTests;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Acceso;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.FabricaDragon;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.FabricaZealot;
import edu.fiuba.algo3.modelo.Edificios.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccesoTest {

    @Test
    public void test01PuedoCrearUnAcceso(){
        Acceso unAcceso = new Acceso();

        assertNotNull(unAcceso);
    }

    @Test
    public void test02UnAccesoNoSeConstruyeEn7TurnosIntentandoCrearFabricaDragon(){
        FabricasDisponibles fabricasDisponibles = new FabricasDisponibles();
        Acceso unAcceso = new Acceso();
        unAcceso.asignarListaDeUnidades(fabricasDisponibles);

        for (int i = 0; i < 7; i++)
            unAcceso.pasarTurno();

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unAcceso.crearUnidad(new FabricaDragon()));
    }

    @Test
    public void test03UnAccesoNoSeConstruyeEn7TurnosIntentandoCrearFabricaZealot(){
        FabricasDisponibles fabricasDisponibles = new FabricasDisponibles();
        Acceso unAcceso = new Acceso();
        unAcceso.asignarListaDeUnidades(fabricasDisponibles);

        for (int i = 0; i < 7; i++)
            unAcceso.pasarTurno();

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unAcceso.crearUnidad(new FabricaZealot()));
    }

    @Test
    public void test04UnAccesoSeConstruyeEn8TurnosIntentandoCrearFabricaDragon(){
        FabricasDisponibles fabricasDisponibles = new FabricasDisponibles();
        Acceso unAcceso = new Acceso();
        unAcceso.asignarListaDeUnidades(fabricasDisponibles);
        unAcceso.asignarRecursos(new Mineral(1000), new Gas(1000));

        for (int i = 0; i < 8; i++)
            unAcceso.pasarTurno();

        assertDoesNotThrow(() -> unAcceso.crearUnidad(new FabricaDragon()));
    }

    @Test
    public void test05UnAccesoSeConstruyeEn8TurnosIntentandoCrearFabricaZealot(){
        FabricasDisponibles fabricasDisponibles = new FabricasDisponibles();
        Acceso unAcceso = new Acceso();
        unAcceso.asignarListaDeUnidades(fabricasDisponibles);
        unAcceso.asignarRecursos(new Mineral(1000), new Gas(1000));

        for (int i = 0; i < 8; i++)
            unAcceso.pasarTurno();

        assertDoesNotThrow(() -> unAcceso.crearUnidad(new FabricaZealot()));
    }
}
