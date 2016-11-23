package cl.makinolas.atk.actors.enemies;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.friend.Enemies;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.stages.AbstractStage;

public class FlyWaveAndDropEnemy extends Enemy {

  private final float attackTime = 1.5f;
  private float accumulatorAttack;
  private float flyingAccumulator;
  
  public FlyWaveAndDropEnemy(World myWorld, TextureRegion enemyTexture, int[] cutSprite, int[][] numberOfSprite,
      int[][] numberOfHurtSprites, int givenHealth, int positionX, int positionY, boolean facingRight, int level, Enemies type,
      Friend parent) {
    super(myWorld, enemyTexture, cutSprite, numberOfSprite, numberOfHurtSprites, givenHealth, positionX, positionY, facingRight, level,
        type, parent);
    
    accumulatorAttack = 0;
    flyingAccumulator = 0;
  }
  
  @Override
  public void act(float delta){     
	  super.act(delta);
    
	  if(!this.isSinging){
	    flyingAccumulator += delta;
	    myBody.setLinearVelocity(vx, (float) (6*Math.sin(4*flyingAccumulator)));
	  }
    checkDamage(delta, 0);
    accumulatorAttack += delta; 
    
    if(accumulatorAttack > attackTime && super.isFree()){
      Attacks attack = parent.getFriendAttack(myWorld, myBody.getPosition().x, myBody.getPosition().y - 1f, isFacingRight, this);
      attack.isDropping();
      attack.setSource(this);
      ((AbstractStage) getStage()).addGameActor(attack);
      attack.getSpriteState().secondaryEfectsToSource(this);
      accumulatorAttack = 0;
    }
  }
}
