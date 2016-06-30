package cl.makinolas.atk.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import cl.makinolas.atk.actors.GameActor;

public abstract class AbstractStage extends Stage {

  public static float elapsedTime;
  
  public AbstractStage(Viewport v) {
    super(v);
  }

  public void addGameActor(GameActor fireball) {
    this.addGameActor(fireball);
  }

  public void changeCamera(float x, float y) {
    this.changeCamera(x, y);    
  }
}
