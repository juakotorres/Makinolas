package cl.makinolas.atk.screen;

import cl.makinolas.atk.stages.MapStage;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import cl.makinolas.atk.stages.LoadStage;
import cl.makinolas.atk.stages.MenuStage;
import cl.makinolas.atk.start.StartingJourneyStage;


public class MenuScreen extends SimpleScreen {

  public MenuScreen(Game game){
    super(game, new MenuStage(new FitViewport(640,480)));
    
    TextButton startButton = new TextButton("Start Game",  new Skin(Gdx.files.internal("Data/uiskin.json")));
    startButton.setPosition(280, 240);
    startButton.addListener(new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) {
          startGame();
        }
    });
    /*
    TextButton shopButton = new TextButton("Enter Shop",  new Skin(Gdx.files.internal("Data/uiskin.json")));
    shopButton.addListener(new ClickListener(){
      @Override
      public void clicked(InputEvent event, float x, float y) {
        enterShop();
      }
    });
    */
    TextButton loadButton = new TextButton("Load Game",  new Skin(Gdx.files.internal("Data/uiskin.json")));
    loadButton.addListener(new ClickListener(){
      @Override
      public void clicked(InputEvent event, float x, float y) {
          loadMap();
      }
    });
    TextButton optionButton = new TextButton("Options",  new Skin(Gdx.files.internal("Data/uiskin.json")));
    optionButton.setWidth(startButton.getWidth());
    loadButton.setWidth(startButton.getWidth());

    loadButton.setPosition(280, 200);
    optionButton.setPosition(280, 160);
    stage.addActor(startButton);
    stage.addActor(loadButton);
    stage.addActor(optionButton);
  }

  protected void loadMap(){
    MapScreen mapScreen = new MapScreen(myGame,new MapStage(new FitViewport(640, 480),myGame));
    myGame.setScreen(mapScreen);
  }

  protected void LoadGame() {
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
