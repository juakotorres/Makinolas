package cl.makinolas.atk.actors;

public class MeleeAttack extends Attacks {
  
  private Monsters source;
  private boolean dead;
  
  public MeleeAttack(Monsters monster){
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
  
}
