package com.appfinic.tweakybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TextureManager {
    
	private static TextureManager textureManager ;
	private static Texture BIRD              ;
	private static Texture WALL              ;
	private static Texture BACKGROUND        ;
	public static Texture BOTTOM_BACKGROUND  ;
	private static Texture COIN              ;
	private static Texture GAME_OVER         ;
	private static Texture GAME_WON          ;
	public static Texture START_BCKGRND      ;
	public static Texture START_LABEL        ;
	public static Texture READY_MENU         ;
	public static Texture PAUSE              ;
	public static Texture RESUME             ;
	
	private TextureManager(){
	};
	
	public static void loadTextures()
	{	
		START_BCKGRND     = new Texture(Gdx.files.internal("start_bckgrnd.png"));
		START_BCKGRND.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		START_LABEL     = new Texture(Gdx.files.internal("start_label.png"));
		START_LABEL.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		READY_MENU = new Texture(Gdx.files.internal("ready_menu.png"));
		READY_MENU.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		PAUSE = new Texture(Gdx.files.internal("pause.png"));
		PAUSE.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		RESUME = new Texture(Gdx.files.internal("resume.png"));
		RESUME.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		GAME_OVER         = new Texture(Gdx.files.internal("gameover.png"));
		GAME_OVER.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		GAME_WON          = new Texture(Gdx.files.internal("gamewon.png" ));
		GAME_WON.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		BIRD              = new Texture(Gdx.files.internal("newbird"  + "_mdpi_1" + ".png" ));
		BIRD.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		WALL              = new Texture(Gdx.files.internal("new_wall" + "_mdpi" + ".png" ));
		WALL.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		BACKGROUND        = new Texture(Gdx.files.internal("bckgrnd"  + "_mdpi" + ".png" ));
		BACKGROUND.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		BOTTOM_BACKGROUND = new Texture(Gdx.files.internal("bottom_bckgrnd" + "_mdpi" + ".png"));
		BOTTOM_BACKGROUND.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		COIN              = new Texture(Gdx.files.internal("new_coin" + "_mdpi_1" + ".png" ));
		COIN.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
	}
	public static TextureManager getTextureManager()
	{
	   if(null == textureManager)
	   {
		   textureManager = new TextureManager();
	   }
	   return textureManager;
	}
	
	public void setResSuffix(String resSuffix) {

	}
	
	public void setBIRD(Texture bIRD) {
		BIRD = bIRD;
	}
	public void setWALL(Texture wALL) {
		WALL = wALL;
	}
	public void setBACKGROUND(Texture bACKGROUND) {
		BACKGROUND = bACKGROUND;
	}
	public void setBOTTOM_BACKGROUND(Texture bOTTOM_BACKGROUND) {
		BOTTOM_BACKGROUND = bOTTOM_BACKGROUND;
	}
	public void setCOIN(Texture cOIN) {
		COIN = cOIN;
	}
	public void setGAME_OVER(Texture gAME_OVER) {
		GAME_OVER = gAME_OVER;
	}
	public void setGAME_WON(Texture gAME_WON) {
		GAME_WON = gAME_WON;
	}
	public Sprite getBIRD() {
		return new Sprite(BIRD);
	}

	public Sprite getWALL() {
		return new Sprite(WALL);
	}

	public Sprite getBACKGROUND() {
		return new Sprite(BACKGROUND);
	}

	public Sprite getBOTTOM_BACKGROUND() {
		return new Sprite(BOTTOM_BACKGROUND);
	}

	public Sprite getCOIN() {
		return new Sprite(COIN);
	}

	public Sprite getGAME_OVER() {
		return new Sprite(GAME_OVER);
	}

	public Sprite getGAME_WON() {
		return new Sprite(GAME_WON);
	}
	
	public static void unloadTextures(){
		Gdx.app.log("Info", "Unloading all Textures.");
		GAME_WON.dispose();
		GAME_OVER.dispose();
		COIN.dispose();
		BOTTOM_BACKGROUND.dispose();
		BACKGROUND.dispose();
		WALL.dispose();
		BIRD.dispose();	
		START_BCKGRND.dispose();
		START_LABEL.dispose();
	}
	

	public static void playSound (Sound sound) {
		if (Settings.soundEnabled) sound.play(1);
	}
}
