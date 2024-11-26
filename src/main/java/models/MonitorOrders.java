package models;

public class MonitorOrders {
    int countOrder = 0;
    int countFood = 0;
    Order[] orders;

    public MonitorOrders(Order[] orders){
        this.orders = orders;
    }

    synchronized public void setOrder(Order order) {
        int i = 0;
        while(this.orders[i] != null){
            i++;
        }

        this.orders[i] = order;
        countOrder++;
        notifyAll();
    }

    synchronized public int getOrder(){
        while(countOrder == 0){
            try{
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        int i = 0;
        while(this.orders[i].getTaken()){
            i++;
        }

        countOrder--;
        this.orders[i].setTaken(true);
        return this.orders[i].getTableId();
    }

    synchronized public void setFood(int tableId){
        countFood++;
        this.orders[tableId].setFinished(true);
        notifyAll();
    }

    synchronized public Order getFood(){
        if(countFood > 0) {
            int i = 0;
            while(!this.orders[i].getFinished()){
                i++;
            }

            Order food = this.orders[i];
            this.orders[i] = null;

            return food;
        }

        return null;
    }
}
