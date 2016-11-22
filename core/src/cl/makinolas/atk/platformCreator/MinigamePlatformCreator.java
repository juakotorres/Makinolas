package cl.makinolas.atk.platformCreator;

import java.util.Observable;
import java.util.Observer;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import cl.makinolas.atk.actors.platform.Platform;
import cl.makinolas.atk.stages.AbstractStage;
import cl.makinolas.atk.stages.CameraPosition;

public class MinigamePlatformCreator extends AbstractPlatformCreator{
  

  
  public MinigamePlatformCreator(World suMundo, AbstractStage stage,
                                 int xInitialPosition, int yInitialPosition, Group ground) {
    super(suMundo,stage,xInitialPosition,yInitialPosition,ground);
  }



  @Override
  public void createPlatform(float cameraPositionX, float cameraPositionY) {
    if (Math.abs(cameraPositionX - lastXPosition * 1.8f) < 20){
      int newXPosition = (lastXPosition) +  (int)(Math.random()*3 + 2);
      int newYPosition = lastYPosition>3? ((int) (Math.random()*5) + (lastYPosition - 2)): (int) (Math.random()*4);
      int width = (int) (Math.random()*8 + 2);
      ground.addActor(new Platform(myWorld, "CU", newXPosition , newYPosition, width , 1));
      this.lastXPosition = newXPosition + width;
      this.lastYPosition = newYPosition;
    }
  }

}
