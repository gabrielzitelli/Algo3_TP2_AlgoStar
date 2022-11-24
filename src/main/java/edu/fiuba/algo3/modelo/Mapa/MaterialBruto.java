package edu.fiuba.algo3.modelo.Mapa;

public abstract class MaterialBruto {
    protected int cantidad;

    public int extraer(int cantidadAExtraer){

        int nuevaCantidadMaterial = cantidad - cantidadAExtraer;
        if(nuevaCantidadMaterial < 0)
            return 0;

        cantidad = nuevaCantidadMaterial;
        return cantidadAExtraer;
    }
}
