package controllers;

import models.MonitorOrders;
import models.MonitorTables;
import models.Observer;
import threads.Waiter;
import views.WaiterView;

public class WaiterController implements Observer {
    Waiter waiter;
    WaiterView waiterView;

    public void exec (MonitorTables monitorTables, MonitorOrders monitorOrders) {
        waiter =  new Waiter(700, 630, monitorTables, monitorOrders);
        waiterView = new WaiterView(waiter);
        waiter.addObserver(this);
        Thread waiterThread = new Thread(waiter);
        waiterThread.start();
    }

    @Override
    public void update(double x, double y) {
        waiterView.move(x, y);
    }
}
