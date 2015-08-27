package com.appfinic.tweakybird.screen;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.appfinic.tweakybird.MainGame;
import com.appfinic.tweakybird.TextureManager;
import com.appfinic.tweakybird.accessor.SpriteAccessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SplashScreen implements Screen{

	private OrthographicCamera camera;
	TweenManager tweenManager;
	Sprite spriteLabel;
	Sprite spriteBckgnrd;
	Texture texture;
	MainGame mainGame;
	
	public SplashScreen(MainGame mainGame){
		this.mainGame = mainGame;
		camera = new OrthographicCamera(MainGame.WIDTH,MainGame.HEIGHT);
		camera.position.set(MainGame.WIDTH/2,MainGame.HEIGHT/2,0f);
		
		//camera.setToOrtho(false, MainGame.WIDTH, MainGame.HEIGHT);	
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(256, 256, 256, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        camera.update();
        mainGame.spriteBatch.setProjectionMatrix(camera.combined);
        
        tweenManager.update(delta);
        mainGame.spriteBatch.begin();
        
        //spriteBckgnrd.draw(mainGame.spriteBatch); 
        spriteLabel.draw(mainGame.spriteBatch);

        mainGame.spriteBatch.end();
	
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		tweenManager = new TweenManager();
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		spriteLabel = new Sprite(TextureManager.START_LABEL);
		spriteLabel.setColor(Color.BLACK);
        spriteLabel.setPosition(MainGame.WIDTH/2 - 150, MainGame.HEIGHT/2 - 10);
		spriteBckgnrd =  new Sprite(TextureManager.START_BCKGRND);
		Tween.set(spriteLabel, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(spriteLabel, SpriteAccessor.ALPHA, 2).target(1).repeatYoyo(1, 0.5f).setCallback(new TweenCallback()
		{
			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				mainGame.setScreen(new MainMenuScreen(mainGame));
			}			
		}).start(tweenManager);
		
	}
}

