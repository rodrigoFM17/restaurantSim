package views;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.Texture;
import components.MoveComponent;
import javafx.application.Platform;
import models.Observer;
import threads.Commensal;

import java.util.Random;

public class CommensalView{

    private Entity commensalView;

    public CommensalView(Commensal commensalModel){
        Random random = new Random();
        Texture commensalTexture = FXGL.getAssetLoader().loadTexture("commensal" + (random.nextInt(5) + 1) + ".png");
        commensalTexture.setFitWidth(50);
        commensalTexture.setFitHeight(50);

        Component moveComponent = new MoveComponent();

            this.commensalView = FXGL.entityBuilder()
                    .at(commensalModel.getX(), commensalModel.getY())
                    .with(moveComponent)
                    .viewWithBBox(commensalTexture)
                    .buildAndAttach();
    }

    public void move(double x, double y) {
        Platform.runLater(()->{
            commensalView.getComponent(MoveComponent.class).move(x, y);
        });
    }

}
