package threads;

import java.util.ArrayList;
import java.util.List;
import models.Position;
import models.Observable;
import models.Observer;
import models.Table;


public class Commensal extends Thread implements Position, Observable {

    public enum STATUS {
        NEW, WFOOD, SERVE
    }

    private List<Observer> observers;
    private double x;
    private double y;
    private STATUS status;
    private Table table;
    private Recepcionist r;

    public Commensal(double x, double y, Recepcionist r) {
        this.x = x;
        this.y = y;
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

            this.x += 0;
            this.y += -5;
            System.out.println(x);
            System.out.println(y);
            notifyObservers();
            try {
                try {
                    System.out.println("Pidiendo mesa");
                    this.table = this.r.attendCommensal(this);
                    this.status = STATUS.WFOOD;
                    System.out.println("Tengo mesa y por eso espero mi comida");

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                while(this.status == STATUS.WFOOD){
                    Thread.sleep(9000);
                }

                System.out.println("Comiendo...");
                Thread.sleep(10000);

                r.dismissCommensal(this.table);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
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

    public void setStatus(STATUS s){
        this.status = s;
    }
}