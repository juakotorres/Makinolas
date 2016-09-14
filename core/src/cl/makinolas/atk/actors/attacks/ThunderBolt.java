package cl.makinolas.atk.actors.attacks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;

public class ThunderBolt extends BombAttack {
  
  public ThunderBolt(World myWorld, float x , float y, boolean facingRight, Monsters source){
    super(myWorld, x, y, facingRight, source);
    initializeBody(x, y, 0.5f, 0.5f);
  }

  protected void setAnimation(){
    setMasterTexture(new TextureRegion(new Texture(Gdx.files.internal("Attacks/Thunderbolt.png"))),36,32);
    addAttackAnimation(0.2f, Animation.PlayMode.LOOP, 0, 4);
    attackTime = 5 * 0.2f;    
  }

  @Override
  public int getAttackDamage() {
    return 30;
  }

}
