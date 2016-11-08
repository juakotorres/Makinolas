package cl.makinolas.atk.stages;

import cl.makinolas.atk.gamemodes.SurvivalModeStage;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import cl.makinolas.atk.actors.Background;
import cl.makinolas.atk.actors.Title;
import cl.makinolas.atk.minigames.MinigameStage;
import cl.makinolas.atk.screen.GameScreen;
import cl.makinolas.atk.screen.MenuScreen;

public class MiniGameSelect extends AbstractStage {
	private static Skin sharedSkin = new Skin(Gdx.files.internal("Data/uiskin.json"));

	
	public MiniGameSelect(Viewport v, GameScreen gameScreen, final Game myGame) {
		super(v);
	    addActor(new Title("Background/atk.png", 320, 350));
	    addActor(new Background("Background/MenuBackground.jpg", getCamera()));


	    // Buttons
	    TextButton menuButton = new TextButton("Back to menu", sharedSkin);
	    TextButton pikachuRunnerButton = new TextButton("Pikachu Runner", sharedSkin);
	    TextButton survivalButton = new TextButton("Survival", sharedSkin);

	    // MiniGames
	    Label miniGamesLabel = new Label("Mini Games", sharedSkin);
	    miniGamesLabel.setColor(Color.BLACK);


	    // Positions
	    menuButton.setPosition(500, 50);
	    miniGamesLabel.setPosition(200, 285);

	    pikachuRunnerButton.setPosition(200, 250);
	    survivalButton.setPosition(200, 220);
	    
	    // Listeners
	    menuButton.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	  	    	MenuScreen menuScreen = new MenuScreen(myGame);
	  	    	myGame.setScreen(menuScreen);	  
	        }
	    });
	    
	    pikachuRunnerButton.addListener(new ClickListener() {
											@Override
											public void clicked(InputEvent event, float x, float y) {
												GameScreen gameScreen = new GameScreen(myGame);
												gameScreen.setStage(new MinigameStage(new FitViewport(640, 480), gameScreen, myGame));
												myGame.setScreen(gameScreen);
											}
										});
	    
	    survivalButton.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	//setStage( survival...)
					GameScreen gameScreen = new GameScreen(myGame);
					gameScreen.setStage(new SurvivalModeStage(new FitViewport(640,480),gameScreen,myGame));
					myGame.setScreen(gameScreen);
	        }
	    });
	    

	    
	    
	    // Add to screen
	    addActor(menuButton);
	    addActor(miniGamesLabel);
	    addActor(pikachuRunnerButton);
	    addActor(survivalButton);	    
	}

	@Override
	public void changeCamera(float x, float y) {
		// Does nothing here
	}
	

}
