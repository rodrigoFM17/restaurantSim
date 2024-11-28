package threads;

import java.util.ArrayList;
import java.util.List;
import models.Position;
import models.Observer;
import models.Table;


public class Commensal extends Position implements Runnable{

    public enum STATUS {
        NEW, WFOOD, SERVE
    }

    private STATUS status;
    private Table table;
    private Recepcionist r;

    public Commensal(double x, double y, Recepcionist r) {
        this.x = x;
        this.y = y;
        this.status = STATUS.NEW;
        this.table = null;
        this.observers = new ArrayList<>();
        this.r = r;
    }

    @Override
    public void run() {

        try {
            moveTo(r.getX() + 50, r.getY(), false);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
                try {
                    System.out.println("Pidiendo mesa");
                    this.table = this.r.attendCommensal(this);
                    moveTo(table.getX() - 50, table.getY(), true);
                    this.status = STATUS.WFOOD;
                    System.out.println("Tengo mesa y por eso espero mi comida");

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                while(this.status == STATUS.WFOOD){
                    Thread.sleep(1000);
                }

                System.out.println("Comiendo...");
                Thread.sleep(10000);

                r.dismissCommensal(this.table);
                moveTo(1280, r.getY(), false);


            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }



    }

    public void setStatus(STATUS s){
        this.status = s;
    }
}