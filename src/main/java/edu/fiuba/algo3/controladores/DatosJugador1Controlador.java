package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.controladores.ElementosGui.ColorItem;
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
import javafx.scene.SubScene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DatosJugador1Controlador extends Controlador{
    private Stage stage;
    private Scene scene;
    @FXML
    private SubScene subsceneForm;
    @FXML
    private AnchorPane anchorPaneMain;
    @FXML
    private ComboBox<String> razaBox;
    @FXML
    private TextField nombreJugador1;
    @FXML
    private Label mensajeError;
    @FXML
    private AnchorPane anchorPaneError;
    @FXML
    private Button botonEntiendo;
    @FXML
    private ComboBox<ColorItem> comboBoxColores;

    private boolean datosValidos = false;

    //Colores
    //Conversor de RGB 0-255 a RGB 0-1: https://giancr.com/calculadora-rgb-escala-0-255-0-1-hexadecimal/
    private ColorItem colorAzul = new ColorItem("Azul", new Color(0.1216, 0.3843, 0.8118, 1));
    private ColorItem colorRojo = new ColorItem("Rojo", new Color(0.8118, 0.1216, 0.1216, 1));
    private ColorItem colorVerde = new ColorItem("Verde",  new Color(0.0824, 0.749, 0.2392, 1));
    private ColorItem colorRosa = new ColorItem("Rosa", new Color(0.749, 0.0824, 0.5608, 1));
    private ColorItem colorAmarillo =  new ColorItem("Amarillo", new Color(0.898, 0.9098, 0.2, 1));

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle){
        this.inicializarComboBoxRaza();
        this.inicializarComboBoxColores();
    }

    private void inicializarComboBoxRaza(){
        this.razaBox.getItems().addAll("Protoss", "Zerg");

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
        razaBox.setValue("Protoss");
    }

    private void inicializarComboBoxColores(){

        comboBoxColores.getItems().addAll(colorAzul, colorRojo, colorVerde, colorRosa, colorAmarillo);

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
        comboBoxColores.setValue(colorAzul);
    }

    public void setSubscene(SubScene subScene){
        this.subsceneForm = subScene;
    }


    private void mostrarForm(SubScene subscene){
        //Primero se esperan 500ms
        FadeTransition animacionEsperarYMostrarForm = new FadeTransition(Duration.millis(300), subscene);
        animacionEsperarYMostrarForm.setFromValue(0);
        animacionEsperarYMostrarForm.setToValue(0);
        animacionEsperarYMostrarForm.setOnFinished(e -> {

            //Luego se muestra
            FadeTransition animacionMostrarForm = new FadeTransition(Duration.millis(300), subscene);
            animacionMostrarForm.setFromValue(0);
            animacionMostrarForm.setToValue(1);
            animacionMostrarForm.play();

        });

        animacionEsperarYMostrarForm.play();

        subscene.setVisible(true);
    }

    @FXML
    public void empezarCreacionJugador2(ActionEvent event) throws IOException {
        guardarDatosJugador1();

        if (this.datosValidos) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/vistas/datosJugador2Vista.fxml"));
            Parent root = loader.load();

            //this.subsceneForm = (SubScene) anchorPaneMain.getParent();

            /*mostrarForm(subsceneForm);
            System.out.println(subsceneForm.getLayoutY());
            subsceneForm.setDisable(false);
            subsceneForm.setVisible(true);*/
            //this.subsceneForm.setFill(Color.BLUE);

            //SubScene scene = NodeHelper.getSubScene(razaBox);
            //scene.setRoot(loader.load());
            //this.subsceneForm.setVisible(true);
            //this.mostrarForm(subsceneForm);
            //this.subsceneForm.getChildren().(loader.load());

            stage = obtenerStageActual(razaBox);
            this.scene = new Scene(root, App.INITIAL_WIDTH, App.INITIAL_HEIGHT);

            stage.setScene(scene);
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
    }

    private void guardarDatosJugador1() {

        Imperio imperio = this.parsearImperio(razaBox.getValue());
        try {
            //Color picker viejo
            //App.algoStar.asignarJugador(nombreJugador1.getText(), String.valueOf(elegirColorJugador1.getValue()), imperio);

            App.algoStar.asignarJugador(nombreJugador1.getText(), String.valueOf( comboBoxColores.getValue().obtenerColor() ), imperio);
            this.datosValidos = true;
            this.mensajeError.setText("Datos Validos");
            this.mensajeError.setTextFill(Color.web("#00690c"));

        }catch (ErrorELNombreDelJugadorDebeSerMayorA6Caracteres e) {
            mostrarPaneError();
            this.mensajeError.setText("El nombre debe tener más de 6 caracteres");
            this.mensajeError.setTextFill(Color.web("#940a00"));
        }
    }

    private Imperio parsearImperio(String stringImperio){

        if (stringImperio.equals("Protoss"))
            return new Protoss();
        else
            return new Zerg();
    }
}
