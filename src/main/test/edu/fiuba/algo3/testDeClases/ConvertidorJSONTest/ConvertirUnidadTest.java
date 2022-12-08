package edu.fiuba.algo3.testDeClases.ConvertidorJSONTest;

import edu.fiuba.algo3.modelo.ConvertidorJson.ConvertidorJSON;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zerling;
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
        assertEquals("0", obj.get(ConvertidorJSON.RANGO_ATAQUE));
    }

    @Test
    public void test02ConviertoUnZerlingAJSON() {
        Unidad zangano = new Zerling();

        JSONObject obj = ConvertidorJSON.convertirAJSON(zangano);

        assertEquals("zerling", obj.get(ConvertidorJSON.OCUPABLE));
        assertEquals("35", obj.get(ConvertidorJSON.VIDAMAX));
        assertEquals("1", obj.get(ConvertidorJSON.RANGO_ATAQUE));
    }
}
