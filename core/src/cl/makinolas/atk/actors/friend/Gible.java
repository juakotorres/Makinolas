package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Gible extends AbstractFriend {
  
  public Gible() {
    TextureRegion[][] faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Gible_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/gible.png"))));
    setAnimations(new int[]{31,29}, 4,
                  new int[][]{new int[]{0,4},new int[]{0,5},new int[]{0,6},new int[]{0,5}},
                  1, new int[][]{new int[]{0,0}});
    setFaceSprite(faces[0][0]);
    setVariables(30, false);
  }

  @Override
  protected void initLevel(float level) {
    // TODO Auto-generated method stub
    
  }
  
}
