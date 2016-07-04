package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import cl.makinolas.atk.actors.Hero;

public class Mareep extends AbstractFriend {
  
  private TextureRegion[][] faces;
  
  public Mareep(Hero hero) {
    super(hero);
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Mareep_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Mareep.png"))));
    setAnimations(new int[]{29,21},
                  new int[][]{new int[]{0,1},new int[]{0,2},new int[]{0,3}},
                  new int[][]{new int[]{0,0}});
    setMeleeAnimation(4,6);
    setFaceSprite(faces[0][0]);
    initLevel(5);
    initDead();
    setActualEvolution(0);
    setMaxHealth(30);
    setMaxMagic(100);
    friend = Enemies.MAREEP;
  }
  
  public Mareep(int level, Hero hero){
    this(hero);
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level){
   this.level = new Level(level);
   new Evolution(this.level, 16, 1);
   new Evolution(this.level, 30, 2);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Flaffy.png"))));
      setAnimations(new int[]{25,25},
          new int[][]{new int[]{0,1},new int[]{0,2},new int[]{0,3},new int[]{0,2}},
          new int[][]{new int[]{0,0}});
      setMeleeAnimation(4,7);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setMaxHealth(60);
      setMaxMagic(100);
      friend = Enemies.FLAAFFY;
    } else if (numberOfLevel == 2 && getActualEvolution() < 2){
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Ampharos.png"))));
      setAnimations(new int[]{25,34},
          new int[][]{new int[]{0,4},new int[]{0,5},new int[]{0,6},new int[]{0,5}},
          new int[][]{new int[]{0,0}});
      setMeleeAnimation(7,8);
      setFaceSprite(faces[0][2]);
      setActualEvolution(2);
      setMaxHealth(120);
      setMaxMagic(100);
      friend = Enemies.AMPHAROS;
    }
  }
  
}

