package edu.fiuba.algo3;

import edu.fiuba.algo3.TestGui.Titulo;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppPrincipal extends Application {
    @Override
    public void start(Stage stage) {
        stage.setScene(new Titulo(stage).getScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
