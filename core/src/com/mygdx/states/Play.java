package com.mygdx.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.MyGdxGame;
import com.mygdx.handlers.Box2DConfig;
import com.mygdx.handlers.GameStateManager;
import com.mygdx.handlers.MapConfig;

import static com.mygdx.handlers.Box2DConfig.*;

public class Play extends GameState {

    private World world;
    private Box2DDebugRenderer box2DRenderer;
    private OrthographicCamera box2dCam;

    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer mapRenderer;

    public Play(GameStateManager gsm) {
        super(gsm);

        world = new World(new Vector2(0, 0), true);
        box2DRenderer = new Box2DDebugRenderer();

        // static body - don't move, unaffected by forces
        // kinematic body - don't get affected by forces, but can move
        // dynamic body - always get affected by forces
        BodyDef bd = new BodyDef();
        bd.position.set(convertSize(160), convertSize(120));
        bd.type = BodyDef.BodyType.StaticBody;

        Body body = world.createBody(bd);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50 / Box2DConfig.PPM, 5 / Box2DConfig.PPM);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        body.createFixture(fixDef);

        // falling box
        bd.position.set(160 / Box2DConfig.PPM, 200 / Box2DConfig.PPM);
        bd.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bd);

        shape.setAsBox(5 / Box2DConfig.PPM, 5 / Box2DConfig.PPM);
        fixDef.shape = shape;
        body.createFixture(fixDef);

        box2dCam = new OrthographicCamera();
        box2dCam.setToOrtho(false, convertSize(MyGdxGame.V_WIDTH), convertSize(MyGdxGame.V_HEIGHT));


        ///////////
        // load tile map
//        float unitScale = 1/2f;
        tiledMap = new TmxMapLoader().load("roguelike/Map/start_map.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        Vector2 startCoords = MapConfig.getStartTileCoords(tiledMap);
        player.setPosition(startCoords.x, startCoords.y);
        camera.position.set(MapConfig.getStartTileCoords(tiledMap), 1f);
        camera.update();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        world.step(dt, 6, 2);
    }

    @Override
    public void render() {
        // clear screen
//        ScreenUtils.clear(0, 0, 0.5f, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // draw tile map
        mapRenderer.setView(camera);
        mapRenderer.render();
        Batch batch = mapRenderer.getBatch();
        batch.begin();
        player.render(batch);
        batch.end();

        player.updateBoundaries(MapConfig.getNorthEastBounds(tiledMap));
        camera.position.set(new Vector2(player.getX(), player.getY()), 1f);
        camera.update();
    }

    @Override
    public void dispose() {

    }
}
