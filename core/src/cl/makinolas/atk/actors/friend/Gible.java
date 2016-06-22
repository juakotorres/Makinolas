package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Gible extends AbstractFriend {
  
  private TextureRegion[][] faces;
  
  public Gible() {
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Gible_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/gible.png"))));
    setAnimations(new int[]{31,29}, 4,
                  new int[][]{new int[]{0,4},new int[]{0,5},new int[]{0,6},new int[]{0,5}},
                  1, new int[][]{new int[]{0,0}});
    setFaceSprite(faces[0][0]);
    setVariables(30, false);
  }

  public Gible(float level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(float level){
   this.level = new Level(level);
   new Evolution(this.level, 34, 1);
   new Evolution(this.level, 50, 1);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1){
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Gabite.png"))));
      setAnimations(new int[]{39,34}, 4,
          new int[][]{new int[]{0,4},new int[]{0,5},new int[]{0,6},new int[]{0,5}}, 1,
          new int[][]{new int[]{0,0}});
      setFaceSprite(faces[0][1]);
      setVariables(70, false);
    } else if (numberOfLevel == 2){
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Garchomp.png"))));
      setAnimations(new int[]{43,40}, 4,
          new int[][]{new int[]{0,4},new int[]{0,5},new int[]{0,6},new int[]{0,5}}, 1,
          new int[][]{new int[]{0,0}});
      setFaceSprite(faces[0][2]);
      setVariables(140, false);
    }
  }
  
}
