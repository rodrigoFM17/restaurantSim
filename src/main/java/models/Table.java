package models;

import threads.Commensal;

public class Table implements Position {

    public boolean busy = false;
    public boolean attend = false;
    public int number;
    private double x;
    private double y;
    private Commensal commensal;

    public Table(int number){
        this.number = number;
    }

    public int getNumber(){
        return number;
    }
    
    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getX() {
        return x;
    }
}
