package space.imaginehave;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

import space.imaginehave.screens.GameScreen;

public class MainMenuScreen implements Screen {

	private FirstGameClass game;
	private Texture spaceImage;
	private SpriteBatch batch;
	Stage stage;
	private Skin skin;
	private TextButton button;
	private float buttonheight = 100;
	private float buttonwidth = 200;
	private TextureAtlas atlas;
	private BitmapFont font;

	public MainMenuScreen(FirstGameClass game) {
		this.game = game;
		spaceImage = new Texture(Gdx.files.internal("data/space.png"));
		batch = new SpriteBatch();
		TextButtonStyle style = new TextButtonStyle();
		atlas = new TextureAtlas(Gdx.files.internal("data/button.pack"));
		font = new BitmapFont(Gdx.files.internal("data/font.fnt"));

		skin = new Skin();
		skin.addRegions(atlas);
		style.font = font;
		style.up = skin.getDrawable("buttonnormal");
		style.down = skin.getDrawable("buttonpressed");
		stage = new Stage();
		button = new TextButton("Start", style);
		
		button.setWidth(buttonwidth);
		button.setHeight(buttonheight);
		button.setX(Gdx.graphics.getWidth()/2-button.getWidth()/2);
		button.setY(Gdx.graphics.getHeight()/2-button.getHeight()/2);
		
	}
	
	@Override
	public void show() {
		button.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new GameScreen(game));
			}
		});
		
		stage.addActor(button);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		
		game.batch.begin();
		game.batch.disableBlending();
		game.batch.draw(spaceImage,0,0);
		game.batch.enableBlending();
		stage.draw();
		game.batch.end();

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
		spaceImage.dispose();
		stage.dispose();
		atlas.dispose();
		skin.dispose();
		font.dispose();
	}

}
