package views;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.Texture;
import threads.Recepcionist;


public class RecepcionistView {

    private Entity recepcionistView;

    public RecepcionistView(Recepcionist recepcionist) {
        Texture recepcionistTexture = FXGL.getAssetLoader().loadTexture("recepcionist.png");
        recepcionistTexture.setFitWidth(50);
        recepcionistTexture.setFitHeight(50);

        this.recepcionistView = FXGL.entityBuilder()
                .at(recepcionist.getX(), recepcionist.getY())
                .viewWithBBox(recepcionistTexture)
                .buildAndAttach();
    }
}
