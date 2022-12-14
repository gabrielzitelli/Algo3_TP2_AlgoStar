package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.controladores.MapaControlador;
import edu.fiuba.algo3.modelo.ConvertidorJson.ConvertidorJSON;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.json.simple.JSONObject;

public abstract class UnidadProtossVista extends UnidadVista{

    public void manejarBotones(Button[] arrayBotones, Pane[] arrayWrappersBotonesEdificio, Coordenada coordenada, String imperioDeJugadorActual, MapaControlador mapaControlador){
        if (!imperioDeJugadorActual.equalsIgnoreCase("protoss"))
            return;

        super.manejarBotones(arrayBotones, arrayWrappersBotonesEdificio, coordenada, imperioDeJugadorActual, mapaControlador);
    }

    @Override
    public void aplicarTextoEscudo(Text textoEscudo, JSONObject ocupableJson){
        textoEscudo.setText(ocupableJson.get(ConvertidorJSON.ESCUDO) + "/" + ocupableJson.get(ConvertidorJSON.ESCUDOMAX));
    }
}
