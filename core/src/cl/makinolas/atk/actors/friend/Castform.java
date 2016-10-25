package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import cl.makinolas.atk.types.NormalType;

public class Castform extends AbstractFriend {

  private TextureRegion[][] faces;
  
  public Castform() {
    friend = Enemies.CASTFORM;
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Castform_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/CastformN.png"))));
    setCutSprites(13,17);
    setWalkAnimation(1,2);
    setHurtAnimation(0);
    setMeleeAnimation(3,4);
    setIdleAnimation(1,2);
    setSpecialAnimation(3,4);
    setFaceSprite(faces[0][0]);
    initLevel(10);
    initDead();
    setActualEvolution(0);
    setMaxMagic(1000);
    addType(new NormalType());
  }
  
  public Castform(int level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level){
   this.level = new Level(level);
   setStats();
   setHealth(getMaxHealth());
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1){
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/CastformS.png"))));
      setCutSprites(18,21);
      setWalkAnimation(1,2,3);
      setHurtAnimation(0);
      setMeleeAnimation(4,5);
      setIdleAnimation(1,2,3);
      setSpecialAnimation(4,5);
      setFaceSprite(faces[0][3]);
      setActualEvolution(1);
      setStats();
      setMaxMagic(1000);
    } else if(numberOfLevel == 2){
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/CastformW.png"))));
      setCutSprites(20,24);
      setWalkAnimation(1,2,3);
      setHurtAnimation(0);
      setMeleeAnimation(4,5);
      setIdleAnimation(1,2,3);
      setSpecialAnimation(4,5);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setStats();
      setMaxMagic(1000);
    } else if(numberOfLevel == 3){
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/CastformC.png"))));
      setCutSprites(22,23);
      setWalkAnimation(1,2);
      setHurtAnimation(0);
      setMeleeAnimation(3,4);
      setIdleAnimation(1,2);
      setSpecialAnimation(3,4);
      setFaceSprite(faces[0][2]);
      setActualEvolution(1);
      setStats();
      setMaxMagic(1000);
    } else {
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/CastformN.png"))));
      setCutSprites(13,17);
      setWalkAnimation(1,2);
      setHurtAnimation(0);
      setMeleeAnimation(3,4);
      setIdleAnimation(1,2);
      setSpecialAnimation(3,4);
      setFaceSprite(faces[0][0]);
      setActualEvolution(0);
      setStats();
      setMaxMagic(1000);
    }
  }
}
