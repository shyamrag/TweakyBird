package com.appfinic.tweakybird.screen;

import com.appfinic.tweakybird.MainGame;
import com.appfinic.tweakybird.TextureManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class GameOverScreen implements Screen {

	private OrthographicCamera camera;
	private Sprite texture;
	private final MainGame mainGame;
	
	public GameOverScreen(MainGame mainGame) {
		this.mainGame = mainGame;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, MainGame.WIDTH, MainGame.HEIGHT);
		texture = TextureManager.getTextureManager().getGAME_OVER();
	}


	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		Gdx.graphics.setContinuousRendering(true);
	}

	@Override
	public void render(float arg0) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		if(Gdx.input.isTouched())
		{
			mainGame.setScreen(new GameScreen(mainGame));
		}
		mainGame.spriteBatch.setProjectionMatrix(camera.combined);
		mainGame.spriteBatch.begin();
		mainGame.spriteBatch.draw(texture, MainGame.WIDTH / 2 - texture.getWidth() / 2, MainGame.HEIGHT / 2 - texture.getHeight() / 2);
		mainGame.spriteBatch.end();
		
	}

	@Override
	public void show() {
		Gdx.graphics.setContinuousRendering(false);
	}
}
