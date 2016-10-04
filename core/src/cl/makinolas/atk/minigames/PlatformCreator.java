package cl.makinolas.atk.minigames;

import java.util.Observable;
import java.util.Observer;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import cl.makinolas.atk.actors.platform.Platform;
import cl.makinolas.atk.stages.AbstractStage;
import cl.makinolas.atk.stages.CameraPosition;

public class PlatformCreator extends Actor implements Observer{
  
  private int lastXPosition;
  private int lastYPosition;
  private Group ground;
  private World myWorld;
  
  public PlatformCreator(World suMundo, AbstractStage stage,
                          int xInitialPosition, int yInitialPosition, Group ground) {
    stage.cameraObserver.addObserver(this);
    
    myWorld = suMundo;
    this.ground = ground;
    this.lastXPosition = xInitialPosition;
    this.lastYPosition = yInitialPosition;
    
  }

  @Override
  public void update(Observable o, Object arg) {
    float cameraPositionX = ((CameraPosition) o).getPositionX();
    float cameraPositionY = ((CameraPosition) o).getPositionY();
    
    if (Math.abs(cameraPositionX - lastXPosition * 1.8f) < 20){
      int newXPosition = (lastXPosition) +  (int)(Math.random()*3 + 2);
      int newYPosition = lastYPosition>3? ((int) (Math.random()*5) + (lastYPosition - 3)): (int) (Math.random()*3);
      int width = (int) (Math.random()*7 + 3);
      ground.addActor(new Platform(myWorld, "CU", newXPosition , newYPosition, width , 1));
      this.lastXPosition = newXPosition + width;    
      this.lastYPosition = newYPosition;
    }
  }
  
}
