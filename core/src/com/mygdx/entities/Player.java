package com.mygdx.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite {

    /** movement velocity */
    private Vector2 velocity;

    /** movement speed */
    private float speed ;

    private boolean isFacingRight;
    private boolean isFacingLeft;
    private boolean isFacingUp;
    private boolean isFacingDown;

    public Player(Sprite sprite){
        super(sprite);
        isFacingDown = false;
        isFacingLeft = false;
        isFacingRight = false;
        isFacingUp = true;
    }

    @Override
    public void draw(Batch spriteBatch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }

    private void update(float deltaTime) {

    }

    public void render(){
        //Character movement
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            setX(getX() - 50 * Gdx.graphics.getDeltaTime());
            resetFacingValue(4);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            setX(getX() + 50 * Gdx.graphics.getDeltaTime());
            resetFacingValue(2);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            setY(getY() - 50 * Gdx.graphics.getDeltaTime());
            resetFacingValue(3);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            setY(getY() + 50 * Gdx.graphics.getDeltaTime());
            resetFacingValue(1);
        }
    }

    public void updateBoundaries(Vector2 northEast) {
        if (getX() > northEast.x){
            setX(northEast.x);
        }
    }

    @Override
    public void setX(float x){
        x = x < 0 ? 0 : x;

        super.setX(x);
    }

    @Override
    public void setY(float y){
        y = y < 0 ? 0 : y;

        super.setY(y);
    }

    /**
     *
     * @param direction 1 = up, 2 = right, 3 = down, 4 = left
     */
    private void resetFacingValue(int direction) {
        isFacingLeft = direction == 4;
        isFacingUp = direction == 1;
        isFacingRight = direction == 2;
        isFacingDown = direction == 3;
    }
}
