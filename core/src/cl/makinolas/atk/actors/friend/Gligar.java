package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import cl.makinolas.atk.types.FlyingType;
import cl.makinolas.atk.types.GroundType;

public class Gligar extends AbstractFriend {
  
  private TextureRegion[][] faces;
  
  public Gligar() {
    friend = Enemies.GLIGAR;
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Gligar_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Gligar.png"))));
    setCutSprites(36,32);
    setWalkAnimation(1,2,3,2);
    setHurtAnimation(0);
    setMeleeAnimation(4,7);
    setIdleAnimation(1,2,3,2);
    setSpecialAnimation(8,10);
    setFaceSprite(faces[0][0]);
    initLevel(10);
    initDead();
    newMonster();
    setActualEvolution(0);
    setMaxMagic(1000);
    addType(GroundType.getInstance());
    addType(FlyingType.getInstance());
  }
  
  public Gligar(int level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level){
   this.level = new Level(level);
   setStats();
   setHealth(getMaxHealth());
   new Evolution(this.level, 30, 1);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      friend = Enemies.GLISCOR;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Gliscor.png"))));
      setCutSprites(30,34);
      setWalkAnimation(1,2,3,2);
      setHurtAnimation(0);
      setMeleeAnimation(4,5,6,7);
      setIdleAnimation(1,2,3,2);
      setSpecialAnimation(8,9);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setStats();
      setMaxMagic(1000);
    }
  }
}
