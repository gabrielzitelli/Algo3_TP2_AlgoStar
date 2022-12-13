package edu.fiuba.algo3.testDeClases.edificiosTests;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.PuertoEstelar;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasUnidades;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasUnidadesScout;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PuertoEstelarTest {

    final FabricasDisponibles fabricasDisponibles = new FabricasDisponibles();

    @BeforeEach
    public void setupFabricasDisponibles() {
        ArrayList<FabricasUnidades> fabricasHabilitada = new ArrayList<>();
        fabricasHabilitada.add(new FabricasUnidadesScout());
        fabricasDisponibles.aumentar(fabricasHabilitada);
    }

    @Test
    public void test01UnPuertoEstelarNoSeConstruyeEn9Turnos(){
        PuertoEstelar puertoEstelar = new PuertoEstelar();
        puertoEstelar.asignarListaDeUnidades(fabricasDisponibles);

        for(int i = 0; i < 9; i++)
            puertoEstelar.pasarTurno();

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> puertoEstelar.crearUnidad(new FabricasUnidadesScout()));
    }

    @Test
    public void test02UnPuertoEstelarSePuedeConstruirEn10Turnos(){
        PuertoEstelar puertoEstelar = new PuertoEstelar();
        puertoEstelar.asignarListaDeUnidades(fabricasDisponibles);
        puertoEstelar.asignarRecursos(new Mineral(1000), new Gas(1000));
        puertoEstelar.asignarSuministro(new Suministro(4));

        //Construyo Puerto Estelar
        for(int i = 0; i < 10; i++)
            puertoEstelar.pasarTurno();

        assertDoesNotThrow(() -> puertoEstelar.crearUnidad(new FabricasUnidadesScout()));
    }
}
