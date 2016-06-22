package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Charmander extends AbstractFriend {
  
  private TextureRegion[][] faces;
  
  public Charmander() {
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Charmander_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/charmander.png"))));
    setAnimations(new int[]{22,22}, 4,
                  new int[][]{new int[]{0,0},new int[]{0,1},new int[]{0,2},new int[]{0,1}}, 1,
                  new int[][]{new int[]{0,7}});
    setFaceSprite(faces[0][0]);
    initLevel(5);
    
    setVariables(30, false);
  }
  
  public Charmander(float level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(float level){
   this.level = new Level(level);
   new Evolution(this.level, 16, 1);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1){
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/charmeleon.png"))));
      setAnimations(new int[]{27,27}, 4,
          new int[][]{new int[]{0,3},new int[]{0,4},new int[]{0,5},new int[]{0,4}}, 1,
          new int[][]{new int[]{0,0}});
      setFaceSprite(faces[0][1]);
      setVariables(60, false);
    }
  }
  
}
