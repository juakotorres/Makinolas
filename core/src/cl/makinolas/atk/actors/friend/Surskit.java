package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import cl.makinolas.atk.types.TypeFactory;

public class Surskit extends AbstractFriend {
  
  private TextureRegion[][] faces;
  
  public Surskit() {
    friend = Enemies.SURSKIT;
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Surskit_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Surskit.png"))));
    setCutSprites(22,22);
    setWalkAnimation(2,3,4);
    setHurtAnimation(0);
    setMeleeAnimation(2,3,4);
    setIdleAnimation(1);
    setSpecialAnimation(5,6);
    setFaceSprite(faces[0][0]);
    initLevel(10);
    initDead();
    newMonster();
    setActualEvolution(0);
    setMaxMagic(1000);
    addType(TypeFactory.getType("Bug"));
    addType(TypeFactory.getType("Water"));
  }
  
  public Surskit(int level){
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
      friend = Enemies.MASQUERAIN;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Masquerain.png"))));
      setCutSprites(30,26);
      setWalkAnimation(1,2,3,2);
      setHurtAnimation(0);
      setMeleeAnimation(4,5,6,7);
      setIdleAnimation(1,2,3,2);
      setSpecialAnimation(8,9);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setStats();
      setMaxMagic(1000);
      resetType();
      addType(TypeFactory.getType("Bug"));
      addType(TypeFactory.getType("Flying"));
    }
  }
}
