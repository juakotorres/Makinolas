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
	    
	    // Buttons
	    TextButton menuButton = new TextButton("Back to menu",  new Skin(Gdx.files.internal("Data/uiskin.json")));
	    TextButton soundButton = new TextButton("*Sound - Slider bar",  new Skin(Gdx.files.internal("Data/uiskin.json")));
	    TextButton brightnessButton = new TextButton("*Brightness - Slider bar",  new Skin(Gdx.files.internal("Data/uiskin.json")));
	    TextButton windowedButton = new TextButton("*Windowed",  new Skin(Gdx.files.internal("Data/uiskin.json")));
	    TextButton fullscreenButton = new TextButton("*Full Screen",  new Skin(Gdx.files.internal("Data/uiskin.json")));
	    
	    // Positions
	    menuButton.setPosition(500, 50);
	    soundButton.setPosition(270, 200);
	    brightnessButton.setPosition(270, 160);
	    windowedButton.setPosition(270, 120);
	    fullscreenButton.setPosition(270, 80);
	    
	    // Listeners
	    menuButton.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	          MainMenu();
	        }
	    });
	    
	    windowedButton.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	          windowed();
	        }
	    });
	    
	    fullscreenButton.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	          fullscreen();
	        }
	    });
	    
	    // Add to screen
	    addActor(menuButton);
	    addActor(soundButton);
	    addActor(brightnessButton);
	    addActor(windowedButton);
	    addActor(fullscreenButton);
	    
	}
	
	// Listener methods
	
	protected void MainMenu() {
	    MenuScreen menuScreen = new MenuScreen(myGame);
	    myGame.setScreen(menuScreen);	    
	}
	
	protected void windowed() {
		Gdx.graphics.setWindowedMode(640, 480);
	}
	
	protected void fullscreen() {
		Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
	}
}
