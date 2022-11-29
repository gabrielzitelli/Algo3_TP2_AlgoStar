package edu.fiuba.algo3.controladores.ElementosGui;

public class Camara {

    private int x = 0;
    private int y = 0;
    private int bordeX;
    private int bordeY;

    private int razonDeMovimiento = 8;

    public Camara(int bordeX, int bordeY) {
        this.bordeX -= bordeX;
        this.bordeY = - bordeY;
    }

    public void subir() {
        if (y < 0){
            y += razonDeMovimiento;
        }
    }

    public void bajar() {
        if (y > bordeY)
            y -= razonDeMovimiento;
    }

    public void irAderecha() {
        if (x > bordeX)
            x -= razonDeMovimiento;
    }
    public void irAIzquierda() {
        if (x < 0) {
            x += razonDeMovimiento;
        }
    }

    public int getX() {return x;}

    public int getY() {return y;}



}
