package cl.makinolas.atk.actors.attacks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;

public class BlueBeam extends ThrowableAttacks {
  
  public BlueBeam(World myWorld, float x, float y, boolean facingRight, Monsters source) {
    super(myWorld, x, y, facingRight, source);
  }

  @Override
  protected void setAnimation() {
    setMasterTexture(new TextureRegion(new Texture(Gdx.files.internal("Attacks/bluebeam.png"))),25,15);
    addAnimation(0.2f, new int[]{0, 0}, new int[]{0, 1}, new int[]{0, 2},new int[]{0, 3});
  }
  
  @Override
  public int getAttackDamage() {
    return 70;
  }
  
}
