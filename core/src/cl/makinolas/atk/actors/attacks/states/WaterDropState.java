package cl.makinolas.atk.actors.attacks.states;

import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.types.IType;
import cl.makinolas.atk.types.TypeFactory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class WaterDropState extends SpriteState {
	
  private static int magicRequirement = 70;
  
  @Override
  public int getAttackDamage() {
    return 50;
  }
  
  @Override
  public TextureRegion getTexture() {
    return new TextureRegion(new Texture(Gdx.files.internal("Attacks/WaterDrop.png")));
  }
  
  @Override
  public int getWidth() {
    return 28;
  }
  
  @Override
  public int getHeight() {
    return 12;
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
    return 0;
  }
  
  @Override
  public int getTypeAttack(Monsters monster) {
    return myAttack.getSpecialAttackDamage(monster);
  }

@Override
public IType getType() {
	return TypeFactory.getType("Water");
}

public static int getMagicRequirement(){
	return magicRequirement;
}
  
}
