package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Scyther extends AbstractFriend {
  
  private TextureRegion[][] faces;
  
  public Scyther() {
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Scyther_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Scyther.png"))));
    setAnimations(new int[]{43,35},
                  new int[][]{new int[]{0,3},new int[]{0,4},new int[]{0,5},new int[]{0,4}},
                  new int[][]{new int[]{0,0}});
    setFaceSprite(faces[0][0]);
    initLevel(5);
    setActualEvolution(0);
    setVariables(60, false);
  }
  
  public Scyther(float level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(float level){
   this.level = new Level(level);
   new Evolution(this.level, 30, 1);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Scyzor.png"))));
      setAnimations(new int[]{35,34},
          new int[][]{new int[]{0,3},new int[]{0,4},new int[]{0,5},new int[]{0,4}},
          new int[][]{new int[]{0,0}});
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setVariables(80, false);
    } 
  }
  
  
}
