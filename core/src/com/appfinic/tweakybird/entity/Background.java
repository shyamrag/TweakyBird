package com.appfinic.tweakybird.entity;

import com.appfinic.tweakybird.TextureManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Background extends Entity{
	public static final String LOG = Background.class.getSimpleName();
	//public static float x;
	public Background(Vector2 pos, Vector2 direction) {
		super(TextureManager.getTextureManager().getBACKGROUND(), pos, direction);
		//texture.setScale(MainGame.WIDTH, MainGame.HEIGHT);
	}

	@Override
	public void update() {
		Gdx.app.log( Background.LOG, "X position==>"+getPosition().x);
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
