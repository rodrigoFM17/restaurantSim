package views;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.Texture;
import components.MoveComponent;
import javafx.application.Platform;
import threads.Kitchener;

public class KitchenerView {

    private Entity waiterView;

    public KitchenerView(Kitchener kitchenerModel) {
            Texture kitchenerTexture = FXGL.getAssetLoader().loadTexture("kitchener.png");
            kitchenerTexture.setFitWidth(100);
            kitchenerTexture.setFitHeight(100);

            Component moveComponent = new MoveComponent();

            this.waiterView = FXGL.entityBuilder()
                    .at(kitchenerModel.getX(), kitchenerModel.getY())
                    .with(moveComponent)
                    .viewWithBBox(kitchenerTexture)
                    .buildAndAttach();
    }

        public void move(double x, double y) {
            Platform.runLater(()->{
                waiterView.getComponent(MoveComponent.class).move(x, y);
            });
        }
}
