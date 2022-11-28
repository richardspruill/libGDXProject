package com.mygdx.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Arrays;

public class Animator {

    // Constant rows and columns of the sprite sheet
    private static final int WALK_FRAME_COLS = 9, WALK_FRAME_ROWS = 4;

    public static final int WALK_UP_INDEX = 0;
    public static final int WALK_RIGHT_INDEX = 3;
    public static final int WALK_DOWN_INDEX = 2;
    public static final int WALK_LEFT_INDEX = 1;

    // Objects used
    Animation<TextureRegion>[] walkAnimation; // Must declare frame type (TextureRegion)

    TextureRegion[] standAnimation;
    Texture walkSheet;

    // A variable for tracking elapsed time for the animation
    float stateTime;

    public Animator() {

        // Load the sprite sheet as a Texture
        walkSheet = new Texture(Gdx.files.internal("male_spritesheet.png"));

        // Use the split utility method to create a 2D array of TextureRegions. This is
        // possible because this sprite sheet contains frames of equal size and they are
        // all aligned.
        TextureRegion[][] tmp = TextureRegion.split( walkSheet,64,64);

        // Place the regions into a 1D array in the correct order, starting from the top
        // left, going across first. The Animation constructor requires a 1D array.
        walkAnimation = new Animation[4];
        standAnimation = new TextureRegion[4];
        for (int i = 0; i < WALK_FRAME_ROWS; i++) {
            int index = 0;
            TextureRegion[] walkFrames = new TextureRegion[WALK_FRAME_COLS - 1];
            for (int j = 0; j < WALK_FRAME_COLS; j++) {
                if (j == 0) {
                    standAnimation[i] = tmp[i][j];
                } else {
                    walkFrames[index++] = tmp[i][j];
                }
            }
            // Initialize the Animation with the frame interval and array of frames
            walkAnimation[i] = new Animation<TextureRegion>(0.075f, walkFrames);
        }

        // reset the elapsed animation time to 0
        stateTime = 0f;
    }

    /**
     * Return the walk animation for a specific direction
     * 0 = walk up,
     * 1 = walk left,
     * 2 = walk down,
     * 3 = walk right
     * @param direction direction index, see static class variables.
     * @return Animation object for walking in specified direction
     */
    public TextureRegion getWalkAnimation(int direction) {
        stateTime += Gdx.graphics.getDeltaTime();

        return walkAnimation[direction].getKeyFrame(stateTime, true);
    }

    public TextureRegion getStandAnimation(int direction) {
        return standAnimation[direction];
    }

    public void dispose() {
        // SpriteBatches and Textures must always be disposed
        walkSheet.dispose();
    }
}
