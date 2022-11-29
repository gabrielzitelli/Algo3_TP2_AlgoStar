package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.Excepciones.ErrorELNombreDelJugadorDebeSerMayorA6Caracteres;
import edu.fiuba.algo3.modelo.Imperio.Imperio;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DatosJugador1Controlador extends Controlador{
    private Stage stage;
    private Scene scene;
    @FXML
    private ChoiceBox razaBox;
    ObservableList<String> razaLista = FXCollections.observableArrayList("Protoss", "Zergs");

    @FXML
    private TextField nombreJugador1;
    @FXML
    private ColorPicker elegirColorJugador1;

    @FXML
    private Label mensajeError;

    private boolean datosValidos = false;
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle){
        this.razaBox.setItems(razaLista);
        this.razaBox.setValue("Protoss");
    }

    @FXML
    public void empezarCreacionJugador2(ActionEvent event) throws IOException {
        guardarDatosJugador1();

        if (this.datosValidos) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/vistas/datosJugador2Vista.fxml"));

            stage = obtenerStageActual(event);
            this.scene = new Scene(loader.load(), App.INITIAL_WIDTH, App.INITIAL_HEIGHT);

            stage.setScene(scene);
        }
    }

    private void guardarDatosJugador1() {
        Imperio imperio = this.parsearImperio((String) razaBox.getValue());
        try {
            App.algoStar.asignarJugador(nombreJugador1.getText(), String.valueOf(elegirColorJugador1.getValue()), imperio);
            this.datosValidos = true;
            this.mensajeError.setText("Datos Validos");
            this.mensajeError.setTextFill(Color.GREEN);
        }catch (ErrorELNombreDelJugadorDebeSerMayorA6Caracteres e) {
            this.mensajeError.setText("Nombre del jugador debe ser\nmayor a 6 caracteres");
            this.mensajeError.setTextFill(Color.RED);
        }
    }

    private Imperio parsearImperio(String stringImperio){
        if (stringImperio.equals("Protoss")){
            return new Protoss();
        } else {
            return new Zerg();
        }
    }
}
