package components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import javafx.application.Platform;

public class MoveComponent extends Component {
    private double x;
    private double y;

    public void move(double x, double y) {
        Platform.runLater(()->{
            if (x < FXGL.getAppWidth()){
                entity.setPosition(x, y);
            } else {
                entity.removeFromWorld();
                System.out.println("ya no estoy en la pantalla");
            }
        });
    }

}
