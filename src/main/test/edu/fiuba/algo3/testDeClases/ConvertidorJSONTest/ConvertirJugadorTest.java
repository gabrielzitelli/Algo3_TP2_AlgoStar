package edu.fiuba.algo3.testDeClases.ConvertidorJSONTest;

import edu.fiuba.algo3.modelo.AlgoStar.Jugador;
import edu.fiuba.algo3.modelo.ConvertidorJson.ConvertidorJSON;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConvertirJugadorTest {
    @Test
    public void test01ConviertoUnJugadorAJson() {
        Jugador jugador = new Jugador("Miguelangel", "Rojo", new Zerg());
        ConvertidorJSON convertidor = new ConvertidorJSON();
        JSONObject obj = ConvertidorJSON.convertirAJSON(jugador);

        assertEquals("Miguelangel", obj.get(ConvertidorJSON.NOMBRE));
        assertEquals("Rojo", obj.get(ConvertidorJSON.COLOR));
        assertEquals("zerg", obj.get(ConvertidorJSON.IMPERIO));
        assertEquals("200", obj.get(ConvertidorJSON.MINERAL));
        assertEquals("0", obj.get(ConvertidorJSON.GAS));
        assertEquals("0", obj.get(ConvertidorJSON.POBLACION));
        assertEquals("0", obj.get(ConvertidorJSON.SUMINISTRO));
    }
}
