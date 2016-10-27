package cl.makinolas.atk.stages;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.screen.GameScreen;

public abstract class AbstractStage extends Stage {

  public static float elapsedTime;
  public Levels level;
  public static String levelName;
  protected GameScreen myScreen;
  protected boolean paused;
  public CameraPosition cameraObserver;
  public static Music music;

  public AbstractStage(Viewport v) {
    super(v);
  }

  public void addGameActor(GameActor fireball) {
    this.addGameActor(fireball);
  }

  public abstract void changeCamera(float x, float y);

  public void addAllie(Friend friend) {
    Hero.getInstance().addAllie(friend);
  }

  public String getLevelName(){
    return level.levelName;
  }

  public String getLevelBackground(){
    return level.levelBackground;
  }
  
  public String getLevelMusic(){
    return level.levelMusic;
  }
    
  public Levels getLevel(){
    return level;
  }
  
  public void changeDeadMenu() {
    music.stop();
    myScreen.mainMenu();
  }

  public void togglePause(){
    paused = !paused;
  }

  public boolean isPaused() {
    return paused;
  }

}
