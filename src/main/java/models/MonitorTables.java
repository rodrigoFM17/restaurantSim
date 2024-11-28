package models;

import threads.Commensal;

public class MonitorTables {
    Table[] tables;
    int countTables;
    int countClient = 0;

    public MonitorTables(Table[] tables, int countTables){
        this.tables = tables;
        this.countTables = countTables;
    }

    synchronized public Table getTable(Commensal c) throws Exception {
        while(this.countTables == 0){
            try{
                wait();
            }catch (InterruptedException e){
                throw new Exception(e);
            }
        }

        int i = 0;
        while(this.tables[i].getBusy()){
            i++;
        }
        this.tables[i].setBusy(true);
        this.tables[i].setCommensal(c);
        this.countTables--;
        this.countClient++;
        return this.tables[i];
    }

    synchronized public void setTable(Table table){
        this.tables[table.getNumber()].setBusy(false);
        this.tables[table.getNumber()].setAttend(false);
        this.countTables++;

        System.out.println(this.tables[table.getNumber()]);
    }

    synchronized public Table attendTable() throws Exception {
        if(countClient > 0){
            int i = 0;
            while(this.tables[i].getAttend()){
                i++;
            }
            this.tables[i].setAttend(true);

            this.countClient--;

            return this.tables[i];
        }
        return null;
    }

    public Table findTable(int tableId){
        int i = 0;

        while(this.tables[i].getNumber() != tableId){
            i++;
        }

        return this.tables[i];
    }

    public void serveCommensal(Table table){
        table.getCommensal().setStatus(Commensal.STATUS.SERVE);
    }

}
