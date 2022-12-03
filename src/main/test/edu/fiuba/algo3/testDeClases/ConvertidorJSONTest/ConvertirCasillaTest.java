package edu.fiuba.algo3.testDeClases.ConvertidorJSONTest;

import edu.fiuba.algo3.modelo.ConvertidorJson.ConvertidorJSON;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Criadero;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;

import edu.fiuba.algo3.modelo.Mapa.Casilla.GasRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;

import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Zealot;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConvertirCasillaTest {
    @Test
    public void test01conviertoUnaCasillaVaciaAJSON() {
        Mapa mapa = Mapa.obtener();
        mapa.reiniciarMapa();
        Coordenada coordenada = new Coordenada(0,0);

        Casilla casilla = mapa.obtenerCasilla(coordenada);

        JSONObject obj = ConvertidorJSON.convertirAJSON(casilla);

        assertEquals("ninguno", obj.get("ocupable"));
    }
    @Test
    public void test02conviertoUnaCasillaOcupadaConUnEdificioAJSON() {
        Mapa mapa = Mapa.obtener();
        mapa.reiniciarMapa();
        Coordenada coordenada = new Coordenada(0,0);

        mapa.construirEdificio(new Criadero(), coordenada);
        Casilla casilla = mapa.obtenerCasilla(coordenada);

        JSONObject obj = ConvertidorJSON.convertirAJSON(casilla);

        assertEquals("criadero", obj.get("ocupable"));
        assertEquals("en_construccion", obj.get("estado"));
    }
    @Test
    public void test03conviertoUnaCasillaOcupadaConUnaUnidadAJSON() {
        Mapa mapa = Mapa.obtener();
        mapa.reiniciarMapa();
        Coordenada coordenada = new Coordenada(0,0);

        mapa.colocarUnaUnidad(new Zealot(), coordenada);
        mapa.colocarMaterial(new GasRecolectable(), coordenada);
        Casilla casilla = mapa.obtenerCasilla(coordenada);

        JSONObject obj = ConvertidorJSON.convertirAJSON(casilla);

        assertEquals("zealot", obj.get("ocupable"));
        assertEquals("gas", obj.get("recurso"));
    }
}
