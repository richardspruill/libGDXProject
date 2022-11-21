package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	private Texture maleBack;
	private Texture grasslands;
	private Sprite grasslandSprite;
	private OrthographicCamera camera;
	private Rectangle character;
	private TiledMap map1;
	private MapRenderer mapRenderer;
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		grasslands = new Texture("grassland_blend/grassland/grass_overcast.png");
		grasslands.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
		grasslandSprite = new Sprite(grasslands);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 400, 200);
		maleBack = new Texture("male/male_back.png");
		img = new Texture("badlogic.jpg");
		character = new Rectangle();
		map1 = new TmxMapLoader().load("maps/map1.tmx");

		float unitScale = 1/4f;
		mapRenderer = new IsometricTiledMapRenderer(map1, unitScale);
		character.x = 1;
		character.y = 1;
		character.width = 8;
		character.height = 8;
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0.5f, 1);
		mapRenderer.setView(camera);
		mapRenderer.render();
//		batch.setProjectionMatrix(camera.combined);
		batch.begin();
//		grasslandSprite.draw(batch);
		batch.draw(maleBack, character.x, character.y);
		batch.end();


		//Character movement
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			character.x -= 100 * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			character.x += 100 * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			character.y -= 100 * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			character.y += 100 * Gdx.graphics.getDeltaTime();
		}

		if(character.x < 0) character.x = 0;
		if(character.x > 800 - 64) character.x = 800 - 64;

		 camera.position.set(character.getX(), character.getY(), 0);
		 camera.update();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
