package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.Excepciones.ErrorDosJugadoresNoPuedenTenerElMismoColor;
import edu.fiuba.algo3.modelo.Excepciones.ErrorDosJugadoresNoPuedenTenerElMismoImperio;
import edu.fiuba.algo3.modelo.Excepciones.ErrorDosJugadoresNoPuedenTenerElMismoNombre;
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

public class DatosJugador2Controlador extends Controlador {
    private Stage stage;
    private Scene scene;
    @FXML
    private ChoiceBox razaBox2;
    ObservableList<String> razaLista = FXCollections.observableArrayList("Protoss", "Zergs");
    @FXML
    private TextField nombreJugador2;
    @FXML
    private ColorPicker elegirColorJugador2;
    @FXML
    private Label mensajeError;
    private boolean datosValidos = false;
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle){
        this.razaBox2.setItems(razaLista);
        this.razaBox2.setValue("Protoss");
    }

    @FXML
    public void iniciarJuego(ActionEvent event) throws IOException {
        guardarDatosJugador2();
        if (this.datosValidos) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/vistas/Mapa.fxml"));

            stage = obtenerStageActual(event);
            this.scene = new Scene(loader.load(), App.INITIAL_WIDTH, App.INITIAL_HEIGHT);

            MapaControlador controlador = loader.getController();
            this.scene.setOnMouseClicked(controlador.pintarCasilla());
            this.scene.setOnKeyPressed(controlador.pressKey());
            this.scene.setOnKeyReleased(controlador.releaseKey());

            stage.setScene(scene);
        }
    }

    private void guardarDatosJugador2() {
        Imperio imperio = this.parsearImperio((String) razaBox2.getValue());
        try {
            App.algoStar.asignarJugador(nombreJugador2.getText(), String.valueOf(elegirColorJugador2.getValue()), imperio);
            this.datosValidos = true;
            this.mensajeError.setText("Datos Validos");
            this.mensajeError.setTextFill(Color.GREEN);
        }catch (Exception e) {
            this.manejarExcepcion(e);
        }
    }

    private void manejarExcepcion(Exception e){
        if(e instanceof ErrorDosJugadoresNoPuedenTenerElMismoNombre) {
            this.mensajeError.setText("Dos jugadores no pueden\ntener el mismo nombre");
            this.mensajeError.setTextFill(Color.RED);
        } else if (e instanceof ErrorDosJugadoresNoPuedenTenerElMismoColor) {
            this.mensajeError.setText("Dos jugadores no pueden\ntener el mismo color");
            this.mensajeError.setTextFill(Color.RED);
        } else if (e instanceof ErrorDosJugadoresNoPuedenTenerElMismoImperio) {
            this.mensajeError.setText("Dos jugadores no pueden\ntener la misma raza");
            this.mensajeError.setTextFill(Color.RED);
        } else if (e instanceof ErrorELNombreDelJugadorDebeSerMayorA6Caracteres) {
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
