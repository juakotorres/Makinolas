package cl.makinolas.atk.actors.enemies;

import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.friend.Enemies;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.stages.AbstractStage;
import com.badlogic.gdx.physics.box2d.World;

public class FlyWaveAndDropEnemy extends Enemy {

  private final float attackTime = 1.5f;
  private float accumulatorAttack;
  private float flyingAccumulator;
  
  public FlyWaveAndDropEnemy(World myWorld, int givenHealth, int positionX, int positionY, boolean facingRight, int level, Enemies type,
      Friend parent) {
    super(myWorld, givenHealth, positionX, positionY, facingRight, level,
        type, parent);
    
    accumulatorAttack = 0;
    flyingAccumulator = 0;
  }
  
  @Override
  public void act(float delta){     
    
    flyingAccumulator += delta;
    myBody.setLinearVelocity(vx, (float) (6*Math.sin(4*flyingAccumulator)));
    
    checkState(delta);
    accumulatorAttack += delta; 
    
    if(accumulatorAttack > attackTime){
      Attacks attack = parent.getFriendAttack(myWorld, myBody.getPosition().x, myBody.getPosition().y - 1f, isFacingRight, this);
      attack.isDropping();
      attack.setSource(this);
      ((AbstractStage) getStage()).addGameActor(attack);
      accumulatorAttack = 0;
    }
  }
}
