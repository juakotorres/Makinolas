package cl.makinolas.atk.actors.attacks.states;

import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import cl.makinolas.atk.actors.attacks.Attacks;

public abstract class SpriteState {
  
  protected Attacks myAttack;

  public void setAttack(Attacks attack) {
   myAttack = attack;    
  }
  
  public float getAttackTime(){
    return 0;
  }

  public abstract void initializeBody(float x, float y);
  public abstract int getAttackDamage();
  public abstract TextureRegion getTexture();
  public abstract int getWidth();
  public abstract int getHeight();
  public abstract float getFrameTime();
  public abstract PlayMode getModeAnimation();
  public abstract int getInitialSprite();
  public abstract int getFinalSprite();

  public int getBodyWidth() {
    return getWidth();
  }

  public int getBodyHeight() {
    return getHeight();
  }
}
