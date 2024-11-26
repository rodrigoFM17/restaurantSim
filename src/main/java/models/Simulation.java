package models;

import com.almasb.fxgl.dsl.FXGL;
import controllers.CommensalController;
import controllers.RecepcionistController;
import javafx.application.Platform;
import threads.Recepcionist;
import views.CommensalView;
import views.RecepcionistView;
import views.TableView;
import threads.Commensal;

public class Simulation extends Thread {

    private int capacity;
    private int waitress;
    private int tables;
    private int kitcheners;

    public Simulation(int capacity){
        this.capacity = capacity;
        this.waitress = (int) (capacity * 0.1);
        this.tables = capacity;
        this.kitcheners = (int) (capacity * 0.15);
    }

    private Table[] initTables() {
        int rows = 5, cols = 4;
        int stepWSize = (int) (FXGL.getAppWidth() * .5) / (cols + 1) ;
        int stepHSize = FXGL.getAppHeight() / (rows + 1);
        int n_table = 0;

        Table[] tables = new Table[capacity];

        for ( int i = 1; i <= cols; i++) {
            for (int j= 1; j <= rows; j++){
                Table table = new Table(i * stepWSize, j * stepHSize, n_table);
                tables[n_table] = table;
                Platform.runLater(() -> {
                    TableView tableView = new TableView(table);
                });
                n_table++;
            }
        }

        return tables;
    }

    @Override
    public void run() {

        Table[] tables = initTables();
        MonitorTables monitorTable = new MonitorTables(tables, capacity);

        while (true){

            CommensalController commensalController = new CommensalController();
            commensalController.exec();

            RecepcionistController recepcionistController = new RecepcionistController();
            recepcionistController.exec(monitorTable);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }

        }
    }
}
