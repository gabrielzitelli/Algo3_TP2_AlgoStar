package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TutorialControlador extends Controlador{

    @FXML
    protected AnchorPane mainPane;
    @FXML
    protected Pagination paginationTutorial;
    @FXML
    protected Button botonVolverAlMenu;

    private final Image[] imagenesTutorial = new Image[15];
    private final ImageView[] imageViewTutorial = new ImageView[15];

    public void initialize(URL url, ResourceBundle resourceBundle){
        inicializarImagenes();
        paginationTutorial.setPageFactory((Integer pageIndex) -> {
            return imageViewTutorial[pageIndex];
        });
    }

    private void inicializarImagenes(){
        imagenesTutorial[0] = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/tutorial/0.png")));
        imagenesTutorial[1] = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/tutorial/1.png")));
        imagenesTutorial[2] = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/tutorial/2.png")));
        imagenesTutorial[3] = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/tutorial/3.png")));
        imagenesTutorial[4] = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/tutorial/4.png")));
        imagenesTutorial[5] = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/tutorial/5.png")));
        imagenesTutorial[6] = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/tutorial/6.png")));
        imagenesTutorial[7] = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/tutorial/7.png")));
        imagenesTutorial[8] = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/tutorial/8.png")));
        imagenesTutorial[9] = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/tutorial/9.png")));
        imagenesTutorial[10] = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/tutorial/10.png")));
        imagenesTutorial[11] = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/tutorial/11.png")));
        imagenesTutorial[12] = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/tutorial/12.png")));
        imagenesTutorial[13] = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/tutorial/13.png")));
        imagenesTutorial[14] = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/tutorial/14.png")));

        for (int i = 0; i < 15; i++) {
            imageViewTutorial[i] = new ImageView(imagenesTutorial[i]);
            imageViewTutorial[i].setFitHeight(App.INITIAL_HEIGHT - 100);
            imageViewTutorial[i].setFitWidth(App.INITIAL_WIDTH - 100);
        }
    }

    public void volverAlMenu() throws IOException {
        ControladorEfectosSonido.obtenerControlador().reproducirFX("boton");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/vistas/inicioVista.fxml"));
        loader.setRoot(new BorderPane());
        Parent root = loader.load();

        Scene scene = new Scene(root);

        Stage stage = obtenerStageActual(botonVolverAlMenu);

        stage.setScene(scene);
        InicioControlador controlador = loader.getController();
        controlador.inicializar();
    }
}
