package cl.makinolas.atk.stages;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import cl.makinolas.atk.actors.Title;
import cl.makinolas.atk.screen.GameScreen;
import cl.makinolas.atk.screen.MenuScreen;

public class CorruptSAVStage extends AbstractStage {
	private Skin sharedSkin = new Skin(Gdx.files.internal("Data/uiskin.json"));
	
	public CorruptSAVStage(Viewport v, GameScreen gameScreen, final Game myGame) {
		super(v);
		
		addActor(new Title("Background/atk.png", 320, 350));
	    
	    // Buttons
	    TextButton menuButton = new TextButton("Back to menu", sharedSkin);
	    
	    // Labels
	    Label warningLabel = new Label("Corrupt .SAV file :-(", sharedSkin);
	    
	    // Positions
	    menuButton.setPosition(500, 50);
	    warningLabel.setPosition(255, 200);
	    
	    // Listeners
	    menuButton.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	  	    	MenuScreen menuScreen = new MenuScreen(myGame);
	  	    	myGame.setScreen(menuScreen);	  
	        }
	    });
	    
	    // Add to screen
	    addActor(menuButton);
	    addActor(warningLabel);
	}

	@Override
	public void changeCamera(float x, float y) {
		// TODO Auto-generated method stub

	}

}
