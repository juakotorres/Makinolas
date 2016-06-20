package cl.makinolas.atk.actors.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class FriendImage extends Actor {
  
  private TextureRegion region;
  
  public void setTexture(TextureRegion faceReg){
    region = faceReg;
  }
  
  @Override
  public void draw(Batch batch, float parentAlpha) {
      batch.draw(region,getX(),getY());
  }
}
