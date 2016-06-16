package cl.makinolas.atk.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Background extends Actor {
 
  private TextureRegion backgroundImage;
  
  public Background(){
    backgroundImage = new TextureRegion(new Texture(Gdx.files.internal("SuPuente.jpg")));
  }
  
  @Override
  public void draw(Batch batch, float alpha){
    batch.draw(backgroundImage, 0, 0);
  }
}
