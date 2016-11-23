package cl.makinolas.atk.actors.attacks.states;

import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.types.IType;
import cl.makinolas.atk.types.NormalType;

import com.badlogic.gdx.Gdx;	
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CrushClawState extends SpriteState {
	
  public static int magicRequirement = 70;
  
  @Override
  public void initializeBody(float x, float y) {
    myAttack.initializeBody(x, y);
  }
  
  @Override
  public int getAttackDamage() {
    return 75;
  }
  
  @Override
  public TextureRegion getTexture() {
    return new TextureRegion(new Texture(Gdx.files.internal("Attacks/CrushClaw.png")));
  }
  
  @Override
  public int getWidth() {
    return 29;
  }
  
  @Override
  public int getHeight() {
    return 28;
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
    return 4;
  }
  
  @Override
  public float getAttackTime() {
    return 5 * getFrameTime();
  }
  
  @Override
  public int getTypeAttack(Monsters monster) {
    return myAttack.getPhysicalAttackDamage(monster);
  }

@Override
public IType getType() {
	return NormalType.getInstance();
}
}
