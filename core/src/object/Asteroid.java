package object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Asteroid extends MoveableEntity {
	TextureRegion asteroidImage;
	float rotationRate = MathUtils.random(0.1f, 1f);

	public Asteroid(Vector2 position, int width, int height, Vector2 direction, float speed, float rotation) {
		super(position, width, height, direction, speed, rotation);
		asteroidImage = new TextureRegion(new Texture(Gdx.files.internal("data/asteroid.png")));
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(asteroidImage, position.x, position.y, width/2, height/2, width, height, 1 ,1 , rotation);
	}

	public void update() {
		position.add(direction.cpy().scl(Gdx.graphics.getDeltaTime() * 10 * speed));
		checkInsideScreen();
		this.setBounds(new Rectangle(position.x, position.y, width, height));
		rotation += rotationRate;
	}
	
	public void dispose(){
	}
	
}
