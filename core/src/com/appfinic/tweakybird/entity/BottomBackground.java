package com.appfinic.tweakybird.entity;

import com.appfinic.tweakybird.TextureManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class BottomBackground extends Entity{

	public static final String LOG = BottomBackground.class.getSimpleName();
	public BottomBackground(Vector2 pos, Vector2 direction) {
		super(TextureManager.getTextureManager().getBOTTOM_BACKGROUND(), pos, direction);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		Gdx.app.log( BottomBackground.LOG, "X position==>"+getPosition().x);
		if (getPosition().x <= -sprite.getWidth())
			setPosition(0,pos.y);
		setDirection(getHorinzontalSpeed(),0);
		pos.add(direction);	
		
	}
	
	public void render(SpriteBatch sb) {
		sb.draw(sprite, pos.x, pos.y);
		sb.draw(sprite, pos.x+sprite.getWidth(), pos.y);
	}

}
