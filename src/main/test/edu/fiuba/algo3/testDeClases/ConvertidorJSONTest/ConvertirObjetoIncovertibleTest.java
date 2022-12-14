package edu.fiuba.algo3.testDeClases.ConvertidorJSONTest;

import edu.fiuba.algo3.modelo.ConvertidorJson.ConvertidorJSON;
import edu.fiuba.algo3.modelo.Excepciones.ErrorObjetoNoSePuedeConvertirAJSON;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConvertirObjetoIncovertibleTest {
    @Test
    public void test01IntentoCovertirAJSONUnObjetoQueNoPuedeSerConvertido() {

        assertThrows( ErrorObjetoNoSePuedeConvertirAJSON.class, () ->
                ConvertidorJSON.convertirAJSON(new Mineral(0)));
    }
}
