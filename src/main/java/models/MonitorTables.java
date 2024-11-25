package models;

public class MonitorTables {
    Table[] tables;
    int countTables;
    int countClient = 0;

    public MonitorTables(Table[] tables, int countTables){
        this.tables = tables;
        this.countTables = countTables;
    }

    synchronized public Table getTable() throws Exception {
        while(this.countTables == 0){
            try{
                wait();
            }catch (InterruptedException e){
                throw new Exception(e);
            }
        }

        int i = 0;
        while(this.tables[i].busy){
            i++;
        }
        this.tables[i].busy = false;
        this.countTables--;
        this.countClient++;
        return this.tables[i];
    }

    synchronized public void setTable(Table table){
        this.tables[table.number].busy = false;
        this.tables[table.number].attend = false;
        this.countTables++;
        this.countClient--;
    }

    synchronized public Table attendTable() throws Exception {
        while(this.countClient == 0){
            try{
                wait();
            }catch (InterruptedException e){
                throw new Exception(e);
            }
        }

        int i = 0;
        while(this.tables[i].busy && this.tables[i].attend){
            i++;
        }
        this.tables[i].attend = true;
        return this.tables[i];
    }
}
