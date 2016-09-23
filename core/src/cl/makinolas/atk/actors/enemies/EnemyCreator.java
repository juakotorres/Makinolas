package cl.makinolas.atk.actors.enemies;

import java.util.Observable;
import java.util.Observer;

import com.badlogic.gdx.math.Vector2;

import cl.makinolas.atk.stages.AbstractStage;
import cl.makinolas.atk.stages.CameraPosition;

public class EnemyCreator implements Observer{

  private String enemyType;
  private float positionX;
  private float positionY;
  private AbstractStage stage;
  private boolean firstSpawn;
  private int enemyThinker;
  private Enemy actualEnemy;
  private boolean enemyDead;
  
  public EnemyCreator(AbstractStage stage, String enemy, float positionX, float positionY, int enemyThinker) {
    stage.cameraObserver.addObserver(this);
    this.stage = stage;
    this.enemyType = enemy;
    this.positionX = positionX;
    this.firstSpawn = false;
    this.enemyDead = true;
    this.positionY = positionY;
    this.enemyThinker = enemyThinker;
  }

  @Override
  public void update(Observable o, Object arg) {
    float cameraPositionX = ((CameraPosition) o).getPositionX();
    
    if (Math.abs(cameraPositionX - positionX) < 24 && !firstSpawn){
      firstSpawn = true;  
      stage.addGameActor(chooseEnemyThinker());
    } else if(firstSpawn && enemyDead && Math.abs(cameraPositionX - positionX) > 24){ 
      firstSpawn = false;    
    } else if (firstSpawn && !enemyDead){
      Vector2 enemyPosition = actualEnemy.getBody().getPosition();

      if(Math.abs(cameraPositionX - enemyPosition.x) > 30){
        actualEnemy.setDead();
        enemyDead = true;
      }      
    }
   
  }

  private Enemy chooseEnemyThinker() {
    switch(enemyThinker){
      case 2:
        actualEnemy = MonsterFactory.getInstance().giveStayAndShootEnemy(enemyType, 5, (int) (positionX), (int) (positionY + 2));
        break;
      case 3:
        actualEnemy = MonsterFactory.getInstance().giveFlyWaveAndDropEnemy(enemyType, 5, (int) (positionX), (int) (positionY + 2));
        break;
      case 4:
        actualEnemy =  MonsterFactory.getInstance().giveJumperEnemy(enemyType, 5, (int) (positionX), (int) (positionY + 2));
        ((JumperEnemy) actualEnemy).initDetector(stage);
        break;
      case 5:
        actualEnemy =  MonsterFactory.getInstance().giveFollowerEnemy(enemyType, 5, (int) (positionX), (int) (positionY + 2));
        break;
      default:
        actualEnemy = MonsterFactory.getInstance().giveClassicEnemy(enemyType, 5, (int) (positionX), (int) (positionY + 2));
        break;
    }
    enemyDead = false;
    return actualEnemy;
  } 
}
