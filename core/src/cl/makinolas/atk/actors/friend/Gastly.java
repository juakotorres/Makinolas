package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import cl.makinolas.atk.actors.Hero;

public class Gastly extends AbstractFriend {
  
 private TextureRegion[][] faces;
  
  public Gastly(Hero hero) {
    super(hero);
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Gastly_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Gastly.png"))));
    setAnimations(new int[]{30,30},
                  new int[][]{new int[]{0,1},new int[]{0,2},new int[]{0,3}},
                  new int[][]{new int[]{0,0}});
    setMeleeAnimation(4,5);
    setFaceSprite(faces[0][0]);
    initLevel(10);
    initDead();
    setActualEvolution(0);
    setMaxHealth(30);
    setMaxMagic(100);
    friend = Enemies.GASTLY;
  }
  
  public Gastly(int level, Hero hero){
    this(hero);
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level){
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
      setMeleeAnimation(6,10);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setMaxHealth(70);
      setMaxMagic(100);
      friend = Enemies.HAUNTER;
    }
  }
  
}
