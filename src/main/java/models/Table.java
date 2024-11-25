package models;

public class Table implements Position {

    private double x;
    private double y;
    private boolean busy;
    private Commensal commensal;

    public Table(double x, double y) {
        this.x = x;
        this.y = y;
        this.busy = false;
        this.commensal = null;
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
