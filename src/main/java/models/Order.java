package models;

public class Order {
    private int tableId;
    private boolean finished = false;
    private boolean taken = false;

    public Order(int tableId) {
        this.tableId = tableId;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean getFinished() {
        return finished;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public boolean getTaken() {
        return taken;
    }

    public int getTableId() {
        return tableId;
    }
}
