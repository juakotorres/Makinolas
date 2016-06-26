package cl.makinolas.atk.actors;

import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.MeleeAttack;

public abstract class Monsters extends AnimatedActor {
  
  public abstract void damage(int damage, Attacks inflictor);
  public abstract int getMeleeDamage();
  
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
  
  public abstract float getMonsterWidth();
  public abstract float getMonsterHeight();
}

