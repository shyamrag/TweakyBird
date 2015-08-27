package com.appfinic.tweakybird;

import java.util.HashMap;

import com.appfinic.tweakybird.constants.Config;
import com.appfinic.tweakybird.screen.GameOverScreen;
import com.appfinic.tweakybird.screen.MainMenuScreen;
import com.appfinic.tweakybird.screen.SplashScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GLTexture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class MainGame extends Game {
	
	private static final String LOG = MainGame.class.getSimpleName();
	public static final int WIDTH=800,HEIGHT=480;
	
	public ActionResolver actionResolver;
	public GameOverScreen gameOverScreen;
	public MainMenuScreen mainMenuScreen;
	public SplashScreen splashScreen;
	public SpriteBatch spriteBatch;
	public BitmapFont font;
	public static HashMap<String, Sound> Sounds = new HashMap<String, Sound>();
	
	public TextureAtlas atlas;
	public AssetManager manager = new AssetManager();

	public MainGame(ActionResolver actionResolver) {
		super();
		this.actionResolver = actionResolver;
	}

	@Override
	public void create() {		
		Gdx.app.log( MainGame.LOG, "Creating Game" );
		GLTexture.setEnforcePotImages(false);
		//Loading all the Textures
		TextureManager.loadTextures();
		
		spriteBatch = new SpriteBatch();

		Sounds.put(Config.SoundsHit, Gdx.audio.newSound(Gdx.files.internal("sounds/sfx_hit.mp3")));
		Sounds.put(Config.SoundsScore, Gdx.audio.newSound(Gdx.files.internal("sounds/sfx_point.mp3")));
		Sounds.put(Config.SoundsJump, Gdx.audio.newSound(Gdx.files.internal("sounds/sfx_wing.mp3")));
		Sounds.put(Config.SoundsClick, Gdx.audio.newSound(Gdx.files.internal("sounds/click.wav")));
		
		manager.load("ui/button.pack", TextureAtlas.class);
		manager.finishLoading();
		atlas = manager.get("ui/button.pack", TextureAtlas.class);
		
		splashScreen = new SplashScreen(this);
		setScreen(splashScreen);
		
		Gdx.app.log( MainGame.LOG, "WIDTH::"+WIDTH+"\nHEIGHT::"+HEIGHT );

	}
	
	
	@Override
	public void dispose() {
		for (String key : Sounds.keySet()) {
			Sounds.get(key).dispose();
		}
		spriteBatch.dispose();
		manager.dispose();
		TextureManager.unloadTextures();
	}

	@Override
	public void render() {		
		//Gdx.app.log( MainGame.LOG, "Rendering The Game" );
		super.render();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {
		Gdx.app.log( MainGame.LOG, "Pausing game" );

	}

	@Override
	public void resume() {
		 Gdx.app.log( MainGame.LOG, "Resuming game" );

	}

}
