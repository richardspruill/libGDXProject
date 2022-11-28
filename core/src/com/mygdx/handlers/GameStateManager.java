package com.mygdx.handlers;

import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.mygdx.game.MyGdxGame;
import com.mygdx.states.GameState;
import com.mygdx.states.Play;

import java.util.Stack;

public class GameStateManager {
    private MyGdxGame game;

    private Stack<GameState> gameStates;

    public static final int PLAY = 1;

    public GameStateManager(MyGdxGame game) {
        this.game = game;
        gameStates = new Stack<GameState>();
        pushState(PLAY);
    }

    public void update(float dt) {
        gameStates.peek().update(dt);
    }

    public void render() {
        gameStates.peek().render();
    }

    public MyGdxGame getGame(){
        return game;
    }

    private GameState getState(int state){
        if(state == PLAY) return new Play(this);
        return null;
    }

    public void setState(int state) {
        popState();
        pushState(state);
    }

    public void pushState(int state) {
        gameStates.push(getState(state));
    }

    public void popState() {
        GameState state = gameStates.pop();
        state.dispose();
    }




}
