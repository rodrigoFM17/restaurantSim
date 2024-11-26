package controllers;

import javafx.application.Platform;
import models.MonitorTables;
import threads.Recepcionist;
import views.RecepcionistView;

public class RecepcionistController {

    private Recepcionist recepcionist;
    private RecepcionistView recepcionistView;

    public void exec(MonitorTables monitorTables) {
        recepcionist = new Recepcionist(900, 100, monitorTables);
        Platform.runLater(() -> {
            recepcionistView = new RecepcionistView(recepcionist);
        });
        recepcionist.start();
    }

    public Recepcionist getRecepcionist() {
        return recepcionist;
    }
}
