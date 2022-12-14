package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Excepciones.ErrorElNombreDelJugadorDebeSerMayorA6Caracteres;
import edu.fiuba.algo3.modelo.Imperio.Imperio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class DatosJugador1Controlador extends DatosJugadorControlador{

    @FXML
    private TextField nombreJugador1;
    @FXML
    private Button botonCrearJugador1;

    protected void inicializarComboBoxRaza(){
        this.razaBox.getItems().addAll("Protoss", "Zerg");
        inicializarCeldasComboBoxRaza(razaBox);
        razaBox.setValue("Protoss");
    }

    protected void inicializarComboBoxColores(){
        comboBoxColores.getItems().addAll(listaColorItems);
        inicializarCeldasComboBoxColores(comboBoxColores);
        comboBoxColores.setValue(colorAzul);
    }

    @FXML
    public void empezarCreacionJugador2(ActionEvent event) throws IOException {
        ControladorEfectosSonido sonido = ControladorEfectosSonido.obtenerControlador();
        sonido.reproducirFX("boton");
        guardarDatosJugador1();

        if (this.datosValidos) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/vistas/datosJugador2Vista.fxml"));
            Parent root = loader.load();
            inicioControlador.realizarCargaJugador2(root, loader);
        }
    }

    private void guardarDatosJugador1() {

        Imperio imperio = this.parsearImperio(razaBox.getValue());
        try {
            algoStar.asignarJugador(nombreJugador1.getText().trim(), String.valueOf( comboBoxColores.getValue().obtenerColor() ), imperio);
            this.datosValidos = true;

        }catch (ErrorElNombreDelJugadorDebeSerMayorA6Caracteres exception) {
            this.mensajeError.setText("El nombre debe tener más de 6 caracteres");
            this.mensajeError.setTextFill(paintRojoError);
            mostrarPaneError();
        }
    }

    public void presionarEnterParaCrearJugador(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            botonCrearJugador1.requestFocus();
            botonCrearJugador1.fire();
        }
    }
}
