package com.mygdx.handlers;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class MapConfig {

    // pixels per tile
    public static int tilePx = 16;

    public static Vector2 getStartTileCoords(TiledMap map) {
        Vector2 coords = new Vector2();
        MapObject startTile = map.getLayers().get("points").getObjects().get("starting_point");
        coords.set((float) startTile.getProperties().get("x"), (float) startTile.getProperties().get("y"));
        return coords;
    }

    public static Vector2 getNorthEastBounds(TiledMap map) {
        Vector2 coords = new Vector2();
        TiledMapTileLayer floor = (TiledMapTileLayer) map.getLayers().get("floor");
        coords.set(floor.getWidth() * floor.getTileWidth() - 30, floor.getHeight() * floor.getTileHeight());
        return coords;
    }
}
