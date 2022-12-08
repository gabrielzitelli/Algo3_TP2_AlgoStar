package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.controladores.MapaControlador;
import edu.fiuba.algo3.modelo.ConvertidorJson.ConvertidorJSON;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Extractor;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.json.simple.JSONObject;

import java.util.Objects;

public class ExtractorVista extends OcupableVista {
    public ExtractorVista() {
        this.tile = new Tile("edificios_zerg/32px/extractor.png");
        this.identificador = "extractor";
        this.info = "Extractor";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_zerg/original/extractorRaw.png")));
        this.imagenParaBoton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_zerg/original/construccion/extractorRawConstruir.png")));
    }

    public void manejarBotones(Button[] arrayBotones, Pane[] arrayWrappersBotonesEdificio, Coordenada coordenada, String imperioDeJugadorActual, MapaControlador mapaControlador){
        if(!imperioDeJugadorActual.equalsIgnoreCase("zerg"))
            return;

        Extractor esteExtractor = (Extractor) Mapa.obtener().obtenerOcupable(coordenada);
        JSONObject extractorJSON = ConvertidorJSON.convertirAJSON(esteExtractor);

        Button botonSacarZangano = arrayBotones[0];

        double anchoBoton = botonSacarZangano.getPrefWidth();
        double altoBoton = botonSacarZangano.getPrefWidth();

        Image imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/especial/mover.png")));

        crearBoton(botonSacarZangano, imagen, anchoBoton, altoBoton);

        botonSacarZangano.setOnAction( event -> {
            esteExtractor.descontratarZangano();
        });

        botonSacarZangano.setTooltip(new Tooltip("SACAR ZANGANO"));

        //Verificar que está construido
        if (extractorJSON.get(ConvertidorJSON.ESTADO).equals("en_construccion")) {
            botonSacarZangano.setDisable(true);
            botonSacarZangano.setVisible(false);
            botonSacarZangano.setTooltip(new Tooltip(""));
        }

        //verificar que tiene Zanganos
        int cantidadDeZanganos = Integer.parseInt(((String)extractorJSON.get(ConvertidorJSON.CANTIDAD_UNIDADES)));
        if (cantidadDeZanganos == 0) {
            botonSacarZangano.setDisable(true);
            botonSacarZangano.setTooltip(new Tooltip("No hay Zanganos"));
        }

    }

    protected void crearBoton(Button boton, Image imagenAColocar, double ancho, double alto) {
        ImageView imagen = new ImageView();
        imagen.setPreserveRatio(false);
        imagen.setSmooth(true);

        imagen.setImage(imagenAColocar);

        imagen.setFitWidth(ancho);
        imagen.setFitHeight(alto);

        imagen.rotateProperty().set(90.0);

        DropShadow efectorBordePNGBlanco = new DropShadow( 1, Color.WHITE );
        imagen.setEffect(efectorBordePNGBlanco);

        boton.setVisible(true);
        boton.setDisable(false);
        boton.setGraphic(imagen);
    }
}
