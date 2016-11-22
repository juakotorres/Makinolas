package cl.makinolas.atk.actors.attacks.states;

import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.types.GroundType;
import cl.makinolas.atk.types.IType;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BoneAttackState extends SpriteState {
	
  public static int magicRequirement = 60;
  
  @Override
  public void initializeBody(float x, float y) {
    myAttack.initializeBody(x, y);
  }
  
  @Override
  public int getAttackDamage() {
    return 50;
  }
  
  @Override
  public TextureRegion getTexture() {
    return new TextureRegion(new Texture(Gdx.files.internal("Attacks/BoneAttack.png")));
  }
  
  @Override
  public int getWidth() {
    return 28;
  }
  
  @Override
  public int getHeight() {
    return 28;
  }
  
  @Override
  public int getBodyWidth() {
    return 20;
  }
  
  @Override
  public int getBodyHeight() {
    return 20;
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
  public float getAttackTime() {
    return 6 * getFrameTime();
  }
  
  @Override
  public int getEndOfInitialAnimation() {
    return 1;
  }
  
  @Override
  public int getTypeAttack(Monsters monster) {
    return myAttack.getPhysicalAttackDamage(monster);
  }

@Override
public IType getType() {

	return GroundType.getInstance();

}
  
}
