package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.controladores.MapaControlador;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public abstract class UnidadZergVista extends UnidadVista{

    public void manejarBotones(Button[] arrayBotones, Pane[] arrayWrappersBotonesEdificio, Coordenada coordenada, String imperioDeJugadorActual, MapaControlador mapaControlador){
        if (!imperioDeJugadorActual.equalsIgnoreCase("zerg"))
            return;

        super.manejarBotones(arrayBotones, arrayWrappersBotonesEdificio, coordenada, imperioDeJugadorActual, mapaControlador);
    }
}
