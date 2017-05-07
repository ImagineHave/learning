package space.imaginehave;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

import object.SpaceShip;
import space.imaginehave.screens.GameScreen;

public class InputHandler implements InputProcessor{

	private SpaceShip spaceShip;

	public InputHandler(SpaceShip spaceShip){
		this.spaceShip = spaceShip;
	}
	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.W:
			spaceShip.moveForward = true;
			break;
		case Keys.A:
			spaceShip.rotatesLeft = true;
			break;
		case Keys.D:
			spaceShip.rotateRight = true;
			break;
		case Keys.SPACE:
			spaceShip.firePressed = true;
		default:
			break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Keys.W:
			spaceShip.moveForward = false;
			break;
		case Keys.A:
			spaceShip.rotateLeft = false;
			break;
		case Keys.D :
			spaceShip.rotateRight = false;
			break;
		case Keys.SPACE:
			spaceShip.firePressed = false;
			break;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
