package cl.makinolas.atk.stages;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import cl.makinolas.atk.actors.Background;
import cl.makinolas.atk.actors.Title;
import cl.makinolas.atk.screen.GameScreen;
import cl.makinolas.atk.screen.MenuScreen;

public class OptionsStage extends AbstractStage {
	private static Slider volumeSlider;
	private Slider brightnessSlider;
	
	public OptionsStage(Viewport v, GameScreen gameScreen, Game myGame) {
		super(v);
		
	    addActor(new Title("Background/atk.png", 320, 350));
	    addActor(new Background("Background/MenuBackground.jpg", getCamera()));
	    if (music == null)
	    	music = Gdx.audio.newMusic(Gdx.files.internal("Music/Never-Gonna-Give-You-Up.mp3"));
	    music.setLooping(true);
	    music.play();
	    Skin sharedSkin = new Skin(Gdx.files.internal("Data/uiskin.json"));
	    
	    // Buttons
	    TextButton menuButton = new TextButton("Back to menu", sharedSkin);
	    TextButton soundButton = new TextButton("*Volume", sharedSkin);
	    TextButton brightnessButton = new TextButton("*Brightness", sharedSkin);
	    TextButton windowedButton = new TextButton("Windowed", sharedSkin);
	    TextButton fullscreenButton = new TextButton("Full Screen", sharedSkin);
	    volumeSlider = new Slider(0.0f, 1.0f, 0.1f, false, sharedSkin);
	    brightnessSlider = new Slider(0.0f, 1.0f, 0.1f, false, sharedSkin);
	    
	    // Positions
	    menuButton.setPosition(500, 50);
	    soundButton.setPosition(270, 200);
	    volumeSlider.setPosition(370, 200);
	    volumeSlider.setValue(music.getVolume());
	    brightnessButton.setPosition(270, 160);
	    brightnessSlider.setPosition(370, 160);
	    windowedButton.setPosition(270, 120);
	    fullscreenButton.setPosition(270, 80);
	    
	    // Listeners
	    menuButton.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	music.stop();
	  	    	MenuScreen menuScreen = new MenuScreen(myGame);
	  	    	myGame.setScreen(menuScreen);	  
	        }
	    });
	    
	    windowedButton.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	Gdx.graphics.setWindowedMode(640, 480);
	        }
	    });
	    
	    fullscreenButton.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
	        }
	    });
	    
	    brightnessButton.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	          // Nothing yet
	        }
	    });
	    
	    volumeSlider.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	music.setVolume(volumeSlider.getPercent());
	        }
	    });
	    
	    volumeSlider.addListener(new DragListener(){
	        @Override
	        public void drag(InputEvent event, float x, float y, int pointer) {
	        	music.setVolume(volumeSlider.getPercent());
	        }
	    });
	    
	    brightnessSlider.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	System.out.println("TODO: Implementar brillo");
	        }
	    });
	    
	    brightnessSlider.addListener(new DragListener(){
	        @Override
	        public void drag(InputEvent event, float x, float y, int pointer) {
	        	System.out.println("TODO: Implementar brillo");
	        }
	    });
	    
	    // Add to screen
	    addActor(menuButton);
	    addActor(soundButton);
	    addActor(volumeSlider);
	    addActor(brightnessButton);
	    addActor(brightnessSlider);
	    addActor(windowedButton);
	    addActor(fullscreenButton);	    
	}

	@Override
	public void changeCamera(float x, float y) {
		// Does nothing here
	}
	
	public static float getMusicVolume() {
		return volumeSlider.getPercent();
	}
}
