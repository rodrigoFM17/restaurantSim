package models;

import threads.Commensal;

import java.util.Queue;

public class MonitorTables {
    Table[] tables;
    Queue<Table> clients;
    int countTables;
    int countClient = 0;

    public MonitorTables(Table[] tables, Queue<Table> clients, int countTables){
        this.tables = tables;
        this.clients = clients;
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

        this.clients.add(this.tables[i]);

        return this.tables[i];
    }

    synchronized public void setTable(Table tableLeaved){

        for(Table table: tables){
            if(table.getNumber() == tableLeaved.getNumber()){
                table.setBusy(false);
            }
        }

        this.countTables++;

        notifyAll();
    }

    synchronized public Table attendTable() throws Exception {
        if(countClient > 0){

            Table table = clients.remove();

            this.countClient--;

            return table;
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
