package threads;

import models.*;

import java.util.List;

public class Waiter extends Position implements Runnable {
    MonitorTables monitorTables;
    MonitorOrders monitorOrders;

    public Waiter(double x, double y, MonitorTables monitorTables, MonitorOrders monitorOrders){
        this.x = x;
        this.y = y;
        this.monitorTables = monitorTables;
        this.monitorOrders = monitorOrders;
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
                Order order = new Order(tableToAttend.getNumber());

                this.monitorOrders.setOrder(order);
            }

            Order order = this.monitorOrders.getFood();

            if(order !=  null){
                System.out.println("Entregar comida");
                // Movimiento a la mesa para entregar comida notifySuscribers();

                this.monitorTables.findTable(order.getTableId());

            }

        }

    }
}
