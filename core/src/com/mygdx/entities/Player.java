package com.mygdx.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.handlers.Animator;

public class Player extends Sprite {
    /** animation class */
    private Animator animator;

    /** movement velocity */
    private Vector2 velocity;

    /** movement speed */
    private float speed ;

    private int currentWalkingDirection;

    public Player(Sprite sprite){
        super(sprite);
        animator = new Animator();
    }

    @Override
    public void draw(Batch spriteBatch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }

    private void update(float deltaTime) {

    }

    public void render(Batch batch){
        //Character movement
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            setX(getX() - 50 * Gdx.graphics.getDeltaTime());
            setWalkingAnimation(Animator.WALK_LEFT_INDEX);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            setX(getX() + 50 * Gdx.graphics.getDeltaTime());
            setWalkingAnimation(Animator.WALK_RIGHT_INDEX);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            setY(getY() - 50 * Gdx.graphics.getDeltaTime());
            setWalkingAnimation(Animator.WALK_DOWN_INDEX);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            setY(getY() + 50 * Gdx.graphics.getDeltaTime());
            setWalkingAnimation(Animator.WALK_UP_INDEX);
        }
        if(noMovementKeyPressed()) {
            batch.draw(animator.getStandAnimation(currentWalkingDirection), getX(), getY());
        } else {
            batch.draw(animator.getWalkAnimation(currentWalkingDirection), getX(), getY());
        }
    }

    private boolean noMovementKeyPressed(){
        return !Gdx.input.isKeyPressed(Input.Keys.LEFT) &&
                !Gdx.input.isKeyPressed(Input.Keys.RIGHT) &&
                !Gdx.input.isKeyPressed(Input.Keys.DOWN) &&
                !Gdx.input.isKeyPressed(Input.Keys.UP);

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
     * @param direction 0 = up, 1 = right, 2 = down, 3 = left
     */
    private void setWalkingAnimation(int direction) {
        if (currentWalkingDirection != direction) {
            currentWalkingDirection = direction;
        }
    }

    public void dispose(){
        animator.dispose();
    }
}
