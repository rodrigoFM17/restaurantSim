package threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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

    private void sleepThread() throws InterruptedException {
        Thread.sleep(10);
    }

    private void moveX(double x) throws InterruptedException {
        while (this.x != x){
            if (this.x > x){
                this.x -= 2;
            } else {
                this.x += 2;
            }
                notifyObservers();
                sleepThread();
        }
    }

    private void moveY(double y) throws InterruptedException {
        while (this.y != y){
            if (this.y > y){
                this.y -= 2;
            } else {
                this.y += 2;
            }
            notifyObservers();
            sleepThread();
        }
    }

    private void moveTo(double x, double y, boolean xFirst) throws InterruptedException {
        if(xFirst){
            moveX(x);
            moveY(y);
        } else {
            moveY(y);
            moveX(x);
        }
    }

    @Override
    public void run() {

        try {
            moveTo(r.getX() + 50, r.getY(), false);
//            moveToRecepcionist();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
                try {
                    System.out.println("Pidiendo mesa");
                    this.table = this.r.attendCommensal(this);
                    this.status = STATUS.WFOOD;
                    moveTo(table.getX() - 50, table.getY(), true);
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