package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Shinx extends AbstractFriend{
  
  private TextureRegion[][] faces;
  
  public Shinx() {
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Shinx_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Shinx.png"))));
    setAnimations(new int[]{29,28},
                  new int[][]{new int[]{0,0},new int[]{0,1},new int[]{0,2},new int[]{0,1}},
                  new int[][]{new int[]{0,4}});
    setMeleeAnimation(3,3);
    setFaceSprite(faces[0][0]);
    initLevel(5);
    initDead();
    setActualEvolution(0);
    setMaxHealth(30);
    setMaxMagic(100);
  }
  
  public Shinx(int level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level){
   this.level = new Level(level);
   new Evolution(this.level, 16, 1);
   new Evolution(this.level, 32, 1);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Luxio.png"))));
      setAnimations(new int[]{28,32},
          new int[][]{new int[]{0,2},new int[]{0,3},new int[]{0,4},new int[]{0,3}},
          new int[][]{new int[]{0,7}});
      setMeleeAnimation(5,6);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setMaxHealth(80);
      setMaxMagic(100);
    } else if (numberOfLevel == 2 && getActualEvolution() < 2){
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Luxray.png"))));
      setAnimations(new int[]{32,32},
          new int[][]{new int[]{0,2},new int[]{0,3},new int[]{0,4},new int[]{0,3}},
          new int[][]{new int[]{0,7}});
      setMeleeAnimation(5,6);
      setFaceSprite(faces[0][2]);
      setActualEvolution(2);
      setMaxHealth(100);
      setMaxMagic(100);
    }
  }
  

}
