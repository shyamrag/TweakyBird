package com.appfinic.tweakybird.screen;

import com.appfinic.tweakybird.MainGame;
import com.appfinic.tweakybird.TextureManager;
import com.appfinic.tweakybird.constants.Config;
import com.appfinic.tweakybird.entity.EntityManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class GameScreen implements Screen {

	static final int STATE_READY = 0;
	static final int STATE_RUNNING = 1;
	static final int STATE_PAUSED = 2;
	static final int STATE_OVER = 3;
	
	private final MainGame mainGame;
	private OrthographicCamera camera;
	private EntityManager entityManager;
	
	Vector3 touchPoint;
	int currentState;
	
	Rectangle pauseBounds;
	Rectangle resumeBounds;
	
	
	public GameScreen(MainGame mainGame) {
		
		currentState = STATE_READY;
		
		this.mainGame = mainGame;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, MainGame.WIDTH, MainGame.HEIGHT);
		
		entityManager = new EntityManager(mainGame,3, camera);
		
		touchPoint = new Vector3();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void dispose() {
		entityManager.dispose();
	}

	@Override
	public void pause() {
		if (currentState == STATE_RUNNING) currentState = STATE_PAUSED;
		entityManager.pause();
	}

	@Override
	public void resume() {
		if (currentState == STATE_PAUSED) currentState = STATE_RUNNING;
		entityManager.resume();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {

		update(delta);
		draw(delta);
	}

	public void update(float delta)
	{
		switch (currentState) {
		case STATE_READY:
			updateReady();
			break;
		case STATE_RUNNING:
			updateRunning(delta);
			break;
		case STATE_PAUSED:
			updatePaused();
			break;
		case STATE_OVER:
			updateGameOver();
			break;
		}

	}
	
	private void updateReady () {
		if (Gdx.input.justTouched()) {
			currentState = STATE_RUNNING;
			Gdx.graphics.setContinuousRendering(true);
		}
	}
	
	private void updateRunning (float deltaTime) {
		if (Gdx.input.justTouched()) {
			camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			if (pauseBounds.contains(touchPoint.x,touchPoint.y)) {
				TextureManager.playSound(MainGame.Sounds.get(Config.SoundsClick));
				currentState = STATE_PAUSED;
				return;
			}
		}
		
		entityManager.update();
	}
	
	private void updatePaused () {
		if (Gdx.input.justTouched()) {
			camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			if (pauseBounds.contains(touchPoint.x,touchPoint.y)) {
				TextureManager.playSound(MainGame.Sounds.get(Config.SoundsClick));
				currentState = STATE_RUNNING;
				return;
			}
		}
	}
	
	private void updateGameOver () {
		if (Gdx.input.justTouched()) {
//			if (game.actionResolver.getSignedInGPGS()) {
//				game.actionResolver.submitScoreGPGS(world.score);
//				if (world.score >= 100) game.actionResolver.unlockAchievementGPGS("CgkI6574wJUXEAIQAQ");
//				if (world.score >= 200) game.actionResolver.unlockAchievementGPGS("CgkI6574wJUXEAIQAg");
//				if (world.score >= 300) game.actionResolver.unlockAchievementGPGS("CgkI6574wJUXEAIQBA");
//				if (world.score >= 400) game.actionResolver.unlockAchievementGPGS("CgkI6574wJUXEAIQBQ");
//				if (world.score >= 500) game.actionResolver.unlockAchievementGPGS("CgkI6574wJUXEAIQBg");
//			}
			//game.setScreen(new MainMenuScreen(game));
		}
	}
	
	public void draw(float delta)
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.update();
		mainGame.spriteBatch.setProjectionMatrix(camera.combined);
		
		mainGame.spriteBatch.begin();
		entityManager.render(mainGame.spriteBatch);
		switch (currentState) 
		{
		case STATE_READY:
			drawReady();
			break;
		case STATE_RUNNING:
			drawRunning();
			break;
		case STATE_PAUSED:
			drawPaused();
			break;
		case STATE_OVER:
			drawGameOver();
			break;
		}
		mainGame.spriteBatch.end();
	}
	
	private void drawReady () 
	{
		mainGame.spriteBatch.draw(TextureManager.READY_MENU, 228, MainGame.HEIGHT - 318);
	}
	
	private void drawRunning () {
		mainGame.spriteBatch.draw(TextureManager.RESUME, 378, MainGame.HEIGHT - 45);
	}

	private void drawPaused () {
		mainGame.spriteBatch.draw(TextureManager.PAUSE, 378, MainGame.HEIGHT - 45);
	}
	
	private void drawGameOver () {
//		batcher.draw(Assets.gameOver, 160 - 160 / 2, 240 - 96 / 2, 160, 96);
//		float scoreWidth = Assets.font.getBounds(scoreString).width;
//		Assets.font.draw(batcher, scoreString, 160 - scoreWidth / 2, 480 - 20);
	}
	
	@Override
	public void show() {
		pauseBounds = new Rectangle(378, MainGame.HEIGHT - 45,39,37);
	}
}
