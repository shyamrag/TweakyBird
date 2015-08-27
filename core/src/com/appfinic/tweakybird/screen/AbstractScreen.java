package com.appfinic.tweakybird.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class AbstractScreen implements Screen{

	public abstract void create();
	
	public abstract void update();
	
	public abstract void resize(int width, int height);
	
	public abstract void dispose();
	
	public abstract void pause();
	
	public abstract void resume();
	
	public abstract void render(SpriteBatch batch);
	
}
