package cl.makinolas.atk.actors.attacks.states;

import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class NullSpriteState extends SpriteState {
  
  @Override
  public int getAttackDamage() {
    return 0;
  }
  
  @Override
  public TextureRegion getTexture() {
    return null;
  }
  
  @Override
  public int getWidth() {
    return 0;
  }
  
  @Override
  public int getHeight() {
    return 0;
  }
  
  @Override
  public float getFrameTime() {
    return 0;
  }
  
  @Override
  public PlayMode getModeAnimation() {
    return null;
  }
  
  @Override
  public int getInitialSprite() {
    return 0;
  }
  
  @Override
  public int getFinalSprite() {
    return 0;
  }
  
}
