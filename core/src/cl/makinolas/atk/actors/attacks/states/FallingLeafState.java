package cl.makinolas.atk.actors.attacks.states;

import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

import cl.makinolas.atk.actors.Monsters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FallingLeafState extends SpriteState {
  
  @Override
  public void initializeBody(float x, float y) {
    myAttack.initializeBody(x, y);    
  }
  
  @Override
  public int getAttackDamage() {
    return 45;
  }
  
  @Override
  public TextureRegion getTexture() {
    return new TextureRegion(new Texture(Gdx.files.internal("Attacks/fallingLeaf.png")));
  }
  
  @Override
  public int getWidth() {
    return 16;
  }
  
  @Override
  public int getHeight() {
    return 9;
  }
  
  @Override
  public float getFrameTime() {
    return 0.1f;
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
    return 11;
  }
  
  @Override
  public float getAttackTime() {
    return 12 * getFrameTime();
  }
  
  @Override
  public int getTypeAttack(Monsters monster) {
    return myAttack.getPhysicalAttackDamage(monster);
  }
  
}
