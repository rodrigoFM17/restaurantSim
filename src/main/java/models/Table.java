package models;

public class Table implements Position {

    public boolean busy;
    public boolean attend;
    public int number;
    private double x;
    private double y;
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
