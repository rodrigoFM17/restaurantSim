package org.example;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.GameView;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.texture.Texture;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import models.Commensal;
import models.Simulation;
import models.Table;
import views.CommensalView;
import views.TableView;

import java.util.Map;

public class Main extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1280);
        settings.setHeight(720);
        settings.setTitle("Basic Game App");
    }

    private Entity player;

    @Override
    protected void initGame() {

        var background = new ImageView(FXGL.image("background.png"));

        background.setFitWidth(FXGL.getAppWidth());
        background.setFitHeight(FXGL.getAppHeight());

        FXGL.getGameScene().getContentRoot().getChildren().add(0, background);

        Simulation sim = new Simulation(20);
        sim.start();
    }

    @Override
    protected void initPhysics() {

    }


//    @Override
//    protected void initUI() {
//        Text textPixels = new Text();
//        textPixels.setTranslateX(50); // x = 50
//        textPixels.setTranslateY(100); // y = 100
//        textPixels.textProperty().bind(FXGL.getWorldProperties().intProperty("pixelsMoved").asString());
//        FXGL.getGameScene().addUINode(textPixels); // add to the scene graph
//
//    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("pixelsMoved", 0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}