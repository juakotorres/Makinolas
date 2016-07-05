package cl.makinolas.atk.actors.attacks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;

public class DragonBreath extends ThrowableAttacks {
  
  public DragonBreath(World myWorld, float x , float y, boolean facingRight, Monsters source){
    super(myWorld, x, y, facingRight, source);
  }
  
  @Override
  public int getAttackDamage() {
    return 60;
  }
  
  protected void setAnimation(){
    setMasterTexture(new TextureRegion(new Texture(Gdx.files.internal("Attacks/DragonBreath.png"))),38,30);
    addAttackAnimation(0.1f, Animation.PlayMode.LOOP, 0, 5);
  }
  
}
