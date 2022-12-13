package edu.fiuba.algo3.controladores;

import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Double.min;

public class OpcionesControlador extends Controlador {

    @FXML
    protected BorderPane mainBorderPaneMapa;
    @FXML
    protected SubScene subsceneRoot;

    private Controlador controlador;
    @FXML
    protected Slider sliderMusica;
    @FXML
    protected Slider sliderEfectos;
    @FXML
    protected Slider sliderMaestro;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void inicializar(BorderPane mainBorderPaneMapa, SubScene subsceneRoot, MapaControlador controlador){
        this.mainBorderPaneMapa = mainBorderPaneMapa;
        this.subsceneRoot = subsceneRoot;
        this.controlador = controlador;

        ControladorEfectosSonido sonido = ControladorEfectosSonido.obtenerControlador();

        sliderMusica.setValue(sonido.obtenerVolumenMusica().getValue()*100);
        sliderEfectos.setValue(sonido.obtenerVolumenFx().getValue()*100);
        sliderMaestro.setValue( min(sonido.obtenerVolumenMusica().getValue(), sonido.obtenerVolumenFx().getValue())*100);

        sliderMusica.valueProperty().addListener((o, oldValue, newValue) -> {
            sonido.modificarVolumenMusica(newValue.doubleValue());
        });
        sliderEfectos.valueProperty().addListener((o, oldValue, newValue) -> {
            sonido.modificarVolumenEfectos(newValue.doubleValue());
        });
        sliderMaestro.valueProperty().addListener((o, oldValue, newValue) -> {
            sonido.modificarVolumenMusica(newValue.doubleValue());
            sonido.modificarVolumenEfectos(newValue.doubleValue());
        });
    }

    @FXML
    public void cerrarOpciones() {
        mainBorderPaneMapa.getChildren().remove(subsceneRoot);
        controlador.cerrarOpciones();
        ControladorEfectosSonido.obtenerControlador().reproducirFX("boton");
    }
}
