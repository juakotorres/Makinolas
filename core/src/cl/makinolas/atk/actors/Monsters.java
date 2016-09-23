package cl.makinolas.atk.actors;

import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.MeleeAttack;
import cl.makinolas.atk.actors.friend.Enemies;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.utils.Formulas;

public abstract class Monsters extends AnimatedActor {
  
  protected boolean isAttacking;
  public abstract void damage(int damage, Attacks inflictor);
  public abstract int getMeleeDamage();
  protected Friend parent;
  public abstract float getXDirection();

  
  @Override
  public boolean isMonster(){
    return true;
  }
  
  public boolean facingRight(){
    return isFacingRight;
  }
  
  public Friend getMyself(){
    return parent;
  }
  
  protected void meleeAttack(Monsters monster, boolean isAttacking){
    if(isAttacking){
      Attacks melee = new MeleeAttack(this);
      monster.damage(monster.getAttackDamage(melee), melee);
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
  
  public int getAttackDamage(Attacks attack) {
    int attackStat = attack.getSource().parent.getAttack();
    int level1 = attack.getSource().parent.getLevel();
    int defenseStat = this.parent.getDefense();
    int level2 = this.parent.getLevel();
    return Formulas.getDamage(attackStat, level1, defenseStat, level2, attack.getAttackDamage());
  }
  
  public boolean isEnemy() {
    return false;
  }

}

