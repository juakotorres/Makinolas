package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import cl.makinolas.atk.types.FireType;

public class Vulpix extends AbstractFriend {

  private TextureRegion[][] faces;
  
  public Vulpix() {
    friend = Enemies.VULPIX;
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Vulpix_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Vulpix.png"))));
    setCutSprites(21,22);
    setWalkAnimation(3,4,5,4);
    setHurtAnimation(0);
    setMeleeAnimation(6,7,8);
    setIdleAnimation(1,2);
    setSpecialAnimation(9,10);
    setFaceSprite(faces[0][0]);
    initLevel(10);
    initDead();
    newMonster();
    setActualEvolution(0);
    setMaxMagic(1000);
    addType(new FireType());
  }
  
  public Vulpix(int level){
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
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      friend = Enemies.NINETALES;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Ninetales.png"))));
      setCutSprites(29,25);
      setWalkAnimation(3,4,5,4);
      setHurtAnimation(0);
      setMeleeAnimation(6,7);
      setIdleAnimation(1,2);
      setSpecialAnimation(8,12);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setStats();
      setMaxMagic(1000);
    }
  }
}
