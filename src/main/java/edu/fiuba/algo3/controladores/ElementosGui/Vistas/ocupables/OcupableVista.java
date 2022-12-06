package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables;

import edu.fiuba.algo3.controladores.ElementosGui.Vistas.Vista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios.*;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades.*;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;

public abstract class OcupableVista extends Vista {

    private static ArrayList<Vista> ocupables = inicializarLista();
    protected Image imagenParaBoton;

    private static ArrayList<Vista> inicializarLista(){
        ArrayList<Vista> ocupable = new ArrayList<>();
        iniciarConEdificios(ocupable);
        iniciarConUnidades(ocupable);
        return ocupable;
    }

    private static void iniciarConUnidades(ArrayList<Vista> ocupable) {
        ocupable.add(new SinOcuparVista());

        //Protoss
        ocupable.add(new DragonVista());
        ocupable.add(new ScoutVista());
        ocupable.add(new ZealotVista());
        ocupable.add(new ZealotInvisibleVista());

        //Zerg
        ocupable.add(new AmoSupremoVista());
        ocupable.add(new DevoradorVista());
        ocupable.add(new GuardianVista());
        ocupable.add(new HidraliscoVista());
        ocupable.add(new MutaliscoVista());
        ocupable.add(new ZanganoVista());
        ocupable.add(new ZerlingVista());
    }

    private static void iniciarConEdificios(ArrayList<Vista> ocupable) {
        //Protoss
        ocupable.add(new CriaderoVista());
        ocupable.add(new EspiralVista());
        ocupable.add(new ExtractorVista());
        ocupable.add(new ReservaVista());
        ocupable.add(new GuaridaVista());

        //Protoss
        ocupable.add(new AccesoVista());
        ocupable.add(new AsimiladorVista());
        ocupable.add(new NexoMineralVista());
        ocupable.add(new PilonVista());
        ocupable.add(new PuertoEstelarVista());
    }

    public static Vista obtenerOcupable(Object obtenerOcupable) {
        return obtenerVista((String)obtenerOcupable, ocupables);
    }

    public void aplicarTextoEscudo(Text textoEscudo, String stringOcupable){
        textoEscudo.setText("");
    }

    public void manejarBotones(Button[] arrayBotones, Pane[] arrayWrappersBotonesEdificio, Coordenada coordenada, String imperioDeJugadorActual){
        for(Button boton : arrayBotones)
            boton.setVisible(false);
    }

    public void renderAdentroDeImageViewBoton(ImageView imageView){
        imageView.setImage(imagenParaBoton);
    }

    protected void crearBotonDeUnidad(Button boton, OcupableVista vistaUnidad, double ancho, double alto){
        ImageView imagenZealot = new ImageView();
        imagenZealot.setPreserveRatio(false);
        imagenZealot.setSmooth(true);

        vistaUnidad.renderAdentroDeImageViewBoton(imagenZealot);

        imagenZealot.setFitWidth(ancho);
        imagenZealot.setFitHeight(alto);

        DropShadow efectorBordePNGBlanco = new DropShadow( 1, Color.WHITE );
        imagenZealot.setEffect(efectorBordePNGBlanco);

        boton.setVisible(true);
        boton.setDisable(false);
        boton.setGraphic(imagenZealot);
    }
}
