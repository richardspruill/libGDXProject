package com.mygdx.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.mygdx.entities.Player;
import com.mygdx.game.MyGdxGame;
import com.mygdx.handlers.GameStateManager;

public abstract class GameState {
    protected GameStateManager gsm;
    protected MyGdxGame game;

    protected IsometricTiledMapRenderer mapRenderer;
    protected OrthographicCamera camera;
    protected OrthographicCamera hudCam;
    protected Player player;

    protected GameState(GameStateManager gsm) {
        this.gsm = gsm;
        game = gsm.getGame();
        mapRenderer = game.getMapRenderer();
        camera = game.getCamera();
        hudCam = game.getHudCamera();
        player = game.getPlayer();
    }

    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render();
    public abstract void dispose();

}
