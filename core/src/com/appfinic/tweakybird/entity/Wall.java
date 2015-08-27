package com.appfinic.tweakybird.entity;

import com.appfinic.tweakybird.MainGame;
import com.appfinic.tweakybird.TextureManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;


public class Wall extends Entity {
	public  static final String LOG = Wall.class.getSimpleName();
	private static int wallGap = 50;
	private static float upperBound;
	private static float lowerBound;
	private static float verticalSpeed = 0f;
	private static int level = -1;
	private static Wall currentWall;
	private static boolean wallMoving = false;
	public Wall(Vector2 pos, Vector2 direction) {
		super(TextureManager.getTextureManager().getWALL(), pos, direction);
		currentWall = this;
		level = -1;
		wallMoving = false;
		verticalSpeed = 0f;
		wallGap = 50;
	}
	
	public static void setWallMovementStatus(boolean value){
		wallMoving = value;
	}
	public static Wall getCurrentWall(){
		return currentWall;
	}
	public static int getWallGap()
	{
		return wallGap;
	}

	public static int getLevel()
	{
		return level;
	}
	@Override
	public void update() {
		
		if (currentWall!=this && pos.x + sprite.getWidth() >= MainGame.WIDTH / 2
				&& pos.x + sprite.getWidth() <= MainGame.WIDTH) {
			currentWall = this;
			level++;
		}
		if (pos.x + sprite.getWidth() <= 0) {
			lowerBound = TextureManager.getTextureManager().getBOTTOM_BACKGROUND().getHeight() + wallGap + 25;
			upperBound = MainGame.HEIGHT - 50;
			float y = MathUtils.random(lowerBound, upperBound);
			pos.set(MainGame.WIDTH + MainGame.WIDTH/2 - sprite.getWidth() , y);
		}
		if (wallMoving) {
			if((pos.y ) > upperBound ||( pos.y ) < lowerBound)
				verticalSpeed*=-1;	
		}
		setDirection(getHorinzontalSpeed(), verticalSpeed);
		pos.add(direction);
	}
	
	public static void setVerticalSpeed(float i) {
			verticalSpeed = i;	
	}

	public void render(SpriteBatch sb) {
		sb.draw(sprite, pos.x, pos.y + wallGap);
		sb.draw(sprite, pos.x, pos.y - (sprite.getHeight() + wallGap));
	}
	
}
