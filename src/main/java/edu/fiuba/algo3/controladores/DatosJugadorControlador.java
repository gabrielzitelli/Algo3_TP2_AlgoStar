package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.controladores.ElementosGui.ColorItem;
import edu.fiuba.algo3.modelo.AlgoStar.AlgoStar;
import edu.fiuba.algo3.modelo.Imperio.Imperio;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ResourceBundle;

public abstract class DatosJugadorControlador extends Controlador{

    protected InicioControlador inicioControlador;
    protected boolean datosValidos = false;
    protected final AlgoStar algoStar = App.algoStar;

    @FXML
    protected AnchorPane anchorPaneForm;
    @FXML
    protected ComboBox<ColorItem> comboBoxColores;
    @FXML
    protected ComboBox<String> razaBox;
    @FXML
    protected AnchorPane anchorPaneError;
    @FXML
    protected Label mensajeError;
    @FXML
    protected Button botonEntiendo;

    //Colores
    //Conversor de RGB 0-255 a RGB 0-1: https://giancr.com/calculadora-rgb-escala-0-255-0-1-hexadecimal/
    protected final ColorItem colorAzul = new ColorItem("Azul", new Color(0.1216, 0.3843, 0.8118, 1));
    protected final ColorItem colorRojo = new ColorItem("Rojo", new Color(0.8118, 0.1216, 0.1216, 1));
    protected final ColorItem colorVerde = new ColorItem("Verde",  new Color(0.0824, 0.749, 0.2392, 1));
    protected final ColorItem colorRosa = new ColorItem("Rosa", new Color(0.749, 0.0824, 0.5608, 1));
    protected final ColorItem colorAmarillo =  new ColorItem("Amarillo", new Color(0.898, 0.9098, 0.2, 1));
    final LinkedList<ColorItem> listaColorItems = new LinkedList<>(Arrays.asList(colorAzul, colorRojo,
            colorVerde, colorRosa, colorAmarillo));
    protected final Paint paintRojoError = Color.web("#940a00");

    protected abstract void inicializarComboBoxRaza();
    protected abstract void inicializarComboBoxColores();
    public abstract void presionarEnterParaCrearJugador(KeyEvent event);

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle){
        this.inicializarComboBoxRaza();
        this.inicializarComboBoxColores();
    }

    public void setInicioControlador(InicioControlador inicioControlador){
        this.inicioControlador = inicioControlador;
    }

    protected void inicializarCeldasComboBoxRaza(ComboBox<String> razaBox){
        razaBox.setCellFactory(p -> new ListCell<>() {

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null)
                    setText("");
                else
                    setText(item);
            }
        });
        razaBox.setButtonCell(razaBox.getCellFactory().call(null));
    }

    protected void inicializarCeldasComboBoxColores(ComboBox<ColorItem> comboBoxColores){
        comboBoxColores.setCellFactory(p -> new ListCell<>() {

            private final Rectangle rectangle;
            {
                setContentDisplay(ContentDisplay.LEFT);
                rectangle = new Rectangle(15, 15);
            }

            @Override
            protected void updateItem(ColorItem item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                    setText("");
                } else {
                    rectangle.setFill(item.obtenerColor());
                    setGraphic(rectangle);
                    setText(" " + item.obtenerValor());
                }
            }
        });
        comboBoxColores.setButtonCell(comboBoxColores.getCellFactory().call(null));
    }

    public void ocultarPaneError(){
        FadeTransition animacionOcultarPaneError = new FadeTransition(Duration.millis(100), anchorPaneError);
        animacionOcultarPaneError.setFromValue(1.0);
        animacionOcultarPaneError.setToValue(0);
        animacionOcultarPaneError.setOnFinished(e -> anchorPaneError.setVisible(false));
        animacionOcultarPaneError.play();
    }

    protected void mostrarPaneError(){
        FadeTransition animacionMostrarPaneError = new FadeTransition(Duration.millis(100), anchorPaneError);
        animacionMostrarPaneError.setFromValue(0);
        animacionMostrarPaneError.setToValue(1.0);
        animacionMostrarPaneError.play();

        anchorPaneError.setVisible(true);
        anchorPaneError.requestFocus();
    }

    public void ocultarPaneErrorConEnter(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER)
            botonEntiendo.fire();
    }

    protected Imperio parsearImperio(String stringImperio){
        if (stringImperio.equals("Protoss"))
            return new Protoss();
        else
            return new Zerg();
    }

    public void ponerFocusEnForm() {
        anchorPaneForm.requestFocus();
    }
}
