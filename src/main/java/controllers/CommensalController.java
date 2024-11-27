package controllers;

import javafx.application.Platform;
import models.Observer;
import threads.Commensal;
import threads.Recepcionist;
import views.CommensalView;

public class CommensalController implements Observer {

    Commensal commensal;
    CommensalView commensalView;

    public void exec (Recepcionist recepcionist) {
        commensal =  new Commensal(1200, 630, recepcionist);
        commensalView = new CommensalView(commensal);
        commensal.addObserver(this);
        Thread commensalThread = new Thread(commensal);
        commensalThread.start();
    }

    @Override
    public void update(double x, double y) {
        commensalView.move(x, y);
    }
}
