package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Geodude extends AbstractFriend {
  
  private TextureRegion[][] faces;
  
  public Geodude() {
    friend = Enemies.GEODUDE;
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Geodude_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Geodude.png"))));
    setCutSprites(37, 23);
    setWalkAnimation(2,3,4,3);
    setHurtAnimation(0);
    setMeleeAnimation(5,7);
    setIdleAnimation(1);
    setSpecialAnimation(8,11);
    setFaceSprite(faces[0][0]);
    initLevel(10);
    initDead();
    newMonster();
    setActualEvolution(0);
    setMaxMagic(1000);
  }
  
  public Geodude(int level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level){
   this.level = new Level(level);
   setStats();
   setHealth(getMaxHealth());
   new Evolution(this.level, 25, 1);
   new Evolution(this.level, 35, 2);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      friend = Enemies.GRAVELER;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Graveler.png"))));
      setCutSprites(30,29);
      setWalkAnimation(3,4,5,4);
      setHurtAnimation(0);
      setMeleeAnimation(6,8);
      setIdleAnimation(1,2);
      setSpecialAnimation(9,10);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setStats();
      setMaxMagic(1000);
    } else if (numberOfLevel == 2 && getActualEvolution() < 2){
      friend = Enemies.GOLEM;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Golem.png"))));
      setCutSprites(26,25);
      setWalkAnimation(3,4,5,4);
      setHurtAnimation(0);
      setMeleeAnimation(6,9);
      setIdleAnimation(1,2);
      setSpecialAnimation(10,11);
      setFaceSprite(faces[0][2]);
      setActualEvolution(2);
      setStats();
      setMaxMagic(1000);
    }
  }
  
}
