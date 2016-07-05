package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import cl.makinolas.atk.actors.Hero;

public class Igglybuff extends AbstractFriend {
  
  private TextureRegion[][] faces;
  
  public Igglybuff(Hero hero) {
    super(hero);
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Igglypuff_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Igglypuff.png"))));
    setAnimations(new int[]{17,18},
                  new int[][]{new int[]{0,3},new int[]{0,4},new int[]{0,5}},
                  new int[][]{new int[]{0,0}});
    setMeleeAnimation(6,8);
    setFaceSprite(faces[0][0]);
    initLevel(5);
    initDead();
    setActualEvolution(0);
    setStats();
    setMaxMagic(1000);
    friend = Enemies.IGGLYBUFF;
  }
  
  public Igglybuff(int level, Hero hero){
    this(hero);
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level){
   this.level = new Level(level);
   new Evolution(this.level, 16, 1);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Jigglypuff.png"))));
      setAnimations(new int[]{23,21},
          new int[][]{new int[]{0,3},new int[]{0,4},new int[]{0,5}},
          new int[][]{new int[]{0,0}});
      setMeleeAnimation(6,7);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setStats();
      setMaxMagic(1000);
      friend = Enemies.JIGGLYPUFF;
    } else if (numberOfLevel == 2 && getActualEvolution() < 2){
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Wigglypuff.png"))));
      setAnimations(new int[]{26,25},
          new int[][]{new int[]{0,2},new int[]{0,3},new int[]{0,4},new int[]{0,3}},
          new int[][]{new int[]{0,0}});
      setMeleeAnimation(6,8);
      setFaceSprite(faces[0][2]);
      setActualEvolution(2);
      setStats();
      setMaxMagic(1000);
      friend = Enemies.WIGGLYTUFF;
    }
  }
  
}
