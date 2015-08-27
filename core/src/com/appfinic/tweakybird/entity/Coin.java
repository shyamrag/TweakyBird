package com.appfinic.tweakybird.entity;

import com.appfinic.tweakybird.TextureManager;
import com.badlogic.gdx.math.Vector2;


public class Coin extends Entity {

	public Coin(Vector2 pos,Vector2 direction) {
		super(TextureManager.getTextureManager().getCOIN(), pos, direction);
	}

	@Override
	public void update() {
		setDirection(getHorinzontalSpeed(), 0);
		pos.add(direction);
	}
	
}
