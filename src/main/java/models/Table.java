package models;

public class Table implements Position {

    private double x;
    private double y;
    private boolean busy;
    private Commensal commensal;

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getX() {
        return x;
    }
}
