package cl.makinolas.atk.actors.attacks.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.types.IType;
import cl.makinolas.atk.types.PoisonType;

public class AcidState extends SpriteState {
	
  public static int magicRequirement = 1;
  
  @Override
  public int getAttackDamage() {
    return 40;
  }

  @Override
  public void initializeBody(float x, float y) {
    myAttack.initializeBody(x, y);    
  }

  @Override
  public TextureRegion getTexture() {
    return new TextureRegion(new Texture(Gdx.files.internal("Attacks/Acid.png")));
  }

  @Override
  public int getWidth() {
    return 23;
  }

  @Override
  public int getHeight() {
    return 23;
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
    return 2;
  }

  @Override
  public int getTypeAttack(Monsters monster) {
    return myAttack.getSpecialAttackDamage(monster);
  }

@Override
public IType getType() {
	return new PoisonType();
}
  
}
