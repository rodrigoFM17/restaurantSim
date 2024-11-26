package views;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.Texture;
import threads.Commensal;

public class CommensalView {

    private final Entity commensalView;
    private enum EntityType {
        COMMENSAL
    }

    public CommensalView(Commensal commensalModel){
        Texture commensalTexture = FXGL.getAssetLoader().loadTexture("commensal.png");
        commensalTexture.setFitWidth(50);
        commensalTexture.setFitHeight(50);

        this.commensalView = FXGL.entityBuilder()
                .type(EntityType.COMMENSAL)
                .at(commensalModel.getX(), commensalModel.getY())
                .viewWithBBox(commensalTexture)
                .buildAndAttach();

    }

    public void move(double x, double y) {
        System.out.println("me muevo");
        commensalView.setPosition(x, y);
    }
}
