package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	private Texture maleBack;
	private Texture grasslands;
	private Sprite grasslandSprite;
	private OrthographicCamera camera;
	private Rectangle character;
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		grasslands = new Texture("grassland_blend/grassland/grass_overcast.png");
		grasslands.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
		grasslandSprite = new Sprite(grasslands);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 400);
		maleBack = new Texture("male/male_back.png");
		img = new Texture("badlogic.jpg");
		character = new Rectangle();
		character.x = 800;
		character.y = 20;
		character.width = 64;
		character.height = 64;
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0.5f, 1);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			grasslandSprite.draw(batch);
		batch.draw(maleBack, character.x, character.y);
		batch.end();
		camera.update();


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
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
