package space.imaginehave.screens;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import object.Asteroid;
import object.Bullet;
import object.SpaceShip;
import space.imaginehave.FirstGameClass;
import space.imaginehave.InputHandler;
import space.imaginehave.MainMenuScreen;

public class GameScreen implements Screen {

	private FirstGameClass game;
	private Array<Asteroid> asteroids;
	private OrthographicCamera camera;
	private Texture spaceImage;
	public SpaceShip spaceShip;
	private ParticleEmitter asteroidExplode;
	private Texture asteroidImage;
	private Sprite asteroidSprite;

	public GameScreen(final FirstGameClass game) {
		this.game = game;
		asteroids = new Array<Asteroid>();
		
		spaceShip = new SpaceShip(new Vector2(100, 100), 64, 64, new Vector2(0,0), 1, 0);
		Gdx.input.setInputProcessor(new InputHandler(spaceShip));
		
		spaceImage = new Texture(Gdx.files.internal("data/space.png"));
		asteroidExplode = new ParticleEmitter();
		loadAsteroidEmition();
		asteroidImage = new Texture(Gdx.files.internal("data/asteroid.png"));
		asteroidSprite = new Sprite(asteroidImage);
		asteroidExplode.setSprite(asteroidSprite);
		asteroids.add(createRandomAsteroid(64, 64));
		asteroids.add(createRandomAsteroid(64, 64));
		asteroids.add(createRandomAsteroid(64, 64));
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
	}


	private Asteroid createRandomAsteroid(int width, int height) {
		return createRandomAsteroid(width, height, new Vector2(MathUtils.random(800), MathUtils.random(480)));
	}
	
	private Asteroid createRandomAsteroid(int width, int height, Vector2 position) {
		return new Asteroid(new Vector2(position.x, position.y), width, height, new Vector2(MathUtils.random(-1, 1),MathUtils.random(-1, 1)), MathUtils.random(2,5), MathUtils.random(360));	
	}
	
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		spaceShip.update();
		
		for (Asteroid asteroid : asteroids) {
			asteroid.update();
		}
		
		for (Bullet b : spaceShip.bulletsFired) {
			b.update();
		}
		
		for(Asteroid a: asteroids) {
			for(Bullet b: spaceShip.bulletsFired) {
				if (b.getBounds().overlaps(a.getBounds())) {
					if(a.getWidth() > 16) {
						asteroids.add(createRandomAsteroid(a.getWidth()/2, a.getHeight()/2, a.getPosition()));
						asteroids.add(createRandomAsteroid(a.getWidth()/2, a.getHeight()/2, a.getPosition()));
					}
					asteroidExplode.setPosition(a.getPosition().x + a.getWidth()/2, a.getPosition().y + a.getHeight() /2);
					asteroidExplode.getScale().setHigh(a.getWidth()/64f);
					asteroidExplode.start(); 
					b.dispose();
					a.dispose();
					spaceShip.bulletsFired.removeValue(b, true);
					asteroids.removeValue(a, true);
				}
			}
			if(a.getBounds().overlaps(spaceShip.getBounds())) {
				spaceShip.damage();
				if(spaceShip.lives == 0) {
					endGame();
				}
			}
		}
		if (asteroids.size == 0 ) {
			victory();
		}
		
		game.batch.begin();
		game.batch.disableBlending();
		game.batch.draw(spaceImage,0,0);
		game.batch.enableBlending();
		asteroidExplode.draw(game.batch, Gdx.graphics.getDeltaTime());
		
		for(Asteroid a : asteroids) {
			a.render(game.batch);
		}
		
		for(Bullet b : spaceShip.bulletsFired) {
			b.render(game.batch);
		}
		
		spaceShip.render(game.batch);
		game.batch.end();
	}

	private void victory() {
		System.out.println("victory");
	}

	private void endGame() {
		System.out.println("Ended");
		game.setScreen(new MainMenuScreen(game));
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		spaceShip.dispose();
		spaceImage.dispose();
		for(Asteroid a : asteroids) {
			a.dispose();
		}
		for(Bullet b : spaceShip.bulletsFired) {
			b.dispose();
		}
		asteroidImage.dispose();
	}

	private void loadAsteroidEmition() {
		try {
			asteroidExplode.load(Gdx.files.internal("data/asteroidEmition").reader(2024));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
