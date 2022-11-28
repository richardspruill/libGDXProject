package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entities.Player;
import com.mygdx.handlers.GameStateManager;

/**
 * Main Game class
 *
 *
 */
public class MyGdxGame extends ApplicationAdapter {
	public static final String TITLE = "Hood RPG";
	public static final int V_WIDTH = 600;
	public static final int V_HEIGHT = 400;
	public static final int SCALE = 1;
	public static final int FPS = 60;
	public static final float STEP = 1 / 60f;
	private float timeBuffer;
	private OrthographicCamera camera;
	private OrthographicCamera hudCamera;

	private GameStateManager gsm;

	private Player player;

	/**
	 * Initializes the game
	 */
	@Override
	public void create () {

		camera = new OrthographicCamera();
		camera.setToOrtho(false, V_WIDTH / 2, V_HEIGHT / 2);
		hudCamera = new OrthographicCamera();
		hudCamera.setToOrtho(false, V_WIDTH, V_HEIGHT);
		player = new Player(new Sprite(new Texture("male/male_back.png")));
		player.setScale(0.4f);

		float unitScale = 1/2f;
		gsm = new GameStateManager(this);
	}

	@Override
	public void render () {
		timeBuffer += Gdx.graphics.getDeltaTime();
		while(timeBuffer >= STEP) {
			timeBuffer -= STEP;
			gsm.update(STEP);
			gsm.render();
		}
	}
	
	@Override
	public void dispose () {
		player.dispose();
	}

	private Vector2 worldToIso(Vector2 point, int tileWidth, int tileHeight) {
		point.x /= tileWidth;
//		point.y =
		return null;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public OrthographicCamera getHudCamera() {
		return hudCamera;
	}

	public Player getPlayer() {
		return player;
	}
}
