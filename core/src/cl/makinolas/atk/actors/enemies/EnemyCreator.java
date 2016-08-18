package cl.makinolas.atk.actors.enemies;

import java.util.Observable;
import java.util.Observer;

import cl.makinolas.atk.stages.AbstractStage;
import cl.makinolas.atk.stages.CameraPosition;

public class EnemyCreator implements Observer{

  private String enemyType;
  private int positionX;
  //private int positionY;
  private AbstractStage stage;
  private boolean firstSpawn;
  
  public EnemyCreator(AbstractStage stage, String enemy, int positionX, int positionY) {
    stage.cameraObserver.addObserver(this);
    this.stage = stage;
    enemyType = enemy;
    this.positionX = positionX;
    firstSpawn = false;
    //this.positionY = positionY;
  }

  @Override
  public void update(Observable o, Object arg) {
    float cameraPositionX = ((CameraPosition) o).getPositionX();
    if (Math.abs(cameraPositionX - positionX) < 10 && !firstSpawn){
      firstSpawn = true;
      System.out.println("Spawned");
      stage.addGameActor(MonsterFactory.getInstance().giveClassicEnemy(enemyType, 5, (int) (positionX * 1.8f)));
    }
    
  } 
}
