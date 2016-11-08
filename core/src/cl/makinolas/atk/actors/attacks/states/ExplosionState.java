package cl.makinolas.atk.actors.attacks.states;

import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.types.IType;
import cl.makinolas.atk.types.NormalType;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ExplosionState extends SpriteState {
	
  public static int magicRequirement = 150;
  
  @Override
  public int getAttackDamage() {
    return 100;
  }
  
  @Override
  public TextureRegion getTexture() {
    return new TextureRegion(new Texture(Gdx.files.internal("Attacks/Explosion.png")));
  }
  
  @Override
  public int getWidth() {
    return 35;
  }
  
  @Override
  public int getHeight() {
    return 33;
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
    return 8;
  }
  
  @Override
  public float getXVelocity() {
    return 8;
  }

  @Override
  public boolean oneTimeVelocity() {
    return true;
  }

@Override
public IType getType() {
	return new NormalType();
}
  
}
