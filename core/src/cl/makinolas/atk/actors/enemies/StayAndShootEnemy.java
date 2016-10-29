package cl.makinolas.atk.actors.enemies;

import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.friend.Enemies;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.actors.platform.Platform;
import cl.makinolas.atk.stages.AbstractStage;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;

public class StayAndShootEnemy extends Enemy {

  private final float attackTime = 1.5f;
  private float accumulatorAttack;
  private boolean arrived;
  private boolean inicialLooking;
  
  public StayAndShootEnemy(World myWorld, int givenHealth, int positionX, int positionY,
      boolean facingRight, int level, Enemies type,
      Friend parent) {
    super(myWorld, givenHealth, positionX, positionY, facingRight, level,
        type, parent);
    
    arrived = false;
    inicialLooking = facingRight;
  }
  
  
  @Override
  public void act(float delta){     
    checkState(delta);
    accumulatorAttack += delta; 
    
    if(arrived){
      myBody.setLinearVelocity(0, myBody.getLinearVelocity().y);
    }
    
    if(accumulatorAttack > attackTime){
      GameActor attack = parent.getFriendAttack(myWorld, myBody.getPosition().x - 0.5f, myBody.getPosition().y, isFacingRight, this);
      ((AbstractStage) getStage()).addGameActor(attack);
      accumulatorAttack = 0;
    }
  }
  
  @Override
  public void flip(){
    
  }
  
  
  @Override
  public void interactWithPlatform(Platform platform, WorldManifold worldManifold) {
    arrived = true;
    isFacingRight = inicialLooking;    
  } 
}
