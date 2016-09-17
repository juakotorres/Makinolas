package cl.makinolas.atk.start;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import cl.makinolas.atk.actors.Background;
import cl.makinolas.atk.actors.Title;
import cl.makinolas.atk.screen.GameScreen;
import cl.makinolas.atk.stages.AbstractStage;

public class BoyOrGirlStage extends Stage {

  private Game myGame;

  private TextActor actualText;
  private Title images;
  private int sceneCount;
  private Title arrow;
  private int lastSelected;
  private Title images2;
  private boolean mySex;

  protected String myName;
  
  public BoyOrGirlStage(Viewport v, GameScreen gameScreen, Game myGame) {
    super(v);
    
    sceneCount = 0;
    lastSelected = 0;
    this.myGame = myGame;
    addActor(new Background("Background/SuPuente.jpg", getCamera()));
    
    images = new Title("CharacterImages/Sensei.png",350, 300);
    addActor(images);
    
    //MobileGroup group = new MobileGroup(Gdx.app.getType() == Application.ApplicationType.Android);
    Gdx.input.setInputProcessor(this);
  
    
    actualText = new TextActor(GameText.chooseSexText);
    addActor(actualText);
  }
  
  public void act(float delta){
    super.act(delta);
    
    if(sceneCount == 4){
      GameScreen gameScreen = new GameScreen(myGame);
      gameScreen.setStage(new ChooseStage(new FitViewport(640,480), gameScreen, myGame, myName, mySex));
      myGame.setScreen(gameScreen);
    }
    
    if(Gdx.input.isKeyJustPressed(Keys.Z) && sceneCount == 1){
      selectSex(lastSelected == 0);
    }
    
    if(sceneCount == 2){
      sceneCount = 3;
      Gdx.input.getTextInput(new TextInputListener() {
        
        @Override
        public void input(String text) {
          myName = text;
          sceneCount = 4;
        }
        
        @Override
        public void canceled() {
          myName = "Alex";
          
        }
      }, "Choose your name", "", "Write your name here");
    }
    
    if((Gdx.input.isKeyJustPressed(Keys.Z) || bottomPressed()) && actualText.hasFinished() && sceneCount == 0){
      sceneCount = 1;
      actualText.remove();    
      images.remove();
      images = new Title("CharacterImages/hombre.png",200, 300);
      images.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
          selectSex(true);
          return true;
        }
      });
      images2 = new Title("CharacterImages/mujer.png",400, 300);
      images2.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
          selectSex(false);
          return true;
        }
      });
      addActor(images);
      addActor(images2);
      arrow = new Title("CharacterImages/arrow.png", 100, 300);
      addActor(arrow);      
    }
    
    if (Gdx.input.isKeyJustPressed(Keys.LEFT) && sceneCount == 1){
      lastSelected = (lastSelected == 0)? 1 : (lastSelected - 1);
      changeArrow();
    } if (Gdx.input.isKeyJustPressed(Keys.RIGHT) && sceneCount == 1){
      lastSelected = (lastSelected == 1)? 0 : (lastSelected + 1);
      changeArrow();
    }
  }

  private void selectSex(boolean s){
    arrow.remove();
    images.remove();
    images2.remove();
    sceneCount = 2;
    mySex = s;
  }

  private void changeArrow() {
    int actual = lastSelected;
    if(actual == 0){
      arrow.changeCoordinates(100, 300);
    } else if( actual == 1){
      arrow.changeCoordinates(300, 300);
    }    
  }

  private boolean bottomPressed() {
    return Gdx.input.isTouched() && Gdx.input.getY()>Gdx.graphics.getHeight()*0.7;
  }
  
}
