package cl.makinolas.atk.actors.enemies;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.friend.Enemies;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.stages.AbstractStage;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class FollowerEnemy extends Enemy {

  private final float attackTime = 2f;
  private float accumulatorAttack;
  private float vy;
  
  public FollowerEnemy(World myWorld, int givenHealth, int positionX, int positionY, boolean facingRight, int level, Enemies type,
      Friend parent) {
    super(myWorld, givenHealth, positionX, positionY, facingRight, level,
        type, parent);
    
    accumulatorAttack = 0;
    vy = -0.1f;
  }
  
  @Override
  public void act(float delta){     
    
    myBody.setLinearVelocity(vx * 2, vy);
    
    checkState(delta);
    checkHeroPosition(delta);
    accumulatorAttack += delta; 
    
    if(accumulatorAttack > attackTime){
      Attacks attack = parent.getFriendAttack(myWorld, myBody.getPosition().x, myBody.getPosition().y - 1f, isFacingRight, this);
      attack.isDropping();
      attack.setSource(this);
      ((AbstractStage) getStage()).addGameActor(attack);
      accumulatorAttack = 0;
    }
  }

  private void checkHeroPosition(float delta) {
    if(!Hero.getInstance().hasBody()){
      return;
    }
    
    Vector2 heroPosition = Hero.getInstance().getBody().getPosition();
    Vector2 myPosition = getBody().getPosition();
    
    if(Math.abs(heroPosition.x - myPosition.x) > 10){
      if((heroPosition.x - myPosition.x) * vx < 0){
        if(myPosition.y - heroPosition.y > 5 && vy < 0){
          vy = vy*1.2f;
        } 
        else if (myPosition.y - heroPosition.y > 7 && vy > 0){
          vy = -0.1f;
        } else {
          vy = 1f;
        }
        flip();        
      }
    }
  }
  
}
