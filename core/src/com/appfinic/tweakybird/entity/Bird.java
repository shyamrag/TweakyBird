package com.appfinic.tweakybird.entity;

import com.appfinic.tweakybird.TextureManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Bird extends Entity {

	private float gravity;
	float angle;
	boolean up = false;
	boolean down = false;
	
	public Bird(Vector2 pos, Vector2 direction) {
		super(TextureManager.getTextureManager().getBIRD(), pos, direction);
	}

	@Override
	public void update() {
		
		if (Gdx.input.isTouched()) 
		{
			if((EntityManager.STATE == EntityManager.GAME_RUNNING))
			{
				gravity=-250;
				rotation = 30;	
			}
			//up = true;
		}
//		if(up)
//		{
//			rotation = rotation + 150*Gdx.graphics.getDeltaTime();
//			if(rotation >=30)
//			{
//				up = false;
//				down = true;
//			}
//		}
//		else if(down)
//		{
//			rotation = rotation - 90*Gdx.graphics.getDeltaTime();
//			if(rotation <= -30)
//			{
//				down = false;
//			}
//		}
//		else if(rotation <= -1.0)
//		{
//			rotation = rotation + 60*Gdx.graphics.getDeltaTime();
//		}
//		else
//			rotation = 0;
		
		if(rotation != -30) 
			rotation--;
		else
			rotation = 0;
		
//		if(EntityManager.STATE == EntityManager.BIRD_HIT)
//		{
//			float currentDistance = direction.y - TextureManager.BOTTOM_BACKGROUND.getHeight();
//			direction.y = direction.y - currentDistance;
//		}

			gravity+=(600*Gdx.graphics.getDeltaTime());
			direction.y = direction.y - gravity;
			setDirection(direction.x,direction.y);
			pos.add(direction);
	}
}
