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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.Paths.get;

public class LoadStage extends Stage {

  private Game myGame;

  private LoadActor[] options;
  int k = 0;
  private int lastSelected;
  private Title arrow;
  int cantidad_juegos;
  String [] titulos;
  LoadActor secondSave;
  LoadActor firstSave;
  
  public LoadStage(Viewport v, GameScreen actualScreen, Game myGame) {
    super(v);
    File saves = new File("save");
    cantidad_juegos = saves.list().length;
    titulos = saves.list();
    lastSelected = 0;
    this.myGame = myGame;

    ArrayList<LoadActor> load = new ArrayList<LoadActor>();
    //myScreen = actualScreen;
    addActor(new Background("Background/Wood.png", getCamera()));
    addActor(new Title("Background/LoadFiles.png",220 ,400));


    arrow = new Title("CharacterImages/arrow.png", 50, 300);
    addActor(arrow);


    firstSave = new LoadActor("Save 1", "save/"+titulos[0], 80, 250, this);
    firstSave.addListener(new InputListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        options[0].loadMap();
        return true;
      }
    });

    addActor(firstSave);
    
    secondSave = new LoadActor("Save 2", "save/"+titulos[1], 80, 140, this);
    secondSave.addListener(new InputListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        options[1].loadMap();
        return true;
      }
    });
    addActor(secondSave);



    TextButton menuButton = new TextButton("Back to menu",  new Skin(Gdx.files.internal("Data/uiskin.json")));
    menuButton.setPosition(500, 50);
    menuButton.addListener(new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) {
          MainMenu();
        }
    });
    
    addActor(menuButton);

    options = new LoadActor[]{firstSave,secondSave,firstSave,secondSave};

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
      lastSelected = (lastSelected == 0)? cantidad_juegos-1 : (lastSelected - 1);
      changeArrow(last, lastSelected);
    } if (Gdx.input.isKeyJustPressed(Keys.DOWN)){
      int last = lastSelected;
      lastSelected = (lastSelected == cantidad_juegos-1)? 0 : (lastSelected + 1);
      changeArrow(last, lastSelected);
    } if (Gdx.input.isKeyJustPressed(Keys.Z)){
      options[lastSelected].loadMap();
    }
  }
  
  private void changeArrow(int previous, int actual) {
    
    if(actual < previous){
      arrow.changeCoordinates(50, 300);
      addActor(firstSave =new LoadActor("Save 2", "save/"+titulos[actual], 80, 250, this));
      addActor(secondSave = new LoadActor("Save 2", "save/"+titulos[actual+1], 80, 140, this));
    }

    if( actual > previous){
      arrow.changeCoordinates(50, 190);
      addActor(firstSave = new LoadActor("Save 2", "save/"+titulos[actual-1], 80, 250, this));
      addActor(secondSave = new LoadActor("Save 2", "save/"+titulos[actual], 80, 140, this));
    }

    
  }

  public void startJourney() {
    GameScreen gameScreen = new GameScreen(myGame);
    gameScreen.setStage(new StartingJourneyStage(new FitViewport(640,480), gameScreen, myGame));
    myGame.setScreen(gameScreen);
  }


}
