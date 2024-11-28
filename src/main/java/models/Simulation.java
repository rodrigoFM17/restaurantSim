package models;

import com.almasb.fxgl.dsl.FXGL;
import controllers.CommensalController;
import controllers.KitchenerController;
import controllers.RecepcionistController;
import controllers.WaiterController;
import javafx.application.Platform;
import threads.Kitchener;
import threads.Recepcionist;
import threads.Waiter;
import views.CommensalView;
import views.RecepcionistView;
import views.TableView;
import threads.Commensal;

import java.util.LinkedList;
import java.util.Queue;

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

        Queue<Order> orders = new LinkedList<>();
        MonitorOrders monitorOrders = new MonitorOrders(orders);

        RecepcionistController recepcionistController = new RecepcionistController();
        recepcionistController.exec(monitorTable);

        Platform.runLater(() ->{
            WaiterController waiterController = new WaiterController();
            waiterController.exec(monitorTable, monitorOrders);
        });

        Platform.runLater(() ->{
            KitchenerController kitchenerController = new KitchenerController();
            kitchenerController.exec(monitorOrders);
        });

        while (true){
            System.out.println("nuevo comensal");
            Platform.runLater(()->{
                CommensalController commensalController = new CommensalController();
                commensalController.exec(recepcionistController.getRecepcionist());
            });

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }



//            for (int i = 0; i < 2; i++) {
//                Waiter w = new Waiter(monitorTable, monitorOrders);
//                w.start();
//            }
//
//            for (int i = 0; i < 3; i++) {
//                Kitchener k = new Kitchener(10, 10, monitorOrders);
//                k.start();
//            }

//            for (int i = 0; i < 25; i++){
//                CommensalController commensalController = new CommensalController();
//                commensalController.exec(recepcionistController.getRecepcionist());
////                try{
////                    Thread.sleep(Math.round(Math.random() * 7000));
////                } catch (Exception e) {
////                    throw new RuntimeException(e);
////                }
//            }






    }
}
