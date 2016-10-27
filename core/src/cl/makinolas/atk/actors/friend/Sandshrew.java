package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import cl.makinolas.atk.types.GroundType;

public class Sandshrew extends AbstractFriend {

  private TextureRegion[][] faces;
  
  public Sandshrew() {
    friend = Enemies.SANDSHREW;
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Sandshrew_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Sandshrew.png"))));
    setCutSprites(26,24);
    setWalkAnimation(2,3,4,3);
    setHurtAnimation(0);
    setMeleeAnimation(5,6,7);
    setIdleAnimation(1);
    setSpecialAnimation(8,10);
    setFaceSprite(faces[0][0]);
    initLevel(10);
    initDead();
    newMonster();
    setActualEvolution(0);
    setMaxMagic(1000);
    addType(new GroundType());
  }
  
  public Sandshrew(int level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level){
   this.level = new Level(level);
   setStats();
   setHealth(getMaxHealth());
   new Evolution(this.level, 22, 1);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      friend = Enemies.SANDSLASH;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Sandslash.png"))));
      setCutSprites(33,32);
      setWalkAnimation(1,2,3,2);
      setHurtAnimation(0);
      setMeleeAnimation(4,5,6);
      setIdleAnimation(1,2,3,2);
      setSpecialAnimation(7,8);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setStats();
      setMaxMagic(1000);
    }
  }
  
  
}
