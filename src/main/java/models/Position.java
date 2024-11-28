package models;

import java.util.List;

public class Position implements Observable{

    public double x = 0;
    public double y = 0;
    public List<Observer> observers;

    public void sleepThread() throws InterruptedException {
        Thread.sleep(10);
    }

    public void moveX(double x) throws InterruptedException {
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

     public void moveY(double y) throws InterruptedException {
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

    public void moveTo(double x, double y, boolean xFirst) throws InterruptedException {
        if(xFirst){
            moveX(x);
            moveY(y);
        } else {
            moveY(y);
            moveX(x);
        }
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
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


}
