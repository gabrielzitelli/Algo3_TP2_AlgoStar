package edu.fiuba.algo3.modelo.FlasheadaConObserver;

import java.util.LinkedList;

public abstract class Observable {
    protected LinkedList<Observer> observers = new LinkedList<>();

    public void suscribir(Observer observer){
        observers.add(observer);
    }

    public void desuscribir(Observer observer){
        observers.remove(observer);
    }
}
