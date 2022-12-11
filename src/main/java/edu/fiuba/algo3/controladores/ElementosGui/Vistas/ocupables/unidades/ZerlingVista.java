package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import javafx.scene.image.Image;

import java.util.Objects;

public class ZerlingVista extends UnidadZergVista {
    public ZerlingVista() {
        this.tile = new Tile("unidades_zerg/32px/zerling.png");
        this.identificador = "zerling";
        this.info = "Zerling";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unidades_zerg/original/zerlingRaw.png")));
        this.imagenParaBoton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unidades_zerg/original/creacion/zerlingRawCrear.png")));
        this.requisitosUnidad = "\n" + emojiInformacionUnicode + " Requiere: Reserva de reproducción";
    }
}
