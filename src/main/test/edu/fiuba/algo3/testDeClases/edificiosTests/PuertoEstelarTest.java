package edu.fiuba.algo3.testDeClases.edificiosTests;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.FabricaDragon;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.FabricaScout;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.PuertoEstelar;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PuertoEstelarTest {

    @Test
    public void test01UnPuertoEstelarNoSeConstruyeEn9Turnos(){
        PuertoEstelar puertoEstelar = new PuertoEstelar();

        ArrayList<Fabrica> unidadesDisponibles = new ArrayList<>();
        unidadesDisponibles.add(new FabricaScout());
        puertoEstelar.asignarListaDeUnidades(unidadesDisponibles);

        for(int i = 0; i < 9; i++)
            puertoEstelar.pasarTurno();

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> puertoEstelar.crearUnidad(new FabricaScout()));
    }

    @Test
    public void test02UnPuertoEstelarSePuedeConstruirEn10Turnos(){
        PuertoEstelar puertoEstelar = new PuertoEstelar();

        ArrayList<Fabrica> unidadesDisponibles = new ArrayList<>();
        unidadesDisponibles.add(new FabricaScout());
        puertoEstelar.asignarListaDeUnidades(unidadesDisponibles);

        //Construyo Puerto Estelar
        for(int i = 0; i < 10; i++)
            puertoEstelar.pasarTurno();

        assertDoesNotThrow(() -> puertoEstelar.crearUnidad(new FabricaScout()));
    }
}
