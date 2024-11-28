package threads;

import models.*;

import java.util.ArrayList;

public class Waiter extends Position implements Runnable {
    MonitorTables monitorTables;
    MonitorOrders monitorOrders;
    private double originX;
    private double originY;

    public Waiter(double x, double y, MonitorTables monitorTables, MonitorOrders monitorOrders){
        this.x = x;
        this.y = y;
        this.originX = x;
        this.originY = y;
        this.monitorTables = monitorTables;
        this.monitorOrders = monitorOrders;
        this.observers = new ArrayList<>();
    }

    @Override
    public void run() {
        try{
            findTable();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void findTable() throws Exception {
        while(true){
            Table tableToAttend = this.monitorTables.attendTable();

            if(tableToAttend != null){
                moveTo(tableToAttend.getX() + 50, tableToAttend.getY() + 50, false);
                Order order = new Order(tableToAttend.getNumber());
                Thread.sleep(1000);
                moveTo(originX, originY, true);
                this.monitorOrders.setOrder(order);
            }

            Order order = this.monitorOrders.getFood();

            if(order !=  null){
                System.out.println("Entregar comida");
                // Movimiento a la mesa para entregar comida notifySuscribers();
                // para hacer el movimiento necesito saber a que mesa debo dirigirme
                this.monitorTables.findTable(order.getTableId());


            }

        }

    }
}
