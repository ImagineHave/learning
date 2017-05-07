package object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
	
	protected Vector2 position;
	int width, height;
	private Rectangle bounds;


	public Entity(Vector2 position, int width, int height) {
		super();
		this.position = position;
		this.width = width;
		this.height = height;
		this.bounds = new Rectangle(position.x, position.y, width, height);
	}
	
	public abstract void render(SpriteBatch batch);
	
	public Vector2 getPosition() {
		return position;
	}
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Rectangle getBounds() {
		return bounds;
	}
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
}
