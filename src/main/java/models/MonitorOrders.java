package models;

import java.util.Arrays;
import java.util.Queue;

public class MonitorOrders {
    int countOrder = 0;
    int countFood = 0;
    Queue<Order> orders;

    public MonitorOrders(Queue<Order> orders){
        this.orders = orders;
    }

    synchronized public void setOrder(Order order) {

        this.orders.add(order);

        System.out.println(this.orders);

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
        for (Order order : orders) {
            if (!order.getTaken()) {
                order.setTaken(true);
                i = order.getTableId();
                break;
            }
        }

        countOrder--;

        return i;
    }

    synchronized public void setFood(int tableId){
        countFood++;

        for (Order order : orders) {
            if (order.getTableId() == tableId) {
                order.setFinished(true);
                break;
            }
        }

        notifyAll();
    }

    synchronized public Order getFood(){
        Order food = null;

        if(countFood > 0) {

            for (Order order : orders) {
                if (order.getFinished()) {
                    food = this.orders.remove();
                    break;
                }
            }


            this.countFood--;
        }

        return food;
    }
}
