package views;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.Texture;
import models.Table;

public class TableView {
    private Entity tableView;
    private enum EntityType {
        TABLE
    }

    public TableView(Table tableModel) {

        Texture tableTexture = FXGL.getAssetLoader().loadTexture("table.png");
        tableTexture.setFitWidth(50);
        tableTexture.setFitHeight(50);

        this.tableView = FXGL.entityBuilder()
                .type(EntityType.TABLE)
                .at(tableModel.getX(), tableModel.getY())
                .viewWithBBox(tableTexture)
                .buildAndAttach();
    }

}
