package cl.makinolas.atk.actors.attacks;

import com.badlogic.gdx.physics.box2d.WorldManifold;

import cl.makinolas.atk.actors.AnimatedActor;
import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.platform.Platform;
import cl.makinolas.atk.actors.bosses.IBoss;
import cl.makinolas.atk.actors.enemies.Enemy;

public abstract class Attacks extends AnimatedActor {
	protected float xVelocity;
  public abstract int getAttackDamage();
  public abstract Monsters getSource();
  public abstract void setDead();
  
  public  float getXVelocity() {
	  return xVelocity;
  }
  
  @Override
  public boolean isAttack(){
    return true;
  }
  
  @Override
  public void interact(GameActor actor, WorldManifold worldManifold){
    actor.interactWithAttack(this, worldManifold);
  }
  
  @Override
  public void interactWithHero(Hero hero, WorldManifold worldManifold){
    hero.damage(hero.getAttackDamage(this), this);
  }
  
  @Override
  public void interactWithEnemy(Enemy enemy){
    enemy.damage(enemy.getAttackDamage(this), this);
  }
  
  @Override
  public void interactWithBoss(IBoss boss){
    boss.getBoss().damage(boss.getBoss().getAttackDamage(this), this);
  }
  
  @Override
  public void interactWithPlatform(Platform platform, WorldManifold worldManifold){
    this.setDead();
  }
  
  protected float getBodySize(int size){
    return (0.5f*size)/22;
  }
}
