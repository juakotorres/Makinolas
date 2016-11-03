package cl.makinolas.atk.stages;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import cl.makinolas.atk.actors.Background;
import cl.makinolas.atk.actors.LoadActor;
import cl.makinolas.atk.actors.Title;
import cl.makinolas.atk.screen.GameScreen;
import cl.makinolas.atk.screen.MapScreen;
import cl.makinolas.atk.screen.MenuScreen;
import cl.makinolas.atk.start.StartingJourneyStage;

import java.util.ArrayList;

public class LoadStage extends Stage {

  private Game myGame;

  private LoadActor[] options;
  int k = 0;
  private int lastSelected;
  private Title arrow;
  
  public LoadStage(Viewport v, GameScreen actualScreen, Game myGame) {
    super(v);

    lastSelected = 0;
    this.myGame = myGame;
    ArrayList<LoadActor> load = new ArrayList<LoadActor>();
    //myScreen = actualScreen;
    addActor(new Background("Background/Wood.png", getCamera()));
    addActor(new Title("Background/LoadFiles.png",220 ,400));
    
    arrow = new Title("CharacterImages/arrow.png", 50, 300);
    addActor(arrow);
    LoadActor firstSave = new LoadActor("Save 1", "ATK.sav", 80, 250, this,3);
    firstSave.addListener(new InputListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        options[k].loadMap();
        return true;
      }
    });

    addActor(firstSave);
    
    LoadActor secondSave = new LoadActor("Save 2", "ATK2.sav", 80, 150, this,3);
    secondSave.addListener(new InputListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        options[k].loadMap();
        return true;
      }
    });
    addActor(secondSave);


    LoadActor secondSave2 = new LoadActor("Save 2", "ATK2.sav", 80, 40, this,3);
    secondSave2.addListener(new InputListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        options[k].loadMap();
        return true;
      }
    });
    addActor(secondSave2);

    
    TextButton menuButton = new TextButton("Back to menu",  new Skin(Gdx.files.internal("Data/uiskin.json")));
    menuButton.setPosition(500, 50);
    menuButton.addListener(new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) {
          MainMenu();
        }
    });
    
    addActor(menuButton);

    load.add(firstSave);
    load.add(secondSave);
    options = new LoadActor[load.size()];
    for (int i = 0; i < load.size();i++) {
      options[i] = load.get(i);
    }

    //MobileGroup group = new MobileGroup(Gdx.app.getType() == Application.ApplicationType.Android);
    Gdx.input.setInputProcessor(this);

    //setupCamera();
  }


  
  protected void MainMenu() {
    MenuScreen menuScreen = new MenuScreen(myGame);
    myGame.setScreen(menuScreen);
  }

  public void loadMap(){
    MapScreen mapScreen = new MapScreen(myGame,new MapStage(new FitViewport(640, 480),myGame, Levels.LEVEL1.levelSpot));
    myGame.setScreen(mapScreen);
  }


  public void act(float delta){
    super.act(delta);

    if (Gdx.input.isKeyJustPressed(Keys.UP)){
      int last = lastSelected;
      lastSelected = (lastSelected == 0)? 1 : (lastSelected - 1);
      changeArrow(last, lastSelected);
    } if (Gdx.input.isKeyJustPressed(Keys.DOWN)){
      int last = lastSelected;
      lastSelected = (lastSelected == 1)? 0 : (lastSelected + 1);
      changeArrow(last, lastSelected);
    } if (Gdx.input.isKeyJustPressed(Keys.Z)){
      options[lastSelected].loadMap();
    }
  }
  
  private void changeArrow(int previous, int actual) {
    
    if(actual == 0){
      arrow.changeCoordinates(50, 300);
    } else if( actual == 1){
      arrow.changeCoordinates(50, 190);
    }
    
  }

  public void startJourney() {
    GameScreen gameScreen = new GameScreen(myGame);
    gameScreen.setStage(new StartingJourneyStage(new FitViewport(640,480), gameScreen, myGame));
    myGame.setScreen(gameScreen);
  }


}
