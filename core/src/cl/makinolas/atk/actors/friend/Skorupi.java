package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import cl.makinolas.atk.types.BugType;
import cl.makinolas.atk.types.PoisonType;
import cl.makinolas.atk.types.TypeFactory;

public class Skorupi extends AbstractFriend {
  
  private TextureRegion[][] faces;
  
  public Skorupi() {
    friend = Enemies.SKORUPI;
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Skorupi_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Skorupi.png"))));
    setCutSprites(27,21);
    setWalkAnimation(4,5,6,5);
    setHurtAnimation(0);
    setMeleeAnimation(7,10);
    setIdleAnimation(1,2,3,2);
    setSpecialAnimation(11,12);
    setFaceSprite(faces[0][0]);
    initLevel(10);
    initDead();
    newMonster();
    setActualEvolution(0);
    setMaxMagic(1000);
    addType(new BugType());
    addType(new PoisonType());
  }
  
  public Skorupi(int level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level){
   this.level = new Level(level);
   setStats();
   setHealth(getMaxHealth());
   new Evolution(this.level, 40, 1);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      friend = Enemies.DRAPION;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Drapion.png"))));
      setCutSprites(42,34);
      setWalkAnimation(3,4);
      setHurtAnimation(0);
      setMeleeAnimation(5,8);
      setIdleAnimation(1,2);
      setSpecialAnimation(9,11);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setStats();
      setMaxMagic(1000);
      resetType();
      addType(TypeFactory.getType("Bug"));
      addType(TypeFactory.getType("Dark"));
    }
  }

}
