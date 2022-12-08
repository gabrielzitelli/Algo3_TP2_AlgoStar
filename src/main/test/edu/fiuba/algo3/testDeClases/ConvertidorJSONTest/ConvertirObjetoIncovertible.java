package edu.fiuba.algo3.testDeClases.ConvertidorJSONTest;

import edu.fiuba.algo3.modelo.ConvertidorJson.ConvertidorJSON;
import edu.fiuba.algo3.modelo.Excepciones.ErrorObjetoNoSePuedeConvertirAJSON;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConvertirObjetoIncovertible {
    @Test
    public void test01IntentoCovertirAJSONUnObjetoQueNoPuedeSerConvertido() {
        ConvertidorJSON convertidorJSON = new ConvertidorJSON();

        assertThrows( ErrorObjetoNoSePuedeConvertirAJSON.class, () ->
                convertidorJSON.convertirAJSON(new Mineral(0)));
    }
}
