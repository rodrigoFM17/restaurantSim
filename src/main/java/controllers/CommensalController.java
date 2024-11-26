package controllers;

import javafx.application.Platform;
import models.Observer;
import threads.Commensal;
import views.CommensalView;

public class CommensalController implements Observer {

    Commensal commensal;
    CommensalView commensalView;

    public void exec () {
        commensal =  new Commensal(1200, 630);
        Platform.runLater(() -> {
            commensalView = new CommensalView(commensal);
            commensal.addObserver(this);
        });
        commensal.start();
    }

    @Override
    public void update(double x, double y) {
        commensalView.move(x, y);
    }
}
