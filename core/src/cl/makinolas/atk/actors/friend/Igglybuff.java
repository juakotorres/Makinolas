package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import cl.makinolas.atk.types.FairyType;
import cl.makinolas.atk.types.NormalType;

public class Igglybuff extends AbstractFriend {
  
  private TextureRegion[][] faces;
  
  public Igglybuff() {
    friend = Enemies.IGGLYBUFF;
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Igglypuff_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Igglypuff.png"))));
    setAnimations(new int[]{17,18},
                  new int[][]{new int[]{0,3},new int[]{0,4},new int[]{0,5}},
                  new int[][]{new int[]{0,0}});
    setMeleeAnimation(6,8);
    setIdleAnimation(1,2);
    setSpecialAnimation(9,11);
    setFaceSprite(faces[0][0]);
    initLevel(5);
    initDead();
    setActualEvolution(0);
    setMaxMagic(1000);
    addType(new NormalType());
    addType(new FairyType());
  }
  
  public Igglybuff(int level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level){
   this.level = new Level(level);
   setStats();
   setHealth(getMaxHealth());
   new Evolution(this.level, 16, 1);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      friend = Enemies.JIGGLYPUFF;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Jigglypuff.png"))));
      setAnimations(new int[]{23,21},
          new int[][]{new int[]{0,3},new int[]{0,4},new int[]{0,5}},
          new int[][]{new int[]{0,0}});
      setMeleeAnimation(6,7);
      setIdleAnimation(1,2);
      setSpecialAnimation(8,10);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setStats();
      setMaxMagic(1000);
    } else if (numberOfLevel == 2 && getActualEvolution() < 2){
      friend = Enemies.WIGGLYTUFF;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Wigglypuff.png"))));
      setAnimations(new int[]{26,25},
          new int[][]{new int[]{0,2},new int[]{0,3},new int[]{0,4},new int[]{0,3}},
          new int[][]{new int[]{0,0}});
      setMeleeAnimation(6,8);
      setIdleAnimation(1,2);
      setSpecialAnimation(9,11);
      setFaceSprite(faces[0][2]);
      setActualEvolution(2);
      setStats();
      setMaxMagic(1000);
    }
  }
  
}
