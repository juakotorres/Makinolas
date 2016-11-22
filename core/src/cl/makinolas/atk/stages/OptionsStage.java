package cl.makinolas.atk.stages;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import cl.makinolas.atk.actors.Background;
import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.Title;
import cl.makinolas.atk.audio.GDXMusicPlayer;
import cl.makinolas.atk.screen.GameScreen;
import cl.makinolas.atk.screen.MenuScreen;

public class OptionsStage extends AbstractStage {
	private static Skin sharedSkin = new Skin(Gdx.files.internal("Data/uiskin.json"));
	private static Slider volumeSlider = new Slider(0.0f, 1.0f, 0.05f, false, sharedSkin);
	private static Slider brightnessSlider = new Slider(0.0f, 0.3f, 0.025f, false, sharedSkin);
	
	public OptionsStage(Viewport v, GameScreen gameScreen, final Game myGame) {
		super(v);

	    addActor(new Title("Background/atk.png", 320, 350));
	    addActor(new Background("Background/MenuBackground.jpg", getCamera()));
	    musicplayer=new GDXMusicPlayer();
	    musicplayer.PlayLooped("Music/Never-Gonna-Give-You-Up.mp3");

	    // Buttons
	    TextButton menuButton = new TextButton("Back to menu", sharedSkin);
	    TextButton windowedButton = new TextButton("Windowed", sharedSkin);
	    TextButton fullscreenButton = new TextButton("Full Screen", sharedSkin);

	    // Labels
	    Label soundLabel = new Label("Music Volume", sharedSkin);
	    Label brightnessLabel = new Label("Brightness", sharedSkin);
	    soundLabel.setColor(Color.BLACK);
	    brightnessLabel.setColor(Color.BLACK);

	    // Positions
	    menuButton.setPosition(500, 50);
	    soundLabel.setPosition(200, 200);
	    brightnessLabel.setPosition(200, 160);
	    volumeSlider.setPosition(320, 200);
	
	    brightnessSlider.setPosition(320, 160);
	    brightnessSlider.setValue(brightnessSlider.getValue());
	    
	    windowedButton.setPosition(230, 120);
	    fullscreenButton.setPosition(350, 120);
	    
	    // Listeners
	    menuButton.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	musicplayer.StopMusic();
	  	    	MenuScreen menuScreen = new MenuScreen(myGame);
	  	    	myGame.setScreen(menuScreen);	  
	  	    	Hero.getInstance().Getmplayer().playpressbutton();
	        }
	    });
	    
	    windowedButton.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	Gdx.graphics.setWindowedMode(640, 480);
	        	Hero.getInstance().Getmplayer().playpressbutton();
	        }
	    });
	    
	    fullscreenButton.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
	        	Hero.getInstance().Getmplayer().playpressbutton();
	        }
	    });
	    
	    volumeSlider.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	musicplayer.SetVolume((volumeSlider.getPercent()));
	        	Hero.getInstance().Getmplayer().playpressbutton();
	        }
	    });
	    
	    volumeSlider.addListener(new DragListener(){
	        @Override
	        public void drag(InputEvent event, float x, float y, int pointer) {
	        	musicplayer.SetVolume((volumeSlider.getPercent()));
	        }
	    });
	    
	    // Add to screen
	    addActor(menuButton);
	    addActor(soundLabel);
	    addActor(volumeSlider);
	    addActor(brightnessLabel);
	    addActor(brightnessSlider);
	    addActor(windowedButton);
	    addActor(fullscreenButton);	    
	}

	@Override
	public void changeCamera(float x, float y) {
		// Does nothing here
	}
	
	public static float getMusicVolume() {
		return volumeSlider.getValue();
	}

	public static float getBrightness() {
		return brightnessSlider.getMaxValue() - brightnessSlider.getValue();
	}
}
