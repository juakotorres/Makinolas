package cl.makinolas.atk.actors.attacks;

import cl.makinolas.atk.actors.AnimatedActor;
import cl.makinolas.atk.actors.Enemy;
import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.Platform;

public abstract class Attacks extends AnimatedActor {
  public abstract int getAttackDamage();
  public abstract Monsters getSource();
  public abstract void setDead();
  
  
  @Override
  public boolean isAttack(){
    return true;
  }
  
  @Override
  public void interact(GameActor actor){
    actor.interactWithAttack(this);
  }
  
  @Override
  public void interactWithHero(Hero hero){
    hero.damage(this.getAttackDamage(), this);
  }
  
  @Override
  public void interactWithEnemy(Enemy enemy){
    enemy.damage(this.getAttackDamage(), this);
  }
  
  @Override
  public void interactWithPlatform(Platform platform){
    this.setDead();
  }
  
  protected float getBodySize(int size){
    return (0.5f*size)/22;
  }
}
