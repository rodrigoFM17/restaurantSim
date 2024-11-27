package controllers;

import models.Observer;
import threads.Recepcionist;
import threads.Waiter;
import views.WaiterView;

public class WaiterController implements Observer {
    Waiter waiter;
    WaiterView waiterView;

    public void exec (Recepcionist recepcionist) {
        waiter =  new Waiter(1200, 630, null, null);
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
