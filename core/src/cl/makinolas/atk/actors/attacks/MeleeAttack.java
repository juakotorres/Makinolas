package cl.makinolas.atk.actors.attacks;

import cl.makinolas.atk.actors.Monsters;

public class MeleeAttack extends Attacks {
  
  private Monsters source;
  private boolean dead;
  
  public MeleeAttack(Monsters monster){
    xVelocity =0;
    source = monster;
    dead = false;
  }
  
  @Override
  public int getAttackDamage() {
    return source.getMeleeDamage();
  }
  
  @Override
  public Monsters getSource() {
    return source;
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
    this.source = monsters;
  }

  
}
