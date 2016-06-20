package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Eevee extends AbstractFriend {
  
  public Eevee() {
    TextureRegion[][] faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Eevee_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Eevee.png"))));
    setAnimations(new int[]{29,24}, 3,
                  new int[][]{new int[]{0,1},new int[]{0,2},new int[]{0,3}}, 1,
                  new int[][]{new int[]{0,0}});
    setFaceSprite(faces[0][0]);
    
    setVariables(30, false);
  }
}
