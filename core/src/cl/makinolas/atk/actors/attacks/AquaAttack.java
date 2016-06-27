package cl.makinolas.atk.actors.attacks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import cl.makinolas.atk.actors.Monsters;

public class AquaAttack extends Attacks {
  
  protected void setAnimation(){
    setMasterTexture(new TextureRegion(new Texture(Gdx.files.internal("Attacks/AquaAttack.png"))),39,69);
    addAttackAnimation(0.2f, Animation.PlayMode.NORMAL, 0, 6);
  }
  
  @Override
  public int getAttackDamage() {
    return 20;
  }
  
  @Override
  public Monsters getSource() {
    return null;
  }
  
  @Override
  public void setDead() {
    
  }
  
}
