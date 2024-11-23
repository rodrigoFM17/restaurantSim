package org.example;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import models.Commensal;
import views.CommensalView;

import java.util.Map;

public class Main extends GameApplication {


    public enum EntityType {
        PLAYER, COIN, COMMENSAL
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1280);
        settings.setHeight(720);
        settings.setTitle("Basic Game App");
    }

    private Entity player;

    @Override
    protected void initGame() {
        // Crear un cuadrado y posicionarlo
        Commensal c =  new Commensal();
        CommensalView commenView = new CommensalView(c);
        c.addObserver(commenView);
        c.start();


        FXGL.entityBuilder()
                .type(EntityType.COIN)
                .at(500, 200)
                .viewWithBBox(new Circle(15, 15, 15, Color.YELLOW))
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }

    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.COIN) {

            // order of types is the same as passed into the constructor
            @Override
            protected void onCollisionBegin(Entity player, Entity coin) {
                coin.removeFromWorld();
            }
        });
    }

    @Override
    protected void initInput() {
        FXGL.onKey(KeyCode.D, () -> {
            player.translateX(5); // move right 5 pixels
            FXGL.inc("pixelsMoved", +5);
        });

        FXGL.onKey(KeyCode.A, () -> {
            player.translateX(-5); // move left 5 pixels
            FXGL.inc("pixelsMoved", +5);
        });

        FXGL.onKey(KeyCode.W, () -> {
            player.translateY(-5); // move up 5 pixels
            FXGL.inc("pixelsMoved", +5);
        });

        FXGL.onKey(KeyCode.S, () -> {
            player.translateY(5); // move down 5 pixels
            FXGL.inc("pixelsMoved", +5);
        });
    }

    @Override
    protected void initUI() {
        Text textPixels = new Text();
        textPixels.setTranslateX(50); // x = 50
        textPixels.setTranslateY(100); // y = 100
        textPixels.textProperty().bind(FXGL.getWorldProperties().intProperty("pixelsMoved").asString());
        FXGL.getGameScene().addUINode(textPixels); // add to the scene graph

    }
    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("pixelsMoved", 0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}