package cl.makinolas.atk;

import com.badlogic.gdx.Game;

import cl.makinolas.atk.screen.GameScreen;

public class Main extends Game {
	
	@Override
	public void create () {
	  setScreen(new GameScreen());
	}
  	
	
}
