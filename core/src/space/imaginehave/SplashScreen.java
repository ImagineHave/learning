package space.imaginehave;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;

public class SplashScreen implements Screen {
	
	Texture splashTexture;
	Sprite splashSprite;
	SpriteBatch batch;
	BitmapFont imagineHavefont;
	int backgroundFadeLifeTime = 2;
	int imagineHaveFadeLifeTime = 3;
	int asteroidsFadeLifeTime = 4;
	private int fadeOutLifeTime = 2;
	private FirstGameClass game;
	private float elapsed;
	private BitmapFont asteroidsFont;
	
	public SplashScreen(FirstGameClass game) {
		this.game = game;
	}
	
	@Override
	public void show() {
		splashTexture = new Texture(Gdx.files.internal("data/space.png"));
		splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		splashSprite = new Sprite(splashTexture);
		
		batch = new SpriteBatch();
		imagineHavefont = new BitmapFont(Gdx.files.internal("data/font.fnt"));
		asteroidsFont = new BitmapFont(Gdx.files.internal("data/androids.fnt"));
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		elapsed += delta;
		float backgroundProgress = Math.min(1f, elapsed/backgroundFadeLifeTime);
		float imagineHaveProgress = Math.min(0.8f, (elapsed-2)/backgroundFadeLifeTime);
		float asteroidsProgress = Math.min(1f, (elapsed-4)/backgroundFadeLifeTime);
		Color textColor = Color.valueOf("F3673A");
		imagineHavefont.setColor(textColor.r, textColor.g, textColor.b, imagineHaveProgress);
		asteroidsFont.setColor(textColor.r, textColor.g, textColor.b, asteroidsProgress);
		float fadeOutProgress = Math.max(0f, 1 - Math.min(1f, (elapsed - 6) / fadeOutLifeTime));
		float fadeOutImagineHaveProgress = Math.max(0f, 0.8f - Math.min(0.8f, (elapsed - 6) / fadeOutLifeTime));
		
		if (asteroidsProgress == 1 ) {
			//Fade out
			imagineHavefont.setColor(textColor.r, textColor.g, textColor.b, fadeOutImagineHaveProgress);
			asteroidsFont.setColor(textColor.r, textColor.g, textColor.b, fadeOutProgress);
			
			game.batch.begin();
			splashSprite.draw(game.batch, Interpolation.fade.apply(backgroundProgress));
			imagineHavefont.draw(game.batch, "Imagine have", Gdx.graphics.getWidth() /2 - 350, Gdx.graphics.getHeight() / 2 + 100);
			asteroidsFont.draw(game.batch, "ASTEROIDS", Gdx.graphics.getWidth() / 2 - 300, Gdx.graphics.getHeight() / 2 + 50);
			
			game.batch.end();
			
			if (fadeOutProgress == 0) { //fading finished
				game.setScreen(new MainMenuScreen(game));
			}
		} else {
			imagineHavefont.setColor(textColor.r, textColor.g, textColor.b, imagineHaveProgress);
			asteroidsFont.setColor(textColor.r, textColor.g, textColor.b, asteroidsProgress);
			game.batch.begin();
			splashSprite.draw(game.batch, Interpolation.fade.apply(backgroundProgress));
			imagineHavefont.draw(game.batch, "Imagine have", Gdx.graphics.getWidth() /2 - 350, Gdx.graphics.getHeight() / 2 + 100);
			asteroidsFont.draw(game.batch, "ASTEROIDS", Gdx.graphics.getWidth() / 2 - 300, Gdx.graphics.getHeight() / 2 + 50);
			
			game.batch.end();
		}

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

	}

	@Override
	public void dispose() {
		batch.dispose();
		imagineHavefont.dispose();
		asteroidsFont.dispose();
		splashTexture.dispose();
	}

}
