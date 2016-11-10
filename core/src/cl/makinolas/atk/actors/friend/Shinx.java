package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import cl.makinolas.atk.types.ElectricType;

public class Shinx extends AbstractFriend{
  
  private TextureRegion[][] faces;
  
  public Shinx() {
    friend = Enemies.SHINX;
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Shinx_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Shinx.png"))));
    setAnimations(new int[]{29,28},
                  new int[][]{new int[]{0,0},new int[]{0,1},new int[]{0,2},new int[]{0,1}},
                  new int[][]{new int[]{0,4}});
    setMeleeAnimation(3,3);
    setIdleAnimation(0,1,2,1);
    setSpecialAnimation(3);
    setFaceSprite(faces[0][0]);
    initLevel(5);
    initDead();
    newMonster();
    setActualEvolution(0);
    setMaxMagic(1000);
    addType(new ElectricType());
  }
  
  public Shinx(int level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level){
   this.level = new Level(level);
   setStats();
   setHealth(getMaxHealth());
   new Evolution(this.level, 15, 1);
   new Evolution(this.level, 30, 1);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      friend = Enemies.LUXIO;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Luxio.png"))));
      setAnimations(new int[]{28,32},
          new int[][]{new int[]{0,2},new int[]{0,3},new int[]{0,4},new int[]{0,3}},
          new int[][]{new int[]{0,7}});
      setMeleeAnimation(5,6);
      setIdleAnimation(0,1);
      setSpecialAnimation(5,6);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setStats();
      setMaxMagic(1000);
    } else if (numberOfLevel == 2 && getActualEvolution() < 2){
      friend = Enemies.LUXRAY;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Luxray.png"))));
      setAnimations(new int[]{32,32},
          new int[][]{new int[]{0,2},new int[]{0,3},new int[]{0,4},new int[]{0,3}},
          new int[][]{new int[]{0,7}});
      setMeleeAnimation(5,6);
      setIdleAnimation(0,1);
      setSpecialAnimation(5,6);
      setFaceSprite(faces[0][2]);
      setActualEvolution(2);
      setStats();
      setMaxMagic(1000);
    }
  }
  

}
