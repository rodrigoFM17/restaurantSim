package threads;

import models.MonitorTables;
import models.Position;
import models.Table;

public class Recepcionist extends Position implements Runnable {
    private MonitorTables monitorTables;

    public Recepcionist(double x, double y, MonitorTables monitorTables){
        this.x = x;
        this.y = y;
        this.monitorTables = monitorTables;
    }

    public Table attendCommensal(Commensal c) throws Exception {
        return this.monitorTables.getTable(c);
    }

    public void dismissCommensal(Table table) {
        this.monitorTables.setTable(table);
    }

    @Override
    public void run() {

    }
}
