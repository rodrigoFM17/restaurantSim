package threads;

import models.MonitorOrders;
import models.Observable;
import models.Observer;
import models.Position;

import java.util.ArrayList;
import java.util.List;

public class Kitchener extends Position implements Runnable {

    private MonitorOrders monitorOrders;

    public Kitchener(double x, double y, MonitorOrders monitorOrders){
        this.x = x;
        this.y = y;
        this.observers = new ArrayList<>();
        this.monitorOrders = monitorOrders;
    }

    @Override
    public void run() {
        while(true){
            int orderId = this.monitorOrders.getOrder();

            System.out.println("Cocinando la orden " + orderId);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            this.monitorOrders.setFood(orderId);
        }
    }
}
