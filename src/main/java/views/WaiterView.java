package views;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.Texture;
import components.MoveComponent;
import javafx.application.Platform;
import threads.Commensal;
import threads.Waiter;

public class WaiterView {
    private Entity waiterView;

    public WaiterView(Waiter waiterModel){
        Texture waiterTexture = FXGL.getAssetLoader().loadTexture("waitress.png");
        waiterTexture.setFitWidth(50);
        waiterTexture.setFitHeight(50);

        Component moveComponent = new MoveComponent();

        this.waiterView = FXGL.entityBuilder()
                .at(waiterModel.getX(), waiterModel.getY())
                .with(moveComponent)
                .viewWithBBox(waiterTexture)
                .buildAndAttach();
    }

    public void move(double x, double y) {
        Platform.runLater(()->{
            waiterView.getComponent(MoveComponent.class).move(x, y);
        });
    }
}
