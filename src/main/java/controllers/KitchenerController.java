package controllers;

import models.MonitorOrders;
import models.Observer;
import threads.Kitchener;
import views.KitchenerView;

public class KitchenerController implements Observer {
    Kitchener kitchener;
    KitchenerView kitchenerView;

    public void exec(MonitorOrders monitorOrders){
        kitchener = new Kitchener(800, 630, monitorOrders);
        kitchenerView = new KitchenerView(kitchener);
        kitchener.addObserver(this);
        Thread kitchenerThread = new Thread(kitchener);
        kitchenerThread.start();

    }

    @Override
    public void update(double x, double y) {
        kitchenerView.move(x, y);
    }
}
