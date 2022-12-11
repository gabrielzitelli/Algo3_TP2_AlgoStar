package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.controladores.MapaControlador;
import edu.fiuba.algo3.modelo.ConvertidorJson.ConvertidorJSON;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.json.simple.JSONObject;

import java.util.Objects;

public abstract class UnidadVista extends OcupableVista {

    protected String requisitosUnidad = "";
    protected Image imagenDeMover = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/especial/mover.png")));
    protected Image imagenDeAtacar = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/especial/atacar.png")));

    public String getRequisitosUnidad(){
        return requisitosUnidad;
    }

    protected void crearBoton(Button boton, Image imagenAColocar, double ancho, double alto){
        ImageView imagen = new ImageView();
        imagen.setPreserveRatio(false);
        imagen.setSmooth(true);

        imagen.setImage(imagenAColocar);

        imagen.setFitWidth(ancho);
        imagen.setFitHeight(alto);

        DropShadow efectorBordePNGBlanco = new DropShadow( 1, Color.WHITE );
        imagen.setEffect(efectorBordePNGBlanco);

        boton.setVisible(true);
        boton.setDisable(false);
        boton.setGraphic(imagen);
    }

    public void manejarBotones(Button[] arrayBotones, Pane[] arrayWrappersBotonesEdificio, Coordenada coordenada, String imperioDeJugadorActual, MapaControlador mapaControlador){
        Button botonMover = arrayBotones[0];
        Button botonAtacar = arrayBotones[1];
        Pane wrapperBotonMover = arrayWrappersBotonesEdificio[0];
        Pane wrapperBotonAtacar = arrayWrappersBotonesEdificio[1];

        Unidad unidad = (Unidad) Mapa.obtener().obtenerOcupable(coordenada);

        double anchoBoton = botonMover.getPrefWidth();
        double altoBoton = botonMover.getPrefWidth();

        crearBoton(botonMover, imagenDeMover, anchoBoton, altoBoton);
        crearBoton(botonAtacar, imagenDeAtacar, anchoBoton, altoBoton);

        botonMover.setOnAction(actionEvent -> mapaControlador.guardarCasillaMovimiento(coordenada));
        botonAtacar.setOnAction(actionEvent -> mapaControlador.guardarCasillaAtacar(coordenada));

        botonMover.setTooltip(new Tooltip("MOVER UNIDAD\n" + emojiInformacionUnicode + " Mover a la unidad a otra casilla"));
        botonAtacar.setTooltip( new Tooltip("ATACAR\n" + emojiInformacionUnicode + " Atacar a un edificio o unidad"));

        JSONObject unidadJSON = ConvertidorJSON.convertirAJSON(unidad);
        if (unidadJSON.get(ConvertidorJSON.CAMINAR).equals("no_caminar")){
            botonMover.setDisable(true);
            Tooltip.install(wrapperBotonMover, new Tooltip("MOVER UNIDAD\n" + emojiAdvertenciaUnidode + " Esta unidad ya se ha movido"));
        }

        if (unidadJSON.get(ConvertidorJSON.ATACAR).equals("no_atacar")){
            botonAtacar.setDisable(true);
            Tooltip.install(wrapperBotonAtacar, new Tooltip("ATACAR\n" + emojiAdvertenciaUnidode + " Esta movida ya ha atacado"));
        }
    }
}
