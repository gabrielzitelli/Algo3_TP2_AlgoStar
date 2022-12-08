package edu.fiuba.algo3.testDeClases.ConvertidorJSONTest;

import edu.fiuba.algo3.modelo.ConvertidorJson.ConvertidorJSON;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Extractor;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConvertirEdificioTest {

    @Test
    public void test01ConviertoUnExtractorAJSON() {
        Extractor extractor = new Extractor(new Gas(0));

        for (int i = 0; i < 10; i++)
            extractor.pasarTurno();

        extractor.contratarZangano(new Zangano());
        extractor.contratarZangano(new Zangano());

        JSONObject obj = ConvertidorJSON.convertirAJSON(extractor);

        assertEquals("100", obj.get("costoMineral"));
        assertEquals("extractor", obj.get(ConvertidorJSON.OCUPABLE));
        assertEquals("2", obj.get(ConvertidorJSON.CANTIDAD_UNIDADES));
    }
}
