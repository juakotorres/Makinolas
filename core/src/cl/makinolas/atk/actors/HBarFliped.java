package cl.makinolas.atk.actors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HBarFliped {

  private float vmax, vcurrent;
  private int heightmax;
  private Sprite reg;

  public HBarFliped(float max, float current, int width, int height, TextureRegion sprite){
      vmax = max;
      vcurrent = current;
      heightmax = height;
      reg = new Sprite(sprite);
      setCurrent(current);
      reg.setRegionWidth(width);
  }

  public void setCurrent(float c){
      vcurrent = c;
      reg.setRegionHeight((int) (heightmax*vcurrent/vmax));
  }

  public TextureRegion getSprite(){
      return reg;
  }
}
