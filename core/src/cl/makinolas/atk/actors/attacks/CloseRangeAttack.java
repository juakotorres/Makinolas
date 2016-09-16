package cl.makinolas.atk.actors.attacks;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.states.SpriteState;

public class CloseRangeAttack extends Attacks {

  private float attackTime;
  private float accumulator;
  
  public CloseRangeAttack(SpriteState spriteState, World myWorld, float x, float y, boolean facingRight, Monsters source) {
    super(myWorld, x, y, facingRight, source, false);
    
    mySpriteState = spriteState;
    
    spriteState.setAttack(this);
    spriteState.initializeBody(x,y); 
    
    accumulator = 0;
    this.initialPosition= (facingRight)? 2f: -2f;
    this.initialPosition *= source.getMonsterWidth();
    
    setSprite();
  }
  
  @Override
  public void act(float delta){
    checkFinish(delta);
    myBody.setTransform(new Vector2((mySource.getBody().getPosition().x + initialPosition * mySource.getMonsterWidth()),mySource.getBody().getPosition().y)
                          , myBody.getAngle());
  }
  
  
  private void checkFinish(float delta) {
    accumulator += delta;
    if(accumulator >= attackTime){
      dead = true;
    }
  }
  

  protected void setSprite() {
    setMasterTexture(mySpriteState.getTexture(),mySpriteState.getWidth(),mySpriteState.getHeight());
    addAttackAnimation(mySpriteState.getFrameTime(), mySpriteState.getModeAnimation(), mySpriteState.getInitialSprite(), mySpriteState.getFinalSprite());
    attackTime = mySpriteState.getAttackTime();
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
