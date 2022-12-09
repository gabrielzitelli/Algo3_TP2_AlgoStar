package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.MapaControlador;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class ZanganoVista extends UnidadZergVista {
    public ZanganoVista() {
        this.tile = new Tile("unidades_zerg/32px/zangano.png");
        this.identificador = "zangano";
        this.info = "Zangano";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unidades_zerg/original/zanganoRaw.png")));
        this.imagenParaBoton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unidades_zerg/original/creacion/zanganoRawCrear.png")));
    }

    @Override
    public void manejarBotones(Button[] arrayBotones, Pane[] arrayWrappersBotonesEdificio, Coordenada coordenada, String imperioDeJugadorActual, MapaControlador mapaControlador) {
        super.manejarBotones(arrayBotones, arrayWrappersBotonesEdificio, coordenada, imperioDeJugadorActual, mapaControlador);

        Button botonAtacar = arrayBotones[1];
        botonAtacar.setDisable(true);
        botonAtacar.setVisible(false);
        botonAtacar.setTooltip(new Tooltip(""));
    }
}
