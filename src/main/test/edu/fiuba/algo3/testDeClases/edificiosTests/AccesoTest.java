package edu.fiuba.algo3.testDeClases.edificiosTests;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Acceso;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.FabricaDragon;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.FabricaZealot;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;
import edu.fiuba.algo3.modelo.Edificios.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AccesoTest {

    FabricasDisponibles fabricasDisponibles = new FabricasDisponibles();

    @BeforeEach
    public void setupFabricasDisponibles() {
        ArrayList<Fabrica> fabricasHabilitada = new ArrayList<Fabrica>();
        fabricasHabilitada.add(new FabricaDragon());
        fabricasHabilitada.add(new FabricaZealot());
        fabricasDisponibles.aumentar(fabricasHabilitada);
    }


    @Test
    public void test01PuedoCrearUnAcceso(){
        Acceso unAcceso = new Acceso();

        assertNotNull(unAcceso);
    }

    @Test
    public void test02UnAccesoNoSeConstruyeEn7TurnosIntentandoCrearFabricaDragon(){
        Acceso unAcceso = new Acceso();
        unAcceso.asignarListaDeUnidades(fabricasDisponibles);

        for (int i = 0; i < 7; i++)
            unAcceso.pasarTurno();

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unAcceso.crearUnidad(new FabricaDragon()));
    }

    @Test
    public void test03UnAccesoNoSeConstruyeEn7TurnosIntentandoCrearFabricaZealot(){
        Acceso unAcceso = new Acceso();
        unAcceso.asignarListaDeUnidades(fabricasDisponibles);

        for (int i = 0; i < 7; i++)
            unAcceso.pasarTurno();

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unAcceso.crearUnidad(new FabricaZealot()));
    }

    @Test
    public void test04UnAccesoSeConstruyeEn8TurnosIntentandoCrearFabricaDragon(){
        Acceso unAcceso = new Acceso();
        unAcceso.asignarListaDeUnidades(fabricasDisponibles);

        for (int i = 0; i < 8; i++)
            unAcceso.pasarTurno();

        assertDoesNotThrow(() -> unAcceso.crearUnidad(new FabricaDragon()));
    }

    @Test
    public void test05UnAccesoSeConstruyeEn8TurnosIntentandoCrearFabricaZealot(){
        Acceso unAcceso = new Acceso();
        unAcceso.asignarListaDeUnidades(fabricasDisponibles);

        for (int i = 0; i < 8; i++)
            unAcceso.pasarTurno();

        assertDoesNotThrow(() -> unAcceso.crearUnidad(new FabricaZealot()));
    }
}
