package threads;

import java.util.ArrayList;
import java.util.List;
import models.Position;
import models.Observable;
import models.Observer;
import models.Table;


public class Commensal extends Thread implements Position, Observable {

    private enum STATUS {
        NEW, WTABLE, WWAITRESS
    }

    private List<Observer> observers;
    private double x;
    private double y;
    private STATUS status;
    private Table table;

    public Commensal(double x, double y) {
        this.x = x;
        this.y = y;
        this.status = STATUS.NEW;
        this.table = null;
        this.observers = new ArrayList<>();
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> observer.update(x, y));
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
    public void run() {
        while(true){
            this.x += 0;
            this.y += -5;
            System.out.println(x);
            System.out.println(y);
            notifyObservers();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }
}