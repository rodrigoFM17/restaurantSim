package threads;

import models.MonitorTables;
import models.Table;

public class Recepcionist {
    private MonitorTables monitorTables;

    public Recepcionist(MonitorTables monitorTables){
        this.monitorTables = monitorTables;
    }

    public Table attendCommensal(Commensal c) throws Exception {
        return this.monitorTables.getTable(c);
    }

    public void dismissCommensal(Table table) {
        this.monitorTables.setTable(table);
    }
}
