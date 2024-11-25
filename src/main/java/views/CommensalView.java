package views;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.Texture;
import models.Commensal;
import models.Observer;


public class CommensalView implements Observer {

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

    @Override
    public void update(double x, double y) {
        System.out.println("mne muevo negros");
        commensalView.setPosition(x, y);
    }
}
