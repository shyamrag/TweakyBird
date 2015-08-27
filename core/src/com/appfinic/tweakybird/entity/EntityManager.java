package com.appfinic.tweakybird.entity;

import java.util.ArrayList;

import com.appfinic.tweakybird.MainGame;
import com.appfinic.tweakybird.TextureManager;
import com.appfinic.tweakybird.constants.Config;
import com.appfinic.tweakybird.screen.GameOverScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class EntityManager {

	private static final String LOG = null;
	public static int GAME_RUNNING = 1;
	public static int GAME_OVER = 2;
	public static int BIRD_HIT = 3;
	
	public static int STATE = 0;
	private final Array<Entity> entities = new Array<Entity>();
	private final Bird bird;
	//private final Background bckgrnd;
	private final BottomBackground bottombckgrnd;
	private final Wall wall1;
	private final Wall wall2;
	private final Wall wall3;
	private final Coin coin;
	private long score = 0;
	private long coinCollected = 0;
	private int level = 0;
	private float lowerBound;
	private float stateTime; 
	private String scoreStr;
	private String coinCollectedStr;
	private float verticalSpeed;
	BitmapFont bitmapFontTotalScore;
	BitmapFont bitmapFontLevel;
	MainGame mainGame;
	private Stage stage;
	
	Animation birdAnimation;
	Animation coinAnimation;
	TextureRegion[] birdRegions;
	TextureRegion[] coinRegions;
	
	float x = MainGame.WIDTH;
	float dist = (x - x/2);
	float z = dist/2;
	
	public long getScore() {
		return score;
	}
	
	public int getLevel() {
		return level;
	}
	
	public EntityManager(MainGame mainGame,int amount, OrthographicCamera camera) {
		this.mainGame = mainGame;
		//bckgrnd = new Background(new Vector2(0,0),new Vector2(0,0));
		bottombckgrnd = new BottomBackground(new Vector2(0,0),new Vector2(0,0)); 
		bird = new Bird(new Vector2(MainGame.WIDTH/2, MainGame.HEIGHT/2), new Vector2(0, 0));
		wall1 = new Wall(new Vector2(MainGame.WIDTH + 400, MainGame.HEIGHT/2), new Vector2(0, 0));
		wall2 = new Wall(new Vector2(MainGame.WIDTH + MainGame.WIDTH/2 + 400, MainGame.HEIGHT/2 + 150), new Vector2(0, 0));
		wall3 = new Wall(new Vector2(MainGame.WIDTH + MainGame.WIDTH + 400,  300), new Vector2(0, 0));
		coin = new Coin(new Vector2(MainGame.WIDTH + 600, MainGame.HEIGHT/2), new Vector2(0, 0));
		lowerBound = TextureManager.getTextureManager().getBOTTOM_BACKGROUND().getHeight();
		scoreStr = "SCORE:" + score;
		coinCollectedStr = "COINS:" + coinCollected;
		verticalSpeed = 50f;
		stage = new Stage();
		bitmapFontTotalScore = new BitmapFont(Gdx.files.internal("fonts/whitefont.fnt"),Gdx.files.internal("fonts/whitefont_0.png"),false);;
		bitmapFontLevel = new BitmapFont(Gdx.files.internal("fonts/jokerman.fnt"),Gdx.files.internal("fonts/jokerman_0.png"),false);;
		
		TextureAtlas atlas = new TextureAtlas("ui/button.pack");
		birdRegions = new TextureRegion[] {
				atlas.findRegion("bird1"), atlas.findRegion("bird2"),
				atlas.findRegion("bird3"),atlas.findRegion("bird4"),
				atlas.findRegion("bird5"),atlas.findRegion("bird4"),
				atlas.findRegion("bird3"),atlas.findRegion("bird2"),
				atlas.findRegion("bird1")};
		
		coinRegions = new TextureRegion[]{
				atlas.findRegion("coin1"),
				atlas.findRegion("coin2"),
				atlas.findRegion("coin3"),
				atlas.findRegion("coin4"),
				atlas.findRegion("coin5"),
				atlas.findRegion("coin6"),
		};
		
		birdAnimation = new Animation(0.02f,birdRegions);
		coinAnimation = new Animation(0.25f,coinRegions);
		
		stateTime = 0f;
		bird.setCurrentFrame(birdAnimation.getKeyFrame(stateTime, true));
		coin.setCurrentFrame(coinAnimation.getKeyFrame(stateTime, true));
		
		for (int i = 0; i <= 3; i++) {
			float y = MathUtils.random(TextureManager.getTextureManager().getBOTTOM_BACKGROUND().getHeight(), MainGame.HEIGHT - TextureManager.getTextureManager().getCOIN().getHeight());
			Coin coin = new Coin(new Vector2(x + 400 + z + dist*i, y), new Vector2(0, 0));
			coin.setCurrentFrame(coinAnimation.getKeyFrame(stateTime, true));
			entities.add(coin);			
		}
		
		STATE = GAME_RUNNING;
		//stage.addActor(bird);
	}
	
	public void update() {
		for (Entity e : entities)
			e.update();
		for (Coin c: getCoins())
		{
			c.setCurrentFrame(coinAnimation.getKeyFrame(stateTime, true)); 
			if(coinCollected(c) || c.pos.x + c.sprite.getWidth() < 0)
			{
				entities.removeValue(c, false);
				entities.add(addCoin());	
			}
		}
		
		checkCollisionsAndupdateScoreAndLevel();
		
		//stage.act();
		bird.update();
		//bckgrnd.update();
		bottombckgrnd.update();
		wall1.update();
		wall2.update();
		wall3.update();
        stateTime += Gdx.graphics.getDeltaTime();         
        bird.setCurrentFrame(birdAnimation.getKeyFrame(stateTime, true)); 
        
	}
	
	private ArrayList<Coin> getCoins() {
		ArrayList<Coin> ret = new ArrayList<Coin>();
		for (Entity e : entities)
			if (e instanceof Coin)
				ret.add((Coin)e);
		return ret;
	}

	private Coin addCoin(){
		float y = MathUtils.random(TextureManager.getTextureManager().getBOTTOM_BACKGROUND().getHeight(), MainGame.HEIGHT - TextureManager.getTextureManager().getCOIN().getHeight());
		Coin coin = new Coin(new Vector2(x + dist , y), new Vector2(0, 0));
		coin.setCurrentFrame(coinAnimation.getKeyFrame(stateTime, true));
		return coin;
	}
	
	private boolean coinCollected(Coin c) 
	{
		boolean collected =  false;
		
		if(bird.getBounds().overlaps(c.getBounds()))
		{
			collected = true;
			coinCollected+=10;
			coinCollectedStr = "COINS:" + coinCollected;
		}
		return collected;
	}
	
	private void checkCollisionsAndupdateScoreAndLevel() {
		if(checkCollisionWithUpperOrLowerBounds() || checkCollisionWithWalls()){
			if(STATE == GAME_OVER)
				mainGame.setScreen(new GameOverScreen(mainGame));
			STATE = BIRD_HIT;
			wall1.setHorinzontalSpeed(0);
			wall2.setHorinzontalSpeed(0);
			wall3.setHorinzontalSpeed(0);
			bird.setHorinzontalSpeed(0);
			bottombckgrnd.setHorinzontalSpeed(0);
			for (Coin c: getCoins())
			{
				c.setHorinzontalSpeed(0);
			}
			
		}	
		
		if(level != Wall.getLevel())
		{
			level = Wall.getLevel();
			MainGame.Sounds.get(Config.SoundsScore).play();
			if (level > 0) {
				scoreStr = "Score:" + level;

				if (level > 6) {
					Wall.setVerticalSpeed(verticalSpeed);
					Wall.setWallMovementStatus(true);
				}
				
				if(level % 2 == 0 && verticalSpeed <=120) verticalSpeed+=5;
			}
		}
	}

	private boolean checkCollisionWithWalls() {
		if(Math.abs(bird.pos.x - Wall.getCurrentWall().pos.x) < bird.sprite.getWidth()/2 + 10
				&& Math.abs((bird.pos.y + bird.sprite.getHeight()/2) - Wall.getCurrentWall().pos.y) > Wall.getWallGap())
		{
			if(STATE == GAME_RUNNING)
				MainGame.Sounds.get(Config.SoundsHit).play();
			return true;
		}
		return false;
	}

	private boolean checkCollisionWithUpperOrLowerBounds() {	
		if(((bird.getPosition().y) < lowerBound || (bird.getPosition().y + bird.getBounds().height - 15) > MainGame.HEIGHT))
		{
			MainGame.Sounds.get(Config.SoundsHit).play();
			STATE = GAME_OVER;
			return true;
		}
		return false;
	}

	public void render(SpriteBatch sb) {
		sb.disableBlending();
		//bckgrnd.render(sb);
		sb.enableBlending();
		
		for (Entity e : entities)
		e.render(sb);
		
		wall1.render(sb);
		wall2.render(sb);
		wall3.render(sb);
		
		sb.disableBlending();
		bottombckgrnd.render(sb);
		sb.enableBlending();
		
	
		bird.render(sb);
		//stage.draw();
		bitmapFontLevel.setScale(1.5f, 1.5f);
		bitmapFontLevel.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		//bitmapFontLevel.draw(sb, levelStr, MainGame.WIDTH/2 - 2, MainGame.HEIGHT - 10);
		//bitmapFontTotalScore.setColor(1.0f, 1.0f, 200.0f, 1.0f);
		bitmapFontTotalScore.draw(sb, scoreStr, 25, MainGame.HEIGHT - 20); 
		bitmapFontTotalScore.draw(sb, coinCollectedStr, 25, MainGame.HEIGHT - 40); 
		
	}	
	
	public void pause(){
		
	}
	
	public void resume(){
		
	}

	public void dispose() {
		bitmapFontLevel.dispose();
		bitmapFontTotalScore.dispose();
	}
}
