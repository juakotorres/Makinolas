package cl.makinolas.atk.actors.enemies;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;

import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.friend.Enemies;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.actors.platform.Platform;
import cl.makinolas.atk.stages.AbstractStage;

public class StayAndShootEnemy extends Enemy {

  private final float attackTime = 1.5f;
  private float accumulatorAttack;
  private boolean arrived;
  private boolean inicialLooking;
  
  public StayAndShootEnemy(World myWorld, TextureRegion enemyTexture, int[] cutSprite, int[][] numberOfSprite,
      int[][] numberOfHurtSprites, int givenHealth, int positionX, int positionY,
      boolean facingRight, int level, Enemies type,
      Friend parent) {
    super(myWorld, enemyTexture, cutSprite, numberOfSprite, numberOfHurtSprites, givenHealth, positionX, positionY, facingRight, level,
        type, parent);
    
    arrived = false;
    inicialLooking = facingRight;
  }
  
  
  @Override
  public void act(float delta){     
	  super.act(delta);
    checkDamage(delta, 0);
    accumulatorAttack += delta; 
    
    if(arrived){
      myBody.setLinearVelocity(0, myBody.getLinearVelocity().y);
    }
    
    if(!isSinging && accumulatorAttack > attackTime && super.isFree()){
	    	if(parent.secondaryAttack()){
	    		double rand = Math.random()*100;
	    		if(rand>70){
	    			GameActor attack = parent.getFriendSecondaryAttack(myWorld, myBody.getPosition().x - 0.5f, myBody.getPosition().y, isFacingRight, this);
	                ((AbstractStage) getStage()).addGameActor(attack);
	                ((Attacks) attack).getSpriteState().secondaryEfectsToSource(this);
	                accumulatorAttack = 0;
	    		}else{
	    			GameActor attack = parent.getFriendAttack(myWorld, myBody.getPosition().x - 0.5f, myBody.getPosition().y, isFacingRight, this);
	                ((AbstractStage) getStage()).addGameActor(attack);
	                ((Attacks) attack).getSpriteState().secondaryEfectsToSource(this);
	                accumulatorAttack = 0;
	    		}
	    	}else{
	            GameActor attack = parent.getFriendAttack(myWorld, myBody.getPosition().x - 0.5f, myBody.getPosition().y, isFacingRight, this);
	            ((AbstractStage) getStage()).addGameActor(attack);
	            ((Attacks) attack).getSpriteState().secondaryEfectsToSource(this);
	            accumulatorAttack = 0;
	    	}
	    
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
