package cl.makinolas.atk.actors;

import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.MeleeAttack;
import cl.makinolas.atk.actors.friend.Enemies;

public abstract class Monsters extends AnimatedActor {
  
  protected boolean isAttacking;
  public abstract void damage(int damage, Attacks inflictor);
  public abstract int getMeleeDamage();
  public abstract float getXDirection();
  
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
  public void interactWithHero2(Hero hero) {
    meleeAttack(hero, isAttacking);       
  }
  public void gainExperience(int level, Enemies type) {
    gainExp(level, type);
  }
  protected abstract void gainExp(int level, Enemies type);
}

