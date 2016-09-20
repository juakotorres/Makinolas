package cl.makinolas.atk.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Title extends Actor {

  private TextureRegion backgroundImage;
  private float xPosition;
  private float yPosition; 
  
  public Title(String pathImage, float x, float y){
    xPosition = x;
    yPosition = y;
    backgroundImage = new TextureRegion(new Texture(Gdx.files.internal(pathImage)));
    setBounds(0,0,backgroundImage.getRegionWidth(),backgroundImage.getRegionHeight());
    setPosition(x-backgroundImage.getRegionWidth()/2,y-backgroundImage.getRegionHeight()/2);
  }
  
  @Override
  public void draw(Batch batch, float alpha){
    batch.draw(backgroundImage, xPosition - backgroundImage.getRegionWidth() / 2
                                , yPosition - backgroundImage.getRegionHeight() / 2);
  }

  public void changeCoordinates(int xPosition, int yPosition) {
    this.xPosition = xPosition;
    this.yPosition = yPosition;
  }
  
}
