package cl.makinolas.atk.actors.attacks.states;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.types.IType;
import cl.makinolas.atk.types.WaterType;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AquaAttackState extends SpriteState {
	
  public static int magicRequirement = 100;
  
  @Override
  public int getAttackDamage() {
    return 90;
  }
  
  @Override
  public TextureRegion getTexture() {
    return new TextureRegion(new Texture(Gdx.files.internal("Attacks/AquaAttack.png")));
  }
  
  @Override
  public int getWidth() {
    return 39;
  }
  
  @Override
  public int getHeight() {
    return 69;
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
  public int getTypeAttack(Monsters monster) {
    return myAttack.getSpecialAttackDamage(monster);
  }

@Override
public IType getType() {
	return new WaterType();
}
  
}
