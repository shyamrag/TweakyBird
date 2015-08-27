package com.appfinic.tweakybird.screen;

import com.appfinic.tweakybird.MainGame;
import com.appfinic.tweakybird.Settings;
import com.appfinic.tweakybird.TextureManager;
import com.appfinic.tweakybird.constants.Config;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MainMenuScreen implements Screen{

	private TextureAtlas atlas;
	private TextureRegion mainMenu;
	private Skin skin;
	private BitmapFont font;
	
	Label singlePlayer;
	Label multiPlayer;
	Label options;
	Label highscore;
	Label leaderboard;
	Label help;
	Label exit;
	
	Rectangle singlePlayerBounds;
	Rectangle multiPlayerBounds;
	Rectangle optionsBounds;
	Rectangle highscoreBounds;
	Rectangle leaderboardBounds;
	Rectangle helpBounds;
	Rectangle exitBounds;
	
	Vector3 touchpoint;
	
	private Sprite mainMenuBckgrnd;
	Texture bottomBckgrnd;
	
	private final MainGame mainGame;
	private OrthographicCamera camera;
	
	public MainMenuScreen(MainGame mainGame) {
		
		this.mainGame = mainGame;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, MainGame.WIDTH, MainGame.HEIGHT);
		touchpoint = new Vector3();
		Gdx.graphics.setContinuousRendering(false);
	}
	
 	public void update(float deltaTime){
		if(Gdx.input.justTouched()){
			camera.unproject(touchpoint.set(Gdx.input.getX(),Gdx.input.getY(),0));
					
			if(singlePlayerBounds.contains(touchpoint.x,touchpoint.y))
			{
		        dispose();
		        TextureManager.playSound(MainGame.Sounds.get(Config.SoundsClick));
		        //if (!mainGame.actionResolver.getSignedInGPGS()) mainGame.actionResolver.loginGPGS();
	        	mainGame.setScreen(new GameScreen(mainGame));
			}
			
			if(multiPlayerBounds.contains(touchpoint.x,touchpoint.y))
			{
				 TextureManager.playSound(MainGame.Sounds.get(Config.SoundsClick)); 
			}
			
			if(optionsBounds.contains(touchpoint.x,touchpoint.y))
			{
				 TextureManager.playSound(MainGame.Sounds.get(Config.SoundsClick));
			}
			
			if(highscoreBounds.contains(touchpoint.x,touchpoint.y))
			{
				 TextureManager.playSound(MainGame.Sounds.get(Config.SoundsClick));
			}
			
			if(leaderboardBounds.contains(touchpoint.x,touchpoint.y))
			{
				 TextureManager.playSound(MainGame.Sounds.get(Config.SoundsClick));
			}
			
			if(helpBounds.contains(touchpoint.x,touchpoint.y))
			{
				 TextureManager.playSound(MainGame.Sounds.get(Config.SoundsClick)); 
			}
			
			if(exitBounds.contains(touchpoint.x,touchpoint.y))
			{
				 TextureManager.playSound(MainGame.Sounds.get(Config.SoundsClick));
			}
		}
	}
 	
	@Override
	public void render(float arg0) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        update(arg0);
        
        camera.update();
        mainGame.spriteBatch.setProjectionMatrix(camera.combined);

        mainGame.spriteBatch.begin();
        
        mainMenuBckgrnd.draw(mainGame.spriteBatch);
        mainGame.spriteBatch.draw(mainMenu,112,MainGame.HEIGHT - 400);
        mainGame.spriteBatch.draw(bottomBckgrnd,0,0);
        
        singlePlayer.draw(mainGame.spriteBatch, 1);
        multiPlayer.draw(mainGame.spriteBatch, 1);
        options.draw(mainGame.spriteBatch, 1);
        highscore.draw(mainGame.spriteBatch, 1);
        leaderboard.draw(mainGame.spriteBatch, 1);
        help.draw(mainGame.spriteBatch, 1);
        exit.draw(mainGame.spriteBatch, 1);
        
        mainGame.spriteBatch.end();
		
	}
	
	@Override
	public void dispose() {
		font.dispose();
		atlas.dispose();
		skin.dispose();
	}
	
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		Settings.save();
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
		
		atlas = new TextureAtlas("ui/button.pack");
		skin = new Skin(atlas);
		
		font = new BitmapFont(Gdx.files.internal("fonts/jokerman.fnt"),Gdx.files.internal("fonts/jokerman_0.png"),false);
		
		mainMenu =  atlas.findRegion("main_menu");
		mainMenuBckgrnd = new Sprite(new Texture("main_menu_bckgrnd.png"));
		
		LabelStyle labelStyle = new LabelStyle(font, Color.WHITE);
		labelStyle.background = skin.getDrawable("button_style");
		
		bottomBckgrnd = TextureManager.BOTTOM_BACKGROUND;
			
		singlePlayer = new Label("    Single Player", labelStyle);
		singlePlayer.setSize(130, 40);
		singlePlayer.setPosition(248, MainGame.HEIGHT - 112);
		singlePlayerBounds =  new Rectangle(248,MainGame.HEIGHT - 112,130,40);
		
		multiPlayer = new Label("    Multi Player", labelStyle);
		multiPlayer.setSize(130, 40);
		multiPlayer.setPosition(55, MainGame.HEIGHT - 146);
		multiPlayerBounds =  new Rectangle(55,MainGame.HEIGHT - 146,130,40);
		
		options = new Label("        Settings", labelStyle);
		options.setSize(130, 40);
		options.setPosition(248, MainGame.HEIGHT - 191);
		optionsBounds =  new Rectangle(248,MainGame.HEIGHT - 191,130,40);
		
		highscore = new Label("     Highscore", labelStyle);
		highscore.setSize(130, 40);
		highscore.setPosition(55, MainGame.HEIGHT - 218);
		highscoreBounds =  new Rectangle(55,MainGame.HEIGHT - 218,130,40);
		
		leaderboard = new Label("    LeaderBoard", labelStyle);
		leaderboard.setSize(130, 40);
		leaderboard.setPosition(248, MainGame.HEIGHT - 273);
		leaderboardBounds =  new Rectangle(248,MainGame.HEIGHT - 273,130,40);
		
		help = new Label("            Help", labelStyle);
		help.setSize(130, 40);
		help.setPosition(55, MainGame.HEIGHT - 312);
		helpBounds = new Rectangle(55,MainGame.HEIGHT - 312,130,40);
		
		exit = new Label("            Exit", labelStyle);
		exit.setSize(130, 40);
		exit.setPosition(248, MainGame.HEIGHT - 363);
		exitBounds = new Rectangle(248,MainGame.HEIGHT - 363,130,40);
		
		Gdx.graphics.requestRendering();
		
	}
}
