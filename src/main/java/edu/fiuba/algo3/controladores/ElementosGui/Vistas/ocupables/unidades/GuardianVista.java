package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import javafx.scene.image.Image;

import java.util.Objects;

public class GuardianVista extends UnidadZergVista {
    public GuardianVista() {
        this.tile = new Tile("unidades_zerg/32px/guardian.png");
        this.identificador = "guardian";
        this.info = "Guardian";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unidades_zerg/original/guardianRaw.png")));
        this.imagenParaBoton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unidades_zerg/original/creacion/guardianRawEvolucionar.png")));
        this.archivoDeSonido = "guardian";
    }
}
