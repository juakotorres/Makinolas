package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import cl.makinolas.atk.types.TypeFactory;

public class Beldum extends AbstractFriend {
  
  private TextureRegion[][] faces;
  
  public Beldum() {
    friend = Enemies.BELDUM;
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Beldum_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Beldum.png"))));
    setCutSprites(30,28);
    setWalkAnimation(1,2,3,2);
    setHurtAnimation(0);
    setMeleeAnimation(4,6);
    setIdleAnimation(1,2,3,2);
    setSpecialAnimation(4,6);
    setFaceSprite(faces[0][0]);
    initLevel(10);
    initDead();
    newMonster();
    setActualEvolution(0);
    setMaxMagic(1000);
    addType(TypeFactory.getType("Steel"));
    addType(TypeFactory.getType("Psychic"));
  }
  
  public Beldum(int level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level){
   this.level = new Level(level);
   setStats();
   setHealth(getMaxHealth());
   new Evolution(this.level, 20, 1);
   new Evolution(this.level, 45, 2);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      friend = Enemies.METANG;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Metang.png"))));
      setCutSprites(39,31);
      setWalkAnimation(1,2,3,2);
      setHurtAnimation(0);
      setMeleeAnimation(4,10);
      setIdleAnimation(1,2,3,2);
      setSpecialAnimation(11,13);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setStats();
      setMaxMagic(1000);
    } else if (numberOfLevel == 2 && getActualEvolution() < 2){
      friend = Enemies.METAGROSS;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Metagross.png"))));
      setCutSprites(50,32);
      setWalkAnimation(3,4,5,4);
      setHurtAnimation(0);
      setMeleeAnimation(6,7);
      setIdleAnimation(1,2);
      setSpecialAnimation(1,2);
      setFaceSprite(faces[0][2]);
      setActualEvolution(2);
      setStats();
      setMaxMagic(1000);
    }
  }
  
}
