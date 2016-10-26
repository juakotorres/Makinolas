package cl.makinolas.atk.actors.attacks;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.states.SpriteState;
import cl.makinolas.atk.types.IType;

public class CloseRangeAttack extends Attacks {

  private float accumulator;
  private int actualAnimation;
  private int[] attackAnimations;
  private float spriteTime;
  
  public CloseRangeAttack(SpriteState spriteState, World myWorld, float x, float y, boolean facingRight, Monsters source, IType type) {
    super(myWorld, x, y, facingRight, source, false, type);
    
    mySpriteState = spriteState;
    
    spriteState.setAttack(this);
    spriteState.initializeBody(x,y); 
    
    accumulator = 0;
    spriteTime = spriteState.getFrameTime();
    this.initialPosition= (facingRight)? 2f: -2f;
    this.initialPosition *= source.getMonsterWidth();
    
    setSprite();
  }
  
  @Override
  public void act(float delta){
    checkMelee(delta);
    myBody.setTransform(new Vector2((mySource.getBody().getPosition().x + initialPosition * mySource.getMonsterWidth()),mySource.getBody().getPosition().y)
                          , myBody.getAngle());
  }
  
  
  private void checkMelee(float delta) {
    accumulator += delta;
    if(accumulator > spriteTime){
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
  
   @Override
   public float getXVelocity(){
     return isFacingRight? -3 : 3;
   }
   
   @Override
   public void setSource(Monsters monsters){
     this.mySource = monsters;
   }

  @Override
  public int getAttackDamage() {
    return mySpriteState.getAttackDamage();
  }

  @Override
  protected void setAnimation() {
    
  }

    
}
