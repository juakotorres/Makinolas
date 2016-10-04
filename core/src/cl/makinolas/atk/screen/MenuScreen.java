package cl.makinolas.atk.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import cl.makinolas.atk.minigames.MinigameStage;
import cl.makinolas.atk.stages.Levels;
import cl.makinolas.atk.stages.LoadStage;
import cl.makinolas.atk.stages.MapStage;
import cl.makinolas.atk.stages.MenuStage;
import cl.makinolas.atk.start.StartingJourneyStage;


public class MenuScreen extends SimpleScreen {

  public MenuScreen(Game game){
    super(game, new MenuStage(new FitViewport(640,480)));
    
    /*
    TextButton shopButton = new TextButton("Enter Shop",  new Skin(Gdx.files.internal("Data/uiskin.json")));
    shopButton.addListener(new ClickListener(){
      @Override
      public void clicked(InputEvent event, float x, float y) {
        enterShop();
      }
    });
    */
    TextButton loadButton = new TextButton("Begin Game",  new Skin(Gdx.files.internal("Data/uiskin.json")));
    loadButton.addListener(new ClickListener(){
      @Override
      public void clicked(InputEvent event, float x, float y) {
          loadGame();
      }
    });
    TextButton minigameButton = new TextButton("Minigame",  new Skin(Gdx.files.internal("Data/uiskin.json")));
    minigameButton.addListener(new ClickListener(){
      @Override
      public void clicked(InputEvent event, float x, float y) {
          minigame();
      }
    });
    TextButton optionButton = new TextButton("Options",  new Skin(Gdx.files.internal("Data/uiskin.json")));

    loadButton.setPosition(280, 200);
    optionButton.setPosition(280, 120);
    minigameButton.setPosition(280, 160);
    stage.addActor(minigameButton);
    stage.addActor(loadButton);
    stage.addActor(optionButton);
  }
  
  protected void minigame() {
    GameScreen gameScreen = new GameScreen(myGame);
    gameScreen.setStage(new MinigameStage(new FitViewport(640,480), gameScreen, myGame));
    myGame.setScreen(gameScreen);
  }

  protected void loadMap(){
    MapScreen mapScreen = new MapScreen(myGame,new MapStage(new FitViewport(640, 480),myGame));
    myGame.setScreen(mapScreen);
  }

  protected void loadGame() {
    GameScreen gameScreen = new GameScreen(myGame);
    gameScreen.setStage(new LoadStage(new FitViewport(640,480), gameScreen, myGame));
    myGame.setScreen(gameScreen);
  }

  private void startGame() {
    GameScreen gameScreen = new GameScreen(myGame);
    // previous to go to first stage
    // gameScreen.setStage(new GameStage(new FitViewport(640,480), gameScreen, myGame, Levels.LEVEL1));
    // go to begin story
    gameScreen.setStage(new StartingJourneyStage(new FitViewport(640,480), gameScreen, myGame));
    myGame.setScreen(gameScreen);
  }

  @Override
  public void render(float delta) {
    super.render(delta);
    if(Gdx.input.isKeyPressed(Input.Keys.ENTER))
      startGame();
  }
  
}
