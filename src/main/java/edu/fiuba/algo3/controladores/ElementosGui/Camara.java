package edu.fiuba.algo3.controladores.ElementosGui;

public class Camara {

    private int x = 0;
    private int y = 0;
    private int bordeX;
    private int bordeY;

    private final int razonDeMovimiento = 8;

    public Camara(int bordeX, int bordeY) {
        this.bordeX -= bordeX;
        this.bordeY -= bordeY;
    }

    public void subir() {
        if (y < 0){
            y += razonDeMovimiento;
        }
        else y = 0;
    }

    public void bajar() {
        if (y > bordeY)
            y -= razonDeMovimiento;
        else
            y = bordeY;
    }

    public void irAderecha() {
        if (x > bordeX)
            x -= razonDeMovimiento;
        else
            x = bordeX;
    }
    public void irAIzquierda() {
        if (x < 0) {
            x += razonDeMovimiento;
        }
        else x = 0;
    }

    public int getX() {return x;}

    public int getY() {return y;}

    public void setBordeX(int bordeX) {
        this.bordeX = -bordeX;
    }
    public void setBordeY(int bordeY){
        this.bordeY = -bordeY;
    }


    public void irHacia(int x, int y) {
        this.x = -x;
        this.y = -y;
    }
}
