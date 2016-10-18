package cl.makinolas.atk.stages;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import cl.makinolas.atk.actors.Title;
import cl.makinolas.atk.screen.GameScreen;
import cl.makinolas.atk.screen.MenuScreen;

public class OptionsStage extends Stage {
	private Game myGame;
	
	public OptionsStage(Viewport v, GameScreen gameScreen, Game myGame) {
		super(v);
		this.myGame = myGame;
		
	    addActor(new Title("Background/atk.png", 320, 350 ));
	    
	    TextButton menuButton = new TextButton("Back to menu",  new Skin(Gdx.files.internal("Data/uiskin.json")));
	    menuButton.setPosition(500, 50);
	    menuButton.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	          MainMenu();
	        }
	    });
	    
	    addActor(menuButton);
	    
	}
	
	protected void MainMenu() {
	    MenuScreen menuScreen = new MenuScreen(myGame);
	    myGame.setScreen(menuScreen);
	  }
}
