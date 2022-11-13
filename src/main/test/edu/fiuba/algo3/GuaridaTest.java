package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.EdificioZerg.Guarida;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GuaridaTest {

    @Test
    public void test01UnaGuaridaNoSeConstruyeEn11Turnos(){
        Guarida unaGuarida = new Guarida();

        for(int i = 0; i < 11; i++)
            unaGuarida.pasarTurno();


        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unaGuarida.crearFabricaHidralisco());
    }

    @Test
    public void test02GuaridaSePuedeConstruirEn12Turnos(){
        Guarida unaGuarida = new Guarida();

        for(int i = 0; i < 12; i++)
            unaGuarida.pasarTurno();

        assertDoesNotThrow(() -> unaGuarida.crearFabricaHidralisco());
    }

}
