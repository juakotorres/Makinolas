package cl.makinolas.atk.actors;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.WorldManifold;

import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.MeleeAttack;
import cl.makinolas.atk.actors.friend.Enemies;
import cl.makinolas.atk.actors.friend.EvStates.EvState;
import cl.makinolas.atk.stateEfects.IStateEfects;
import cl.makinolas.atk.actors.friend.Friend;

public abstract class Monsters extends AnimatedActor {
  
  protected boolean isAttacking;
  public abstract void damage(int damage, Attacks inflictor);
  public abstract int getMeleeDamage();
  protected Friend parent;
  public abstract float getXDirection();
  public abstract void CriticalDamage();
  public abstract float getRelativeY();
  public abstract float getRelativeX();
  public abstract void sing();
  public abstract void unSing();
  public abstract void sleep();
  public abstract void Awake();
  
  protected ArrayList<IStateEfects> states;
  
  public Monsters(){
	  super();
	  states = new ArrayList<IStateEfects>();
  }
  
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
      melee.manageInteractWithMonster(monster);
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
  
  public boolean isEnemy() {
    return false;
  }

  public void gainEffortValues(Enemies type) {
    EvState[] states = type.evState;
    for(EvState actualState : states){
      actualState.addEffortValue(this);
    }
  }
  
	@Override
	public void endInteraction(GameActor actor2, WorldManifold worldManifold) {
		actor2.endMonsterIntercation(this, worldManifold);
	}
  
  @Override
  public  void act(float delta) {
      super.act(delta);
      try{
    	  for(IStateEfects state: states){
    		  state.act(delta);
    	  }
      }catch(ConcurrentModificationException e){
    	  return;
      }
  }
  
  @Override
  public  void draw(Batch batch, float alpha){
	  super.draw(batch, alpha);
	  for(IStateEfects state: states){
    	  state.getDrawStateEfects().draw(batch, alpha, this.getRelativeX(),this.getRelativeY());
      } 
  }  
  
  public  void addState(IStateEfects state, int prob){
	  state.affect(this, prob, states);
  }
  
  public  void removeState(IStateEfects state){
	  this.states.remove(state);
  }


}

