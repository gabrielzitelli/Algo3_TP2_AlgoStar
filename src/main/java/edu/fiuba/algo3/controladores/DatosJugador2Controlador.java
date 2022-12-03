package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.controladores.ElementosGui.ColorItem;
import edu.fiuba.algo3.modelo.Excepciones.ErrorDosJugadoresNoPuedenTenerElMismoColor;
import edu.fiuba.algo3.modelo.Excepciones.ErrorDosJugadoresNoPuedenTenerElMismoImperio;
import edu.fiuba.algo3.modelo.Excepciones.ErrorDosJugadoresNoPuedenTenerElMismoNombre;
import edu.fiuba.algo3.modelo.Excepciones.ErrorELNombreDelJugadorDebeSerMayorA6Caracteres;
import edu.fiuba.algo3.modelo.Imperio.Imperio;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class DatosJugador2Controlador extends Controlador {

    private Stage stage;
    private Scene scene;
    private boolean datosValidos = false;

    @FXML
    private TextField nombreJugador2;
    @FXML
    private ComboBox<ColorItem> comboBoxColores;
    @FXML
    private ComboBox<String> razaBox;
    @FXML
    private Label mensajeError;
    @FXML
    private AnchorPane anchorPaneError;
    @FXML
    private Button botonEntiendo;
    @FXML
    private Button botonCrearJugador2;
    @FXML
    private AnchorPane anchorPaneForm;

    //Colores
    //Conversor de RGB 0-255 a RGB 0-1: https://giancr.com/calculadora-rgb-escala-0-255-0-1-hexadecimal/
    private ColorItem colorAzul = new ColorItem("Azul", new Color(0.1216, 0.3843, 0.8118, 1));
    private ColorItem colorRojo = new ColorItem("Rojo", new Color(0.8118, 0.1216, 0.1216, 1));
    private ColorItem colorVerde = new ColorItem("Verde",  new Color(0.0824, 0.749, 0.2392, 1));
    private ColorItem colorRosa = new ColorItem("Rosa", new Color(0.749, 0.0824, 0.5608, 1));
    private ColorItem colorAmarillo =  new ColorItem("Amarillo", new Color(0.898, 0.9098, 0.2, 1));
    private InicioControlador inicioControlador;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle){
        this.inicializarComboBoxRaza();
        this.inicializarComboBoxColores();
    }

    private void inicializarComboBoxRaza(){

        String informacionJugador1 = App.algoStar.jugadoresEnString()[0];
        String razaJugador2 = "Zerg";

        if(informacionJugador1.toUpperCase().contains("ZERG"))
            razaJugador2 = "Protoss";

        this.razaBox.getItems().addAll(razaJugador2);

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
        razaBox.setValue(razaJugador2);
        razaBox.setDisable(true);
    }

    private void inicializarComboBoxColores(){

        LinkedList<ColorItem> listaColorItems = new LinkedList<ColorItem>( Arrays.asList(colorAzul, colorRojo, colorVerde, colorRosa, colorAmarillo) );

        String informacionJugador1 = App.algoStar.jugadoresEnString()[0];

        for (int i = 0; i < listaColorItems.size(); i++) {
            ColorItem colorItem = listaColorItems.get(i);
            String colorString = String.valueOf( colorItem.obtenerColor() );

            if( informacionJugador1.contains(colorString) )
                listaColorItems.remove(colorItem);
        }

        comboBoxColores.getItems().addAll(listaColorItems);
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
        comboBoxColores.setValue(listaColorItems.getFirst());
    }

    public void setInicioControlador(InicioControlador inicioControlador){
        this.inicioControlador = inicioControlador;
    }

    @FXML
    public void iniciarJuego(ActionEvent event) throws IOException, InterruptedException {
        guardarDatosJugador2();

        if (this.datosValidos) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/vistas/Mapa.fxml"));
            Parent root = loader.load();
            inicioControlador.iniciarJuego(root, loader);
        }
    }

    public void ocultarPaneError(){
        FadeTransition animacionOcultarPaneError = new FadeTransition(Duration.millis(100), anchorPaneError);
        animacionOcultarPaneError.setFromValue(1.0);
        animacionOcultarPaneError.setToValue(0);
        animacionOcultarPaneError.setOnFinished(e -> anchorPaneError.setVisible(false));
        animacionOcultarPaneError.play();
    }

    private void mostrarPaneError(){
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

    public void presionarEnterParaCrearJugador(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER){
            botonCrearJugador2.requestFocus();
            botonCrearJugador2.fire();
        }
    }

    private void guardarDatosJugador2() {
        Imperio imperio = this.parsearImperio( razaBox.getValue() );
        try {
            App.algoStar.asignarJugador(nombreJugador2.getText().trim(), String.valueOf( comboBoxColores.getValue().obtenerColor() ), imperio);
            this.datosValidos = true;
            this.mensajeError.setText("Datos Validos");
            this.mensajeError.setTextFill(Color.web("#00690c"));
        }catch (Exception exception) {
            this.manejarExcepcion(exception);
        }
    }

    private void manejarExcepcion(Exception exception){
        if(exception instanceof ErrorDosJugadoresNoPuedenTenerElMismoNombre) {
            this.mensajeError.setText("Este nombre ya fue elegido por otro jugador");
            this.mensajeError.setTextFill(Color.web("#940a00"));
            mostrarPaneError();

        } else if (exception instanceof ErrorDosJugadoresNoPuedenTenerElMismoColor) {
            this.mensajeError.setText("Este color ya fue elegido por otro jugador");
            this.mensajeError.setTextFill(Color.web("#940a00"));
            mostrarPaneError();

        } else if (exception instanceof ErrorDosJugadoresNoPuedenTenerElMismoImperio) {
            this.mensajeError.setText("Esta raza ya fue elegida por otro jugador");
            this.mensajeError.setTextFill(Color.web("#940a00"));
            mostrarPaneError();

        } else if (exception instanceof ErrorELNombreDelJugadorDebeSerMayorA6Caracteres) {
            this.mensajeError.setText("El nombre debe tener más de 6 caracteres");
            this.mensajeError.setTextFill(Color.web("#940a00"));
            mostrarPaneError();
        }
    }

    private Imperio parsearImperio(String stringImperio){
        if (stringImperio.equals("Protoss"))
            return new Protoss();
        else
            return new Zerg();
    }

    public void ponerFocusEnForm() {
        anchorPaneForm.requestFocus();
    }
}
