package cl.makinolas.atk.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.screen.GameScreen;

public abstract class AbstractStage extends Stage {

  public static float elapsedTime;
  public Levels level;
  public static String levelName;
  protected GameScreen myScreen;
  
  public AbstractStage(Viewport v) {
    super(v);
  }

  public void addGameActor(GameActor fireball) {
    this.addGameActor(fireball);
  }

  public void changeCamera(float x, float y) {
    this.changeCamera(x, y);    
  }
  
  public String getLevelName(){
    return level.levelName;
  }
  
  public Levels getLevel(){
    return level;
  }
  
  public void changeDeadMenu() {
    myScreen.mainMenu();
  }
  
}
