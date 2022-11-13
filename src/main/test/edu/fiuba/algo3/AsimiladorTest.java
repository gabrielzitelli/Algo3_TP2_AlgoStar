package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.EdificioProtoss.Asimilador;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.GasBruto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AsimiladorTest {
    @Test
    public void test01PuedoCrearUnExtractor(){
        Recurso gasDelImperio = new Recurso(0);
        GasBruto volcanDeGas = new GasBruto();

        Asimilador unAsimilador = new Asimilador(gasDelImperio, volcanDeGas);

        assertNotNull(unAsimilador);
    }

    @Test
    public void test02NoPuedoExtraerElGasDeUnAsimiladorQueNoEstaConstruidoEn5Turnos(){
        Recurso gasDelImperio = new Recurso(0);
        GasBruto volcanDeGas = new GasBruto();

        Asimilador unAsimilador = new Asimilador(gasDelImperio, volcanDeGas);

        for(int i = 0; i < 5; i++)
            unAsimilador.pasarTurno();

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unAsimilador.extraer());
    }

    @Test
    public void test03PuedoExtraerElGasDeUnAsimiladorQueEstaConstruidoEn6Turnos(){
        Recurso gasDelImperio = new Recurso(0);
        GasBruto volcanDeGas = new GasBruto();

        Asimilador unAsimilador = new Asimilador(gasDelImperio, volcanDeGas);

        for(int i = 0; i < 6; i++)
            unAsimilador.pasarTurno();

        assertDoesNotThrow(() -> unAsimilador.extraer());
    }
}
