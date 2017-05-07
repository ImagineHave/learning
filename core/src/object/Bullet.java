package object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends MoveableEntity {

	private static final float FRAME_DURATION = 1f/6f;
	public long createdTime;
	private TextureRegion[] animationFrames;
	private Animation<TextureRegion> animation;
	private float elapsedTime;

	public Bullet(Vector2 position, int width, int height, Vector2 direction, float speed, float rotation, long createdTime) {
		super(position, width, height, direction, speed, rotation);
		this.createdTime = createdTime;
		animationFrames = new TextureRegion[6];
		Texture bulletTexture = new Texture(Gdx.files.internal("data/big_boss_sprites.png"));
		animationFrames[0] = new TextureRegion(bulletTexture,  390, 2, 10, 40);
		animationFrames[1] = new TextureRegion(bulletTexture,  474, 2, 10, 40);
		animationFrames[2] = new TextureRegion(bulletTexture,  490, 2, 10, 40);
		animationFrames[3] = new TextureRegion(bulletTexture,  574, 2, 10, 40);
		animationFrames[4] = new TextureRegion(bulletTexture,  590, 2, 10, 40);
		animationFrames[5] = new TextureRegion(bulletTexture,  674, 2, 10, 40);
		
		animation = new Animation<TextureRegion>(FRAME_DURATION, animationFrames);
	}

	@Override
	public void render(SpriteBatch batch) {
		elapsedTime += Gdx.graphics.getDeltaTime();
		batch.draw(animation.getKeyFrame(elapsedTime, true), position.x, position.y, width/2, height/2, width, height, 1, 1, direction.angle() - 90);
	}
	
	public void update() {
		position.add(direction.cpy().scl(Gdx.graphics.getDeltaTime() * 7 * speed));
		checkInsideScreen();
		this.setBounds(new Rectangle(position.x, position.y, width, height));
	}
	
	public void dispose() {
	}

}
