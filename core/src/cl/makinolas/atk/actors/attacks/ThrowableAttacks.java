package cl.makinolas.atk.actors.attacks;

import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;

public abstract class ThrowableAttacks extends Attacks {
  
  public ThrowableAttacks(World myWorld, float x , float y, boolean facingRight, Monsters source){
    super(myWorld, x, y, facingRight, source);
    
    initializeBody(x,y + 0.1f,0.5f,0.5f);
  }
  
  @Override
  public void act(float delta){
    myBody.setLinearVelocity(xVelocity, yVelocity);
  }
  
  protected abstract void setAnimation();

  @Override
  public int getAttackDamage() {
    return 10;
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

  
}
