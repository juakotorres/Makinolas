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
import cl.makinolas.atk.actors.Title;
import cl.makinolas.atk.audio.GDXMusicPlayer;
import cl.makinolas.atk.audio.GDXSoundEffectsEnemy;
import cl.makinolas.atk.audio.GDXSoundEffectsPlayer;
import cl.makinolas.atk.screen.GameScreen;
import cl.makinolas.atk.screen.MenuScreen;

public class OptionsStage extends AbstractStage {
	private GDXSoundEffectsPlayer mplayer = GDXSoundEffectsEnemy.getInstance();
	private static Skin sharedSkin = new Skin(Gdx.files.internal("Data/uiskin.json"));
	private static Slider musicSlider = new Slider(0.0f, 1.0f, 0.05f, false, sharedSkin);
	private static Slider sfxSlider = new Slider(0.0f, 1.0f, 0.05f, false, sharedSkin);
	private static Slider brightnessSlider = new Slider(0.0f, 0.3f, 0.025f, false, sharedSkin);
	private static boolean isFirstRun = true;
	private boolean isFullScreen = false;
	
	public OptionsStage(Viewport v, GameScreen gameScreen, final Game myGame) {
		super(v);

	    addActor(new Title("Background/atk.png", 320, 350));
	    addActor(new Background("Background/MenuBackground.jpg", getCamera()));
	    musicplayer = GDXMusicPlayer.getInstance();
	    musicplayer.PlayLooped("Music/Never-Gonna-Give-You-Up.mp3");
	    sfxplayer = GDXSoundEffectsPlayer.getInstance();

	    // Buttons
	    TextButton menuButton = new TextButton("Back to menu", sharedSkin);
	    final TextButton windowStateButton = new TextButton("Toggle to Full Screen", sharedSkin);

	    // Labels
	    Label musicLabel = new Label("Music Volume", sharedSkin);
	    Label sfxLabel = new Label("SFX Volume", sharedSkin);
	    Label brightnessLabel = new Label("Brightness", sharedSkin);
	    musicLabel.setColor(Color.BLACK);
	    sfxLabel.setColor(Color.BLACK);
	    brightnessLabel.setColor(Color.BLACK);

	    // Positions
	    menuButton.setPosition(500, 50);
	    musicLabel.setPosition(200, 200);
	    sfxLabel.setPosition(200, 160);
	    brightnessLabel.setPosition(200, 120);
	    
	    // Sliders
	    musicSlider.setPosition(320, 200);
	    sfxSlider.setPosition(320, 160);
	    brightnessSlider.setPosition(320, 120);
	    brightnessSlider.setValue(brightnessSlider.getValue());
	    
	    windowStateButton.setPosition(290, 80);
	    
	    // Listeners
	    menuButton.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	musicplayer.StopMusic();
	  	    	MenuScreen menuScreen = new MenuScreen(myGame);
	  	    	myGame.setScreen(menuScreen);	  
	  	    	mplayer.PlayPressButton();
	        }
	    });
	    
	    windowStateButton.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	            mplayer.PlayPressButton();
	        	if (isFullScreen) {
	        		Gdx.graphics.setWindowedMode(640, 480);
	        		windowStateButton.setText("Toggle to Full Screen");
	        	}
	        	else {
	        		Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
	        		windowStateButton.setText("Toggle to Windowed");
	        	}
	        	isFullScreen = !isFullScreen;

	        }
	    });
	    
	    musicSlider.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	mplayer.PlayPressButton();
	        	musicplayer.SetVolume((musicSlider.getPercent()));
	        }
	    });
	    
	    musicSlider.addListener(new DragListener(){
	        @Override
	        public void drag(InputEvent event, float x, float y, int pointer) {
	        	musicplayer.SetVolume((musicSlider.getPercent()));
	        }
	    });
	    
	    sfxSlider.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	mplayer.PlayPressButton();
	        	sfxplayer.SetVolume((sfxSlider.getPercent()));
	        	sfxplayer.PlayJumpSound();
	        }
	    });
	    
	    sfxSlider.addListener(new DragListener(){
	        @Override
	        public void drag(InputEvent event, float x, float y, int pointer) {
	        	sfxplayer.SetVolume((sfxSlider.getPercent()));
	        	sfxplayer.PlayJumpSound();
	        }
	    });
	    
	    // Add to screen
	    addActor(menuButton);
	    addActor(musicLabel);
	    addActor(musicSlider);
	    addActor(sfxLabel);
	    addActor(sfxSlider);
	    addActor(brightnessLabel);
	    addActor(brightnessSlider);
	    addActor(windowStateButton);
	    setToFull();
	}

	@Override
	public void changeCamera(float x, float y) {
		// Does nothing here
	}
	
	public static void setToFull() {
		if (isFirstRun) {
			brightnessSlider.setValue(brightnessSlider.getMaxValue());
			musicSlider.setValue(musicSlider.getMaxValue());
			sfxSlider.setValue(sfxSlider.getMaxValue());
			isFirstRun = false;
		}
	}
	
	public static float getMusicVolume() {
		return musicSlider.getValue();
	}

	public static float getBrightness() {
		return brightnessSlider.getMaxValue() - brightnessSlider.getValue();
	}

	public static float getSFXVolume() {
		return sfxSlider.getValue();
	}
}
