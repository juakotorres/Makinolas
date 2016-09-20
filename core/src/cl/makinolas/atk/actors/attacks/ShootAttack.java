package cl.makinolas.atk.actors.attacks;

import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.states.SpriteState;

public class ShootAttack extends Attacks {
  
  public ShootAttack(SpriteState spriteState, World myWorld, float x , float y, boolean facingRight, Monsters source){
    this(spriteState, myWorld, x, y, facingRight, source, false);
  }
  
  public ShootAttack(SpriteState spriteState, World myWorld, float x , float y, boolean facingRight, 
                      Monsters source, boolean rotated){
    super(myWorld, x, y, facingRight, source, rotated);
    
    mySpriteState = spriteState;
    
    spriteState.setAttack(this);
    spriteState.initializeBody(x,y); 
    
    setSprite();
  }
  
  
  @Override
  public void act(float delta){
    myBody.setLinearVelocity(xVelocity, yVelocity);
  }
  
  protected void setSprite() {
    setMasterTexture(mySpriteState.getTexture(),mySpriteState.getWidth(),mySpriteState.getHeight());
    addAttackAnimation(mySpriteState.getFrameTime(), mySpriteState.getModeAnimation(), mySpriteState.getInitialSprite(), mySpriteState.getFinalSprite());
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
    dead = true;
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

  
}
