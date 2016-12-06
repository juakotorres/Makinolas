package cl.makinolas.atk.actors.attacks;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.states.SpriteState;
import cl.makinolas.atk.actors.platform.Platform;

public class TimeChangeAttack extends Attacks {
  
  private float accumulator;
  private int actualAnimation;
  private int[] attackAnimations;
  private float spriteTime;
  private float setTime;
  private boolean touched;
  private boolean changedBody;
  private boolean firstTime;
  private boolean isMoving;
  
  public TimeChangeAttack(SpriteState spriteState, World myWorld, float x, float y, boolean facingRight, Monsters source, boolean rotated) {
    super(myWorld, x, y, facingRight, source, rotated);
    
    mySpriteState = spriteState;
    
    spriteState.setAttack(this);
    spriteState.initializeBody(x,y); 
    
    yVelocity = spriteState.getYVelocity();
    xVelocity = spriteState.getXVelocity();
    
    accumulator = 0;
    isMoving = mySpriteState.oneTimeVelocity();
    spriteTime = spriteState.getFrameTime();
    touched = false;
    changedBody = false;
    firstTime = true;
    setTime = 0.3f;
    
    setSprite();
  }
  
  @Override
  public void act(float delta){
    checkEffect(delta);
    if(!isMoving){
      myBody.setLinearVelocity(xVelocity, yVelocity);
    } else if (isMoving && firstTime){
      firstTime = false;
      myBody.setLinearVelocity(isFacingRight? -xVelocity:xVelocity, yVelocity);
      myBody.setGravityScale(1);
    }
  }

  private void checkEffect(float delta) {
    accumulator += delta;
    if(!changedBody && touched){
      float x = myBody.getPosition().x;
      float y = myBody.getPosition().y + (isMoving? 0:getBodySize(getBodyHeight()));
      myWorld.destroyBody(myBody);
      mySpriteState.initializeBody(x,y);
      myBody.setTransform(x, y, 0);
      changedBody = true;
    }
    if(touched && accumulator > spriteTime){
      if(actualAnimation  < attackAnimations.length) {
        changeAnimation(attackAnimations[actualAnimation]);
        accumulator = 0;
        actualAnimation += 1;
      } else {
        dead = true;
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
  }
  

  @Override
  public int getAttackDamage() {
    return mySpriteState.getAttackDamage();
  }

  @Override
  public Monsters getSource() {
    return mySource;
  }
  
  @Override
  public void setDead(){
    
  }
  
  @Override
  public boolean isDead(){
    return dead;
  }
  
  public float getXVelocity(){
    return xVelocity;
  }
  
  @Override
  public void setSource(Monsters monsters){
    this.mySource = monsters;
  }

  @Override
  protected void setAnimation() {
    
  }
  
  @Override
  public void manageInteractWithMonster(Monsters monster) {
    if(accumulator > setTime){
      touched = true;
      actualAnimation += 1;
      monster.damage(getAttackDamage(monster), this);   
    } else if(touched){
      monster.damage(getAttackDamage(monster), this);   
    }
  }
  
  @Override
  protected int getBodyWidth() {
    if(!touched){
      return mySpriteState.getBodyWidth();
    } else {
      return mySpriteState.getWidth();
    }
  }
  
  @Override
  protected int getBodyHeight() {
    if(!touched){
      return mySpriteState.getBodyHeight();
    } else {
      return mySpriteState.getHeight();
    }
  } 
  
  @Override
  public void interactWithPlatform(Platform platform, WorldManifold worldManifold){
    myBody.setGravityScale(0);
    isMoving = false;
    yVelocity = 0;
    xVelocity = 0;
  }
   
}
