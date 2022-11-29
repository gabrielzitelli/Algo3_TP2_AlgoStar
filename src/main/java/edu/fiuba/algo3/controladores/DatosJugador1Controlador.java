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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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
    public void initialize() {
        this.razaBox.setItems(razaLista);
        this.razaBox.setValue("Protoss");
    }

    @FXML
    public void empezarCreacionJugador2(ActionEvent event) throws IOException {
        guardarDatosJugador1();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/vistas/datosJugador2Vista.fxml"));

        stage = obtenerStageActual(event);
        this.scene = new Scene(loader.load(), App.INITIAL_WIDTH, App.INITIAL_HEIGHT);

        stage.setScene(scene);
    }

    private void guardarDatosJugador1() {
        Imperio imperio = this.parsearImperio((String) razaBox.getValue());
        try {
            App.algoStar.asignarJugador(nombreJugador1.getText(), String.valueOf(elegirColorJugador1.getValue()), imperio);
        }catch (ErrorELNombreDelJugadorDebeSerMayorA6Caracteres e) {
            System.out.println(e);

        }

    }

    private Imperio parsearImperio(String stringImperio){
        // TODO verificar que usaruario eliga si o si una opcion de imperio
        if (stringImperio.equals("Protoss")){
            return new Protoss();
        } else {
            return new Zerg();
        }
    }
}
