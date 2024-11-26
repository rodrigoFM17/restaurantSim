package models;

import com.almasb.fxgl.dsl.FXGL;
import javafx.application.Platform;
import views.CommensalView;
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

    @Override
    public void run() {

        int rows = 5, cols = 4;
        int stepWSize = (int) (FXGL.getAppWidth() * .5) / (cols + 1) ;
        int stepHSize = FXGL.getAppHeight() / (rows + 1);

        int n_table = 0;
        for ( int i = 1; i <= cols; i++) {
            for (int j= 1; j <= rows; j++){
                int finalJ = j;
                int finalI = i;
                Table table = new Table(finalI * stepWSize, finalJ * stepHSize, n_table);
                Platform.runLater(() -> {
                    TableView tableView = new TableView(table);
                });
                n_table++;
            }
        }

        while (true){

            Platform.runLater(() -> {
                Commensal c =  new Commensal(1200, 630);
                CommensalView commenView = new CommensalView(c);
                c.addObserver(commenView);
            });

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }

        }
    }
}
