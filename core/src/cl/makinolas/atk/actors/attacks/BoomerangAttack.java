package cl.makinolas.atk.actors.attacks;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.states.SpriteState;

public class BoomerangAttack extends ShootAttack {
  
  private float accumulator;
  private int actualAnimation;
  private int[] attackAnimations;
  private int endOfInitialAnimation;
  private float spriteTime;
  private float bankVelocity;
  private float returningVelocity;
  private boolean initialAnimation;
  private boolean isReturning;
  private Vector2 sourceReturningPosition;
    
  public BoomerangAttack(SpriteState spriteState, World myWorld, float x, float y, boolean facingRight, Monsters source, boolean rotated) {
    super(spriteState, myWorld, x, y, facingRight, source, rotated);
    
    accumulator = 0;
    bankVelocity = xVelocity;
    xVelocity = source.getBody().getLinearVelocity().x;
    yVelocity = source.getBody().getLinearVelocity().y/5;
    spriteTime = spriteState.getFrameTime();
    initialAnimation = true;
    isReturning = false; 
    
    this.initialPosition = 2;
    this.initialPosition *= source.getMonsterWidth();
  }
  
  public void act(float delta){
    super.act(delta);
    checkAnimation(delta);
    if(initialAnimation){
      myBody.setTransform(new Vector2((mySource.getBody().getPosition().x + (mySource.facingRight()? 1:-1) * initialPosition),mySource.getBody().getPosition().y)
          , myBody.getAngle());
    }
  }
  
  
  private void checkAnimation(float delta) {
    accumulator += delta;
    if(accumulator > spriteTime){
      if(initialAnimation && actualAnimation <= endOfInitialAnimation){
        changeAnimation(attackAnimations[actualAnimation]);
        accumulator = 0;
        actualAnimation += 1;
      } else if(initialAnimation && actualAnimation > endOfInitialAnimation) {
        initialAnimation = false;
        changeAnimation(attackAnimations[actualAnimation]);
        accumulator = 0;
        actualAnimation += 1;
        bankVelocity =  Math.abs(bankVelocity) * (mySource.facingRight()? 1:-1);
        isFacingRight = mySource.facingRight();
        xVelocity = bankVelocity;
      } else {
        changeAnimation(attackAnimations[actualAnimation]);
        xVelocity -= 0.5 * (isFacingRight? 1:-1);
        accumulator = 0;
        actualAnimation = (actualAnimation == attackAnimations.length - 1)? endOfInitialAnimation + 1: actualAnimation + 1 ;
      }
      
      if(!isReturning && bankVelocity * xVelocity < 0){
        isReturning = true;
        sourceReturningPosition = mySource.getBody().getPosition();
        returningVelocity = Math.abs(xVelocity);
      }
      
      if(isReturning){
        returningVelocity += 1;
        float xDiff = Math.abs(sourceReturningPosition.x - myBody.getPosition().x);
        float yDiff = Math.abs(sourceReturningPosition.y - myBody.getPosition().y);
        float xPercentage = xDiff / (xDiff + yDiff);
        float yPercentage = yDiff / (xDiff + yDiff);
        
        xVelocity = xPercentage * returningVelocity * Math.signum(sourceReturningPosition.x - myBody.getPosition().x);
        yVelocity = yPercentage * returningVelocity * Math.signum(sourceReturningPosition.y - myBody.getPosition().y);
      }
    }
    
    
  }

  protected void setSprite() {
    setMasterTexture(mySpriteState.getTexture(),mySpriteState.getWidth(),mySpriteState.getHeight());
    attackAnimations = new int[mySpriteState.getFinalSprite() - mySpriteState.getInitialSprite() + 1];
    for(int i = 0; i < attackAnimations.length; i++){
      attackAnimations[i] = addAnimation(mySpriteState.getFrameTime(), mySpriteState.getInitialSprite() + i);
    }     
    actualAnimation = 0;
    endOfInitialAnimation = mySpriteState.getEndOfInitialAnimation();
  }
  
  @Override
  public void manageInteractWithMonster(Monsters monster, WorldManifold worldManifold){
    if(!initialAnimation && monster.equals(mySource)){
      dead = true;
    } else {
      manageInteractWithMonster(monster);   
    }
  }
  
}
