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
        System.out.println(this.tables[i].getBusy());
        while(this.tables[i].getBusy()){
            System.out.println(i);
            i++;
        }
        this.tables[i].setBusy(true);
        this.countTables--;
        this.countClient++;
        return this.tables[i];
    }

    synchronized public void setTable(Table table){
        this.tables[table.getNumber()].setBusy(false);
        this.tables[table.getNumber()].setAttend(false);
        this.countTables++;
        this.countClient--;

        System.out.println(this.tables[table.getNumber()]);
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
        while(this.tables[i].getBusy() && this.tables[i].getAttend()){
            i++;
        }
        this.tables[i].setAttend(true);

        System.out.println(this.tables[i]);

        return this.tables[i];
    }
}
