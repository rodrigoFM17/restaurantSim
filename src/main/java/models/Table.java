package models;

import threads.Commensal;

import java.awt.color.CMMException;

public class Table implements Position {

    private int number;
    private boolean busy;
    private boolean attend;
    private double x;
    private double y;
    private Commensal commensal;

    public Table(double x, double y, int number) {
        this.x = x;
        this.y = y;
        this.busy = false;
        this.commensal = null;
        this.number = number;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getX() {
        return x;
    }

    public void setBusy(boolean busy){
        this.busy = busy;
    }

    public boolean getBusy(){
        return this.busy;
    }

    public void setAttend(boolean attend){
        this.attend = attend;
    }

    public boolean getAttend(){
        return this.attend;
    }

    public int getNumber(){
        return this.number;
    }

    public void setCommensal(Commensal commensal) {
        this.commensal = commensal;
    }

    public void setStatusCommensal(Commensal.STATUS s){
        this.commensal.setStatus(s);
    }
}
