package models;

public class Recepcionist {
    MonitorTables monitorTables;

    public Recepcionist(MonitorTables monitorTables){
        this.monitorTables = monitorTables;
    }

    public Table attendCommensal() throws Exception {
        return this.monitorTables.getTable();
    }

    public void dismissCommensal(Table table) {
        this.monitorTables.setTable(table);
    }
}
