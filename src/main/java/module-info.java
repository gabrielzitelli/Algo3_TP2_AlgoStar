module edu.fiuba.algo3 {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    opens edu.fiuba.algo3.controladores to javafx.fxml;
    exports edu.fiuba.algo3;
}