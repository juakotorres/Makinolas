package cl.makinolas.atk.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Background extends GameActor {
 
  private TextureRegion backgroundImage;
  
  public Background(){
    backgroundImage = new TextureRegion(new Texture(Gdx.files.internal("Background/SuPuente.jpg")));
  }
  
  @Override
  public void draw(Batch batch, float alpha){
    batch.draw(backgroundImage, getStage().getCamera().position.x - backgroundImage.getRegionWidth() / 2
                                , getStage().getCamera().position.y - backgroundImage.getRegionHeight() / 2);
  }
}
