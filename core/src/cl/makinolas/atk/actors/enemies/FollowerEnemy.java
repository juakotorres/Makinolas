package cl.makinolas.atk.actors.enemies;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.friend.Enemies;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.stages.AbstractStage;

public class FollowerEnemy extends Enemy {

  private final float attackTime = 2f;
  private float accumulatorAttack;
  private float vy;
  
  public FollowerEnemy(World myWorld, TextureRegion enemyTexture, int[] cutSprite, int[][] numberOfSprite,
      int[][] numberOfHurtSprites, int givenHealth, int positionX, int positionY, boolean facingRight, int level, Enemies type,
      Friend parent) {
    super(myWorld, enemyTexture, cutSprite, numberOfSprite, numberOfHurtSprites, givenHealth, positionX, positionY, facingRight, level,
        type, parent);
    
    accumulatorAttack = 0;
    vy = -0.1f;
  }
  
  @Override
  public void act(float delta){     
	  super.act(delta);
    
	  if(!this.isSinging){
		  myBody.setLinearVelocity(vx * 2, vy);
	  }
    checkDamage(delta, 0);
    checkHeroPosition(delta);
    accumulatorAttack += delta; 
    
    if(!this.isSinging && accumulatorAttack > attackTime && super.isFree()){
      Attacks attack = parent.getFriendAttack(myWorld, myBody.getPosition().x, myBody.getPosition().y - 1f, isFacingRight, this);
      attack.isDropping();
      attack.setSource(this);
      ((AbstractStage) getStage()).addGameActor(attack);
      attack.getSpriteState().secondaryEfectsToSource(this);
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
