package cl.makinolas.atk.actors.attacks.states;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.types.IType;
import cl.makinolas.atk.types.NormalType;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MineState extends SpriteState {
	
  private static int magicRequirement = 65;
  
  @Override
  public int getAttackDamage() {
    return 70;
  }
  
  @Override
  public TextureRegion getTexture() {
    return new TextureRegion(new Texture(Gdx.files.internal("Attacks/Mine.png")));
  }
  
  @Override
  public int getWidth() {
    return 31;
  }
  
  @Override
  public int getHeight() {
    return 26;
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
    return -10;
  }
  
  @Override
  public float getXVelocity() {
    return 0;
  }

@Override
public IType getType() {
	return NormalType.getInstance();
}
  
public static int getMagicRequirement(){
	return magicRequirement;
}

}
