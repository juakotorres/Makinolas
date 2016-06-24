package cl.makinolas.atk.actors;

public abstract class Monsters extends AnimatedActor {
  
  public abstract void damage(int damage, Attacks inflictor);
  public abstract int getMeleeDamage();
  public abstract void meleedamage(int damage);
  
  @Override
  public boolean isMonster(){
    return true;
  }
  
  protected void meleeAttack(Monsters monster, boolean isAttacking){
    if(isAttacking){
      Attacks melee = new MeleeAttack(this);
      monster.damage(melee.getAttackDamage(), melee);
    }
  }
}

