package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.controladores.ElementosGui.ColorItem;
import edu.fiuba.algo3.modelo.Excepciones.ErrorDosJugadoresNoPuedenTenerElMismoColor;
import edu.fiuba.algo3.modelo.Excepciones.ErrorDosJugadoresNoPuedenTenerElMismoImperio;
import edu.fiuba.algo3.modelo.Excepciones.ErrorDosJugadoresNoPuedenTenerElMismoNombre;
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
import java.util.LinkedList;

public class DatosJugador2Controlador extends DatosJugadorControlador {

    @FXML
    private TextField nombreJugador2;
    @FXML
    private Button botonCrearJugador2;

    protected void inicializarComboBoxRaza(){
        String razaJugador2 = obtenerRazaJugador2();

        this.razaBox.getItems().addAll(razaJugador2);
        inicializarCeldasComboBoxRaza(razaBox);

        razaBox.setValue(razaJugador2);
        razaBox.setDisable(true);
    }

    private String obtenerRazaJugador2(){
        String informacionJugador1 = algoStar.jugadoresEnString()[0];
        String razaJugador2 = "Zerg";

        if(informacionJugador1.toUpperCase().contains("ZERG"))
            razaJugador2 = "Protoss";

        return razaJugador2;
    }

    protected void inicializarComboBoxColores(){

        LinkedList<ColorItem> listaColorItemsJugador2 = (LinkedList<ColorItem>) listaColorItems.clone();
        quitarColorDeJugador1(listaColorItemsJugador2);

        comboBoxColores.getItems().addAll(listaColorItemsJugador2);
        inicializarCeldasComboBoxColores(comboBoxColores);
        comboBoxColores.setValue(listaColorItemsJugador2.getFirst());
    }

    private void quitarColorDeJugador1(LinkedList<ColorItem> listaColorItemsJugador2){
        String informacionJugador1 = algoStar.jugadoresEnString()[0];

        for (int i = 0; i < listaColorItemsJugador2.size(); i++) {
            ColorItem colorItem = listaColorItemsJugador2.get(i);
            String colorString = String.valueOf( colorItem.obtenerColor() );

            if( informacionJugador1.contains(colorString) )
                listaColorItemsJugador2.remove(colorItem);
        }
    }

    @FXML
    public void iniciarJuego(ActionEvent event) throws IOException {
        guardarDatosJugador2();

        if (this.datosValidos) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/vistas/Mapa.fxml"));
            Parent root = loader.load();
            inicioControlador.prepararInicioDelJuego(root, loader);
        }
    }

    public void presionarEnterParaCrearJugador(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            botonCrearJugador2.requestFocus();
            botonCrearJugador2.fire();
        }
    }

    private void guardarDatosJugador2() {
        Imperio imperio = this.parsearImperio( razaBox.getValue() );
        try {
            algoStar.asignarJugador(nombreJugador2.getText().trim(), String.valueOf( comboBoxColores.getValue().obtenerColor() ), imperio);
            this.datosValidos = true;

        }catch (Exception exception) {
            this.manejarExcepcion(exception);
        }
    }

    private void manejarExcepcion(Exception exception){
        if(exception instanceof ErrorDosJugadoresNoPuedenTenerElMismoNombre) {
            this.mensajeError.setText("Este nombre ya fue elegido por otro jugador");
            this.mensajeError.setTextFill(paintRojoError);
            mostrarPaneError();

        } else if (exception instanceof ErrorDosJugadoresNoPuedenTenerElMismoColor) {
            this.mensajeError.setText("Este color ya fue elegido por otro jugador");
            this.mensajeError.setTextFill(paintRojoError);
            mostrarPaneError();

        } else if (exception instanceof ErrorDosJugadoresNoPuedenTenerElMismoImperio) {
            this.mensajeError.setText("Esta raza ya fue elegida por otro jugador");
            this.mensajeError.setTextFill(paintRojoError);
            mostrarPaneError();

        } else if (exception instanceof ErrorElNombreDelJugadorDebeSerMayorA6Caracteres) {
            this.mensajeError.setText("El nombre debe tener más de 6 caracteres");
            this.mensajeError.setTextFill(paintRojoError);
            mostrarPaneError();
        }
    }
}
