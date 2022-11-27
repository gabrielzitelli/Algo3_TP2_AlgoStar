package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class TestMapaControlador extends Controlador {

    private Mapa elMapa;
    private Zerg imperioZerg;
    private Protoss imperioProtoss;
    @FXML
    GridPane mainGrid;

    public TestMapaControlador(){
        elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        imperioZerg = new Zerg();
        imperioProtoss = new Protoss();
    }

    @FXML
    public void comenzarJuego(ActionEvent event){
        this.inicializarMapa();
    }

    public Node getNodeByCoordinate(Integer row, Integer column) {
        for (Node node : mainGrid.getChildren()) {
            if(GridPane.getColumnIndex(node) == row && GridPane.getColumnIndex(node) == column){
                return node;
            }
        }
        return null;
    }

    EventHandler<ActionEvent> buttonEventHandler(){
        return event -> {
            Node node = (Node) event.getTarget();
            int row = GridPane.getRowIndex(node);
            int column = GridPane.getColumnIndex(node);
        };
    }

    @FXML
    public void clickSobreNodoDeMainGrid(MouseEvent event){
        ///Node clickedNode = (Node)event.getSource();
        //if(clickedNode != mainGrid) {
        // click on descendant node
            //int colIndex = GridPane.getColumnIndex(clickedNode);
            //int rowIndex = GridPane.getRowIndex(clickedNode);
            //System.out.println("Mouse clicked cell: " + colIndex + " And: " + rowIndex);
        //System.out.println(clickedNode);
        //}
        //System.out.println(event.nodeClicked);
        //Integer row = GridPane.getRowIndex(node);
        //Integer column = GridPane.getColumnIndex(node);
        //Node nodoPresionado = getNodeByCoordinate(row, column);
        //System.out.println(event.getTarget());
    }

    @FXML
    public void inicializarMapa(){
        for(Node node : mainGrid.getChildren()) {
            node.setOnMouseClicked(event -> {
                Node nodeClicked = (Node) event.getSource();
                int row = GridPane.getRowIndex(nodeClicked);
                int column = GridPane.getColumnIndex(nodeClicked);

                System.out.println("Mouse clicked cell: " + row + " And: " + column);
            });
        }

    }
}
