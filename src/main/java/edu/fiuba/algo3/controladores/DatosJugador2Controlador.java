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
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class DatosJugador2Controlador extends Controlador{
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
    public void initialize() {
        this.razaBox2.setItems(razaLista);
        this.razaBox2.setValue("Seleccionar Raza");
    }

    @FXML
    public void iniciarJuego(ActionEvent event) throws IOException {
        guardarDatosJugador2();
    }

    private void guardarDatosJugador2() {
        Imperio imperio = this.parsearImperio((String) razaBox2.getValue());
        try {
            App.algoStar.asignarJugador(nombreJugador2.getText(), String.valueOf(elegirColorJugador2.getValue()), imperio);
        }catch (Exception e) {
            System.out.println(e);
            this.manejarExcepcion(e);
        }
    }

    private void manejarExcepcion(Exception e){
        /* TODO
        * Hacer que dependiendo de la excepcion que se reciba se le diga al usuario que dato
        * tiene que modificar
        */
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
