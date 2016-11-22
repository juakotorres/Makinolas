package cl.makinolas.atk.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.stages.CorruptSAVStage;
import cl.makinolas.atk.stages.LoadStage;
import cl.makinolas.atk.stages.MenuStage;
import cl.makinolas.atk.stages.MiniGameSelect;
import cl.makinolas.atk.stages.OptionsStage;

public class MenuScreen extends SimpleScreen {

  public MenuScreen(Game game){
    super(game, new MenuStage(new FitViewport(640,480)));

    TextButton loadButton = new TextButton("Begin Game",  new Skin(Gdx.files.internal("Data/uiskin.json")));
    loadButton.addListener(new ClickListener(){
      @Override
      public void clicked(InputEvent event, float x, float y) {
    	  Hero.getInstance().Getmplayer().playpressbutton();
          loadGame();
      }
    });
    TextButton minigameButton = new TextButton("Minigame",  new Skin(Gdx.files.internal("Data/uiskin.json")));
    minigameButton.addListener(new ClickListener(){
      @Override
      public void clicked(InputEvent event, float x, float y) {
    	  Hero.getInstance().Getmplayer().playpressbutton();
          minigame();
      }
    });
    TextButton optionButton = new TextButton("Options",  new Skin(Gdx.files.internal("Data/uiskin.json")));
    optionButton.addListener(new ClickListener(){
      @Override
      public void clicked(InputEvent event, float x, float y) {
    	  Hero.getInstance().Getmplayer().playpressbutton();
          options();
      }
    });

    loadButton.setPosition(270, 200);
    optionButton.setPosition(270, 120);
    minigameButton.setPosition(270, 160);
    loadButton.setWidth(100);
    optionButton.setWidth(100);
    minigameButton.setWidth(100);
    stage.addActor(minigameButton);
    stage.addActor(loadButton);
    stage.addActor(optionButton);
  }
  
  protected void options() {
	GameScreen gameScreen = new GameScreen(myGame);
	gameScreen.setStage(new OptionsStage(new FitViewport(640,480), gameScreen, myGame));
	myGame.setScreen(gameScreen);
  }
  
  protected void minigame() {
    GameScreen gameScreen = new GameScreen(myGame);
    gameScreen.setStage(new MiniGameSelect(new FitViewport(640,480), gameScreen, myGame));
    myGame.setScreen(gameScreen);
  }

  /*@deprecated
  protected void loadMap(){
    MapScreen mapScreen = new MapScreen(myGame,new MapStage(new FitViewport(640, 480),myGame));
    myGame.setScreen(mapScreen);
  }*/

  protected void loadGame() {
	GameScreen gameScreen = new GameScreen(myGame);
	if (!MenuStage.hasCorruptSAV())
    	gameScreen.setStage(new LoadStage(new FitViewport(640,480), gameScreen, myGame));
	else
		gameScreen.setStage(new CorruptSAVStage(new FitViewport(640,480), gameScreen, myGame));
    myGame.setScreen(gameScreen);
  }

  /*
  private void startGame() {
    GameScreen gameScreen = new GameScreen(myGame);
    // previous to go to first stage
    // gameScreen.setStage(new GameStage(new FitViewport(640,480), gameScreen, myGame, Levels.LEVEL1));
    // go to begin story
    gameScreen.setStage(new StartingJourneyStage(new FitViewport(640,480), gameScreen, myGame));
    myGame.setScreen(gameScreen);
  }*/

  @Override
  public void render(float delta) {
    super.render(delta);
    if(Gdx.input.isKeyPressed(Input.Keys.Z))
      loadGame();
  }
  
}
