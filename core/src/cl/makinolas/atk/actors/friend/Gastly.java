package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Gastly extends AbstractFriend {
  
 private TextureRegion[][] faces;
  
  public Gastly() {
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Gastly_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Gastly.png"))));
    setAnimations(new int[]{30,30},
                  new int[][]{new int[]{0,1},new int[]{0,2},new int[]{0,3}},
                  new int[][]{new int[]{0,0}});
    setFaceSprite(faces[0][0]);
    initLevel(5);
    setActualEvolution(0);
    setVariables(30, false);
  }
  
  public Gastly(float level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(float level){
   this.level = new Level(level);
   new Evolution(this.level, 25, 1);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Haunter.png"))));
      setAnimations(new int[]{46,36},
          new int[][]{new int[]{0,2},new int[]{0,3},new int[]{0,4},new int[]{0,3}},
          new int[][]{new int[]{0,0}});
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setVariables(70, false);
    }
  }
  
}
