package cl.makinolas.atk.actors.attacks.states;

import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.types.GrassType;
import cl.makinolas.atk.types.IType;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TreeState extends SpriteState {
	
  public static int magicRequirement = 80;
  
  @Override
  public int getAttackDamage() {
    return 70;
  }
  
  @Override
  public TextureRegion getTexture() {
    return new TextureRegion(new Texture(Gdx.files.internal("Attacks/Tree.png")));
  }
  
  @Override
  public int getWidth() {
    return 19;
  }
  
  @Override
  public int getHeight() {
    return 29;
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
    return 6;
  }
  
  @Override
  public float getAttackTime() {
    return 7 * getFrameTime();
  }
  
  @Override
  public int getTypeAttack(Monsters monster) {
    return myAttack.getPhysicalAttackDamage(monster);
  }
  
  @Override
  public int getBodyWidth() {
    return 6;
  }

  @Override
  public int getBodyHeight() {
    return 4;
  }
  
  @Override
  public float getYVelocity() {
    return -10;
  }
  
  @Override
  public float getXVelocity() {
    return 0;
  }

@Override
public IType getType() {
	return GrassType.getInstance();
}
  
  
}
