package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Charmander extends AbstractFriend {
  
  public Charmander() {
    TextureRegion[][] faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Charmander_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/charmander.png"))));
    setAnimations(new int[]{22,22}, 4,
                  new int[][]{new int[]{0,0},new int[]{0,1},new int[]{0,2},new int[]{0,1}}, 1,
                  new int[][]{new int[]{0,7}});
    setFaceSprite(faces[0][0]);
    
    setVariables(30, false);
  }
  
  public Charmander(float level){
    this();
    setLevel(level);
  }
  
}
