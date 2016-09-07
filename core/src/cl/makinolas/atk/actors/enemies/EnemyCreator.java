package cl.makinolas.atk.actors.enemies;

import java.util.Observable;
import java.util.Observer;

import com.badlogic.gdx.math.Vector2;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.stages.AbstractStage;
import cl.makinolas.atk.stages.CameraPosition;

public class EnemyCreator implements Observer{

  private String enemyType;
  private int positionX;
  private int positionY;
  private AbstractStage stage;
  private boolean firstSpawn;
  private int enemyThinker;
  private Enemy actualEnemy;
  private boolean enemyDead;
  
  public EnemyCreator(AbstractStage stage, String enemy, int positionX, int positionY, int enemyThinker) {
    stage.cameraObserver.addObserver(this);
    this.stage = stage;
    enemyType = enemy;
    this.positionX = positionX;
    firstSpawn = false;
    enemyDead = true;
    this.positionY = positionY;
    this.enemyThinker = enemyThinker;
  }

  @Override
  public void update(Observable o, Object arg) {
    float cameraPositionX = ((CameraPosition) o).getPositionX();
    
    if (Math.abs(cameraPositionX - positionX) < 0.1 && !firstSpawn){
      firstSpawn = true;
      stage.addGameActor(chooseEnemyThinker());
    } else if(firstSpawn && enemyDead && Math.abs(cameraPositionX - positionX) > 0.1){ 
      firstSpawn = false;    
    } else if (firstSpawn && !enemyDead){
      Vector2 heroPosition = Hero.getInstance().getBody().getPosition();
      Vector2 enemyPosition = actualEnemy.getBody().getPosition();
      
      if(Math.abs(heroPosition.x - enemyPosition.x) > 25){
        actualEnemy.setDead();
        enemyDead = true;
      }      
    }
   
  }

  private Enemy chooseEnemyThinker() {
    switch(enemyThinker){
      case 2:
        actualEnemy = MonsterFactory.getInstance().giveStayAndShootEnemy(enemyType, 5, (int) (positionX * 1.8f), (int) (positionY * 2f));  
        break;
      case 3:
        actualEnemy = MonsterFactory.getInstance().giveFlyWaveAndDropEnemy(enemyType, 5, (int) (positionX * 1.8f), (int) (positionY * 2f));
        break;
      case 4:
        actualEnemy =  MonsterFactory.getInstance().giveJumperEnemy(enemyType, 5, (int) (positionX * 1.8f), (int) (positionY * 2f));
        ((JumperEnemy) actualEnemy).initDetector(stage);
        break;
      default:
        actualEnemy = MonsterFactory.getInstance().giveClassicEnemy(enemyType, 5, (int) (positionX * 1.8f), (int) (positionY * 2f));
        break;
    }
    enemyDead = false;
    return actualEnemy;
  } 
}
