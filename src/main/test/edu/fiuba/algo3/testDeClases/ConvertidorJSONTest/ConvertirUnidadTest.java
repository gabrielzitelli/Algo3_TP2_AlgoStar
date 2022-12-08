package edu.fiuba.algo3.testDeClases.ConvertidorJSONTest;

import edu.fiuba.algo3.modelo.ConvertidorJson.ConvertidorJSON;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConvertirUnidadTest {
    @Test
    public void test01ConviertoUnZanganoAJSON() {
        Unidad zangano = new Zangano();

        JSONObject obj = ConvertidorJSON.convertirAJSON(zangano);

        assertEquals("zangano", obj.get(ConvertidorJSON.OCUPABLE));
        assertEquals("25", obj.get(ConvertidorJSON.VIDAMAX));
    }
}
