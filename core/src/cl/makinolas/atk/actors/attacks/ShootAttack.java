package cl.makinolas.atk.actors.attacks;

import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;

public abstract class ShootAttack extends Attacks {
  
  public ShootAttack(World myWorld, float x , float y, boolean facingRight, Monsters source){
    super(myWorld, x, y, facingRight, source);    
  }
  
  protected abstract void setAnimation();
  
  public float getXVelocity(){
    return xVelocity;
  }
  
}
