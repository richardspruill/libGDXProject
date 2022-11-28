package com.mygdx.maps;

import com.badlogic.gdx.maps.tiled.TiledMap;

public class World extends TiledMap {
    private Position startingPosition;



    /**
     * initialized the map
     */
    public void init(float x, float y){
        startingPosition = new Position(x, y);
    }

    public Position getStartingPosition(){

        return startingPosition;
    }



    public class Position {
        private float x;
        private float y;

        Position(float x , float y){
            this.x = x;
            this.y = y;
        }

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }
    }
}
