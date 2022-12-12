package edu.fiuba.algo3.modelo.Unidades.EstadoUnidad;

import edu.fiuba.algo3.modelo.AlgoStar.Logger;
import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Ataque.TipoDanio;
import edu.fiuba.algo3.modelo.ConvertidorJson.ConvertidorJSON;
import edu.fiuba.algo3.modelo.Excepciones.ErrorLaUnidadNoPuedeAtacarFueraDeSuRango;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Ocupable;
import org.json.simple.JSONObject;

public class Atacante implements Atacar{

    private final int rangoDeAtaque;

    public Atacante(int rangoDeAtaque) {
        this.rangoDeAtaque = rangoDeAtaque;
    }

    @Override
    public Atacar atacar(Coordenada coordenada, Casilla casillaAAtacar, TipoDanio unDanio) {
        if (!Mapa.obtener().estaDentroDeRango(coordenada, casillaAAtacar, rangoDeAtaque))
            throw new ErrorLaUnidadNoPuedeAtacarFueraDeSuRango();

        casillaAAtacar.recibirAtaque(new Ataque(unDanio));
        loggearMensaje(coordenada, casillaAAtacar);

        return new NoAtacante(rangoDeAtaque);
    }

    private static void loggearMensaje(Coordenada coordenada, Casilla casillaAAtacar) {
        JSONObject unidadJSON = ConvertidorJSON.convertirAJSON(Mapa.obtener().obtenerOcupable(coordenada));
        JSONObject ocupableAtacado = ConvertidorJSON.convertirAJSON(casillaAAtacar.obtenerOcupable());
        String nombreUnidad = (String) unidadJSON.get(ConvertidorJSON.OCUPABLE);
        String nombreOcupable = (String) ocupableAtacado.get(ConvertidorJSON.OCUPABLE);
        Logger.obtener().log("La unidad '" + nombreUnidad +
                "' [X: " + coordenada.getCoordenadaX() + ", Y: " + coordenada.getCoordenadaY() + "] " +
                "atacó al Ocupable '" + nombreOcupable + "' [X: " + casillaAAtacar.obtenerCoordenada().getCoordenadaX() +
                ", Y: " + casillaAAtacar.obtenerCoordenada().getCoordenadaY() + "].");
    }

    @Override
    public Atacar pasarTurno() {
        return this;
    }

    public String toString() {
        return "atacar";
    }
}
