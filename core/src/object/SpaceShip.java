package object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class SpaceShip extends MoveableEntity {

	private static final int DAMAGE_TIME_OUT_MILLIS = 30000;
	private static final float FRAME_DURATION = 1f/8f;
	private static final int firingDelay = 200;
	private static final int maxBulletsAllowed = 5;
	public boolean moveForward;
	public boolean rotateLeft;
	public boolean rotateRight;
	private float friction = 0.98f;
	public boolean firePressed;
	public Array<Bullet> bulletsFired;
	private long lastFiredTime;
	private TextureRegion [] animationFrames;
	private Animation<TextureRegion> moveAnimation;
	private float elapsedTime;
	private Animation<TextureRegion> idleAnimation;
	public int lives = 3;
	private long lastDamaged;
	private float elapsedSinceDamaged;

	public SpaceShip(Vector2 position, int width, int height, Vector2 direction, float speed, float rotation) {
		super(position, width, height, direction, speed, rotation);
		
		Texture shipTexture = new Texture(Gdx.files.internal("data/big_boss_sprites.png"));
		animationFrames = new  TextureRegion[2];
		animationFrames[0] = new TextureRegion(shipTexture, 190, 0, 351-190, 248);
		animationFrames[1] = new TextureRegion(shipTexture, 10, 0, 351-190, 248);
		bulletsFired = new Array<Bullet>();
		
		moveAnimation = new Animation<TextureRegion>(FRAME_DURATION, animationFrames);
		idleAnimation = new Animation<TextureRegion>(FRAME_DURATION*2, animationFrames);
	}

	@Override
	public void render(SpriteBatch batch) {
		elapsedTime += Gdx.graphics.getDeltaTime();
		if(moveForward) {
			batch.draw(moveAnimation.getKeyFrame(elapsedTime, true), position.x, position.y, width/2, height/2, width, height, 1, 1, rotation);
		} else {
			batch.draw(idleAnimation.getKeyFrame(elapsedTime, true), position.x, position.y, width/2, height/2, width, height, 1, 1, rotation);
		}
	}
	
	public void dispose() {
		
	}
	
	public void update() {
		elapsedSinceDamaged += Gdx.graphics.getDeltaTime();
		
		for(Bullet b: bulletsFired) {
			if(TimeUtils.millis() - b.createdTime > 5000) {
				bulletsFired.removeValue(b, true);
			}
		}
		
		if (rotateLeft) {
			rotation += 3;
		}
		if (rotateRight) {
			rotation -= 3;
		}
		Vector2 tmp = new Vector2(1,1);
		tmp.setAngle(rotation+90);
		
		if (moveForward) {
			if (direction.cpy().add(tmp.cpy().scl(Gdx.graphics.getDeltaTime() * 8 * speed)).len() < 4) {
				direction.add(tmp.scl(Gdx.graphics.getDeltaTime() * 8 * speed));
			}
		} else {
			direction.scl(friction);
		}
		
		if(firePressed && bulletsFired.size < maxBulletsAllowed) {
			if(TimeUtils.millis() - lastFiredTime > firingDelay) {
				bulletsFired.add(new Bullet(new Vector2(position.x + width/2, position.y + height/2), 10, 40, tmp.cpy(), 50, 0, TimeUtils.millis()));
				lastFiredTime = TimeUtils.millis();
			}
		}
		
		position.add(direction);

		checkInsideScreen();
		
		this.setBounds(new Rectangle(position.x, position.y, width, height));
	}
	
	public void damage() {
		if (!isRecentlyDamaged()) {
			lives--;
			System.out.println("Lost a life");
			lastDamaged = TimeUtils.millis();
			elapsedSinceDamaged = lastDamaged;
		}
		
	}
	
	public boolean isRecentlyDamaged(){
		return lastDamaged- elapsedSinceDamaged  < DAMAGE_TIME_OUT_MILLIS;
	}
}
