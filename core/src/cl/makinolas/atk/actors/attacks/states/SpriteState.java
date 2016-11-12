package cl.makinolas.atk.actors.attacks.states;

import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.types.IType;

public abstract class SpriteState {
  
  protected Attacks myAttack;
  protected long cooldown;
  
  public abstract int getAttackDamage();
  public abstract TextureRegion getTexture();
  public abstract int getWidth();
  public abstract int getHeight();
  public abstract float getFrameTime();
  public abstract PlayMode getModeAnimation();
  public abstract int getInitialSprite();
  public abstract int getFinalSprite();
  public abstract IType getType();
  
  public SpriteState() {
	  cooldown = 500; // Default Value
  }
  
  public int getBodyWidth() {
    return getWidth();
  }

  public int getBodyHeight() {
    return getHeight();
  }
  
  public void initializeBody(float x, float y){
    myAttack.initializeBody(x, y);
  }
  
  public void setAttack(Attacks attack) {
   myAttack = attack;    
  }
  
  public float getAttackTime(){
    return 0;
  }
  
  public int getEndOfInitialAnimation() {
    return -1;
  }
  
  public int getTypeAttack(Monsters monster){
    return myAttack.getPhysicalAttackDamage(monster);
  }
  
  public float getYVelocity() {
    return 0;
  }
  
  public float getXVelocity() {
    return 0;
  }
  
  public boolean oneTimeVelocity() {
    return false;
  }
  
  public int getCriticalChance(){
	  return 1;
  }
public long getCooldown() {
	return cooldown;
}

public void secondaryEfectsToSource(Monsters monster) {
	
}
public void secondaryEfectsToAfected(Monsters monster) {
	
}

}
