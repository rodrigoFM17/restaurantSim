package threads;

import models.Observable;
import models.Observer;
import models.Position;
import models.Table;

import java.util.ArrayList;
import java.util.List;

public class Commensal extends Thread implements Position, Observable {

    private enum STATUS {
        NEW, WTABLE, WWAITRESS
    }

    private List<Observer> observers;
    private double x;
    private double y;
    private STATUS status;
    private Table table;
    private Recepcionist r;

    public Commensal(Recepcionist r) {
        this.x = 100;
        this.y = 100;
        this.status = STATUS.NEW;
        this.table = null;
        this.observers = new ArrayList<>();
        this.r = r;
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

            try {
                this.table = r.attendCommensal();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            r.dismissCommensal(this.table);





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