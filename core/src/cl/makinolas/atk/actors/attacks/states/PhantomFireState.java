package cl.makinolas.atk.actors.attacks.states;

import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PhantomFireState extends SpriteState {
  
  @Override
  public void initializeBody(float x, float y) {
    myAttack.initializeBody(x, y);
  }
  
  @Override
  public int getAttackDamage() {
    return 55;
  }
  
  @Override
  public TextureRegion getTexture() {
    return new TextureRegion(new Texture(Gdx.files.internal("Attacks/PhantomFire.png")));
  }
  
  @Override
  public int getWidth() {
    return 30;
  }
  
  @Override
  public int getHeight() {
    return 31;
  }
  
  @Override
  public float getFrameTime() {
    return 0.2f;
  }
  
  @Override
  public PlayMode getModeAnimation() {
    return Animation.PlayMode.LOOP;
  }
  
  @Override
  public int getInitialSprite() {
    return 0;
  }
  
  @Override
  public int getFinalSprite() {
    return 4;
  }
  
  @Override
  public float getAttackTime() {
    return 5 * getFrameTime();
  }
  
}
