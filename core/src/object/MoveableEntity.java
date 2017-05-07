package object;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class MoveableEntity extends Entity {
	
	Vector2 direction;
	float speed;
	public float rotation;
	
	public MoveableEntity(Vector2 position, int width, int height, Vector2 direction, float speed, float rotation) {
		super(position, width, height);
		this.direction = direction;
		this.speed = speed;
		this.rotation = rotation;
		this.setBounds(new Rectangle(position.x, position.y, width, height));
	}
	
	public Vector2 getDirection() {
		return direction;
	}

	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	protected void checkInsideScreen() {
		if (position.x > 800) {
			position.x = -width;
		} else if (position.x + width < 0) {
			position.x = 800;
		}
		
		if(position.y > 480) {
			position.y = -height;
		} else if (position.y + height < 0) {
			position.y = 480;
		}
	}
}
