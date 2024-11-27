package components;

import com.almasb.fxgl.entity.component.Component;
import javafx.application.Platform;

public class MoveComponent extends Component {
    private double x;
    private double y;

    public void move(double x, double y) {
        Platform.runLater(()->{
            entity.setPosition(x, y);
        });
    }

}
