package cl.makinolas.atk.actors.attacks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;

public class Tornado extends BombAttack {
  
  public Tornado(World myWorld, float x , float y, boolean facingRight, Monsters source){
    super(myWorld, x, y, facingRight, source);
    initializeBody(x, y, 0.5f, 1.7f);
  }

  protected void setAnimation(){
    setMasterTexture(new TextureRegion(new Texture(Gdx.files.internal("Attacks/Tornado.png"))),44,64);
    addAttackAnimation(0.1f, Animation.PlayMode.LOOP, 0, 3);
    attackTime = 8 * 0.1f;    
  }

  @Override
  public int getAttackDamage() {
    return 30;
  }
  
}
