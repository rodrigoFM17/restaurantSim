package threads;

import models.Observable;
import models.Observer;
import models.Position;

import java.util.ArrayList;
import java.util.List;

public class Kitchener extends Thread implements Position, Observable {

    private double x;
    private double y;
    private List<Observer> observers;

    public Kitchener(double x, double y){
        this.x = x;
        this.y = y;
        this.observers = new ArrayList<>();
    }

    @Override
    public void run() {
        super.run();
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> {
            observer.update(x, y);
        });
    }
}
