package cl.makinolas.atk.actors.enemies;

import java.util.Observable;
import java.util.Observer;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.friend.Friend;
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
  private boolean facingRight;
  private int newLevel;
  
  public EnemyCreator(AbstractStage stage, String enemy, float positionX,
                      float positionY, int enemyThinker, boolean facingRight) {
    stage.cameraObserver.addObserver(this);
    this.stage = stage;
    this.enemyType = enemy;
    this.positionX = positionX;
    this.firstSpawn = false;
    this.enemyDead = true;
    this.positionY = positionY;
    this.enemyThinker = enemyThinker;
    this.facingRight = facingRight;
    newLevel = maxLevelOfAllies(Hero.getInstance().getAllies());
  }

  @Override
  public void update(Observable o, Object arg) {
    float cameraPositionX = ((CameraPosition) o).getPositionX();
    float cameraPositionY = ((CameraPosition) o).getPositionY();
    
    if (Math.abs(cameraPositionX - positionX) < 24 && Math.abs(cameraPositionY - positionY) < 24 && !firstSpawn){
      firstSpawn = true;  
      stage.addGameActor(chooseEnemyThinker());
    } else if(firstSpawn && enemyDead && Math.abs(cameraPositionX - positionX) > 24){ 
      firstSpawn = false;    
    } else if (firstSpawn && !enemyDead){
      Vector2 enemyPosition = actualEnemy.getBody().getPosition();

      if(Math.abs(cameraPositionX - enemyPosition.x) > 30 || Math.abs(cameraPositionY - positionY) > 30){
        actualEnemy.setDead();
        enemyDead = true;
      }      
    }
   
  }

  private Enemy chooseEnemyThinker() {
    switch(enemyThinker){
      case 2:
        actualEnemy = MonsterFactory.getInstance().giveStayAndShootEnemy(enemyType, newLevel, (int) (positionX), (int) (positionY + 2), facingRight);
        break;
      case 3:
        actualEnemy = MonsterFactory.getInstance().giveFlyWaveAndDropEnemy(enemyType, newLevel, (int) (positionX), (int) (positionY + 2), facingRight);
        break;
      case 4:
        actualEnemy =  MonsterFactory.getInstance().giveJumperEnemy(enemyType, newLevel, (int) (positionX), (int) (positionY + 2), facingRight);
        ((JumperEnemy) actualEnemy).initDetector(stage);
        break;
      case 5:
        actualEnemy =  MonsterFactory.getInstance().giveFollowerEnemy(enemyType, newLevel, (int) (positionX), (int) (positionY + 2), facingRight);
        break;
      default:
        actualEnemy = MonsterFactory.getInstance().giveClassicEnemy(enemyType, newLevel, (int) (positionX), (int) (positionY + 2), facingRight);
        break;
    }
    enemyDead = false;
    return actualEnemy;
  }

  private int maxLevelOfAllies(Array<Friend> allies) {
    int maxLevel = allies.get(0).getLevel();
    for(Friend ally : allies){
      if(maxLevel < ally.getLevel())
        maxLevel = ally.getLevel();
    }
    return maxLevel;
  } 
}
