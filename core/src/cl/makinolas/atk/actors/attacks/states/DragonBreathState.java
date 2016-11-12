package cl.makinolas.atk.actors.attacks.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.types.DragonType;
import cl.makinolas.atk.types.IType;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DragonBreathState extends SpriteState{
  
  public static int magicRequirement = 80;
	
  @Override
  public int getAttackDamage() {
    return 60;
  }
  
  @Override
  public void initializeBody(float x, float y) {
    myAttack.initializeBody(x, y);
  }

  @Override
  public TextureRegion getTexture() {
    return new TextureRegion(new Texture(Gdx.files.internal("Attacks/DragonBreath.png")));
  }

  @Override
  public int getWidth() {
    return 38;
  }

  @Override
  public int getHeight() {
    return 30;
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
    return 5;
  }
  
  @Override
  public int getBodyWidth() {
    return getWidth()/2;
  }

  @Override
  public int getBodyHeight() {
    return getHeight()/2;
  }
  
  @Override
  public int getTypeAttack(Monsters monster) {
    return myAttack.getSpecialAttackDamage(monster);
  }

@Override
public IType getType() {
	return new DragonType();
}
  
}
