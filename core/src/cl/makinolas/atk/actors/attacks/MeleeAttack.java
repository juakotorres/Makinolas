package cl.makinolas.atk.actors.attacks;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.states.NullSpriteState;
import cl.makinolas.atk.actors.attacks.states.SpriteState;
import cl.makinolas.atk.types.NormalType;

public class MeleeAttack extends Attacks {
  
  public MeleeAttack(Monsters monster){
    super(null, 0, 0, false, monster, false, new NormalType());
    xVelocity =0;
    
    SpriteState spriteState = new NullSpriteState();
    mySpriteState = spriteState;
    
    spriteState.setAttack(this);
  }
  
  @Override
  public int getAttackDamage() {
    return mySource.getMeleeDamage();
  }
  
  @Override
  public Monsters getSource() {
    return mySource;
  }
  
  @Override
  public void setDead() {
    dead = true;
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
  protected void setAnimation() {
    
  }

  
}
