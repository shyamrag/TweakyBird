package com.appfinic.tweakybird.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public abstract class Entity extends Image {
	
	Sprite sprite;
	protected TextureRegion currentFrame;
	protected Vector2 pos, direction;
	public float rotation;
	public float horizontalSpeed;
	
	public Entity(Sprite sprite, Vector2 pos, Vector2 direction) {
		this.sprite =  sprite;
		this.pos = pos;
		this.direction = direction;
		horizontalSpeed = -270f;
	}
	
	public void setCurrentFrame(TextureRegion currentFrame){
		this.currentFrame = currentFrame;
	}
	
	public abstract void update();
	
	public float getHorinzontalSpeed(){
		return horizontalSpeed;
	}
	
	public void setHorinzontalSpeed(float horizontalSpeed){
		this.horizontalSpeed = horizontalSpeed;
	}
	
	public void render(SpriteBatch sb) {
		sb.draw(currentFrame,pos.x, pos.y, 0, 0, currentFrame.getRegionWidth(), currentFrame.getRegionHeight(), 1, 1, rotation);
	}
	
	public Vector2 getPosition() {
		return pos;
	}
	
	public void setPosition(float x,float y)
	{
		pos.x=x;
		pos.y=y;
	}
	public Rectangle getBounds() {
		return new Rectangle(pos.x, pos.y, sprite.getRegionWidth(), sprite.getRegionHeight());
	}
	
	public void setDirection(float x, float y) {
		direction.set(x, y);
		direction.scl(Gdx.graphics.getDeltaTime());
	}
	
}
