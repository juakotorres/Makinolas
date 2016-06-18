package cl.makinolas.atk;

import com.badlogic.gdx.Game;

import cl.makinolas.atk.screen.MenuScreen;

public class Main extends Game {
	
	@Override
	public void create () {
	  setScreen(new MenuScreen(this));
	}
  	
	
}
