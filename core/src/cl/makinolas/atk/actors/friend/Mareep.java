package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import cl.makinolas.atk.types.TypeFactory;

public class Mareep extends AbstractFriend {
  
  private TextureRegion[][] faces;
  
  public Mareep() {
    friend = Enemies.MAREEP;
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Mareep_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Mareep.png"))));
    setAnimations(new int[]{29,21},
                  new int[][]{new int[]{0,1},new int[]{0,2},new int[]{0,3}},
                  new int[][]{new int[]{0,0}});
    setMeleeAnimation(4,6);
    setIdleAnimation(1,2,3);
    setSpecialAnimation(7);
    setFaceSprite(faces[0][0]);
    initLevel(5);
    initDead();
    newMonster();
    setActualEvolution(0);
    setMaxMagic(1000);
    addType(TypeFactory.getType("Electric"));
  }
  
  public Mareep(int level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level){
   this.level = new Level(level);
   setStats();
   setHealth(getMaxHealth());
   new Evolution(this.level, 16, 1);
   new Evolution(this.level, 30, 2);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      friend = Enemies.FLAAFFY;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Flaffy.png"))));
      setAnimations(new int[]{25,25},
          new int[][]{new int[]{0,1},new int[]{0,2},new int[]{0,3},new int[]{0,2}},
          new int[][]{new int[]{0,0}});
      setMeleeAnimation(4,7);
      setIdleAnimation(1,2,3,2);
      setSpecialAnimation(8,9);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setStats();
      setMaxMagic(1000);
    } else if (numberOfLevel == 2 && getActualEvolution() < 2){
      friend = Enemies.AMPHAROS;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Ampharos.png"))));
      setAnimations(new int[]{25,34},
          new int[][]{new int[]{0,4},new int[]{0,5},new int[]{0,6},new int[]{0,5}},
          new int[][]{new int[]{0,0}});
      setMeleeAnimation(7,8);
      setIdleAnimation(1,2,3);
      setSpecialAnimation(9,12);
      setFaceSprite(faces[0][2]);
      setActualEvolution(2);
      setStats();
      setMaxMagic(1000);
    }
  }
  
}

