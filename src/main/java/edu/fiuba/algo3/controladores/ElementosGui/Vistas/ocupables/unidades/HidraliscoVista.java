package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import javafx.scene.image.Image;

import java.util.Objects;

public class HidraliscoVista extends UnidadZergVista {
    public HidraliscoVista() {
        this.tile = new Tile("unidades_zerg/32px/hidralisco.png");
        this.identificador = "hidralisco";
        this.info = "Hidralisco";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unidades_zerg/original/hidraliscoRaw.png")));
        this.imagenParaBoton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unidades_zerg/original/creacion/hidraliscoRawCrear.png")));
        this.requisitosUnidad = "\n" + emojiInformacionUnicode + " Requiere: Guarida";
        this.archivoDeSonido = "hidralisco";
    }
}
