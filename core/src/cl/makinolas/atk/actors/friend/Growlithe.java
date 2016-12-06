package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import cl.makinolas.atk.types.FireType;

public class Growlithe extends AbstractFriend {

  private TextureRegion[][] faces;
  
  public Growlithe() {
    friend = Enemies.GROWLITHE;
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Growlithe_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Growlithe.png"))));
    setCutSprites(32,27);
    setWalkAnimation(2,3,4,3);
    setHurtAnimation(0);
    setMeleeAnimation(5,8);
    setIdleAnimation(1);
    setSpecialAnimation(9,10);
    setFaceSprite(faces[0][0]);
    initLevel(10);
    initDead();
    newMonster();
    setActualEvolution(0);
    setMaxMagic(1000);
    addType(FireType.getInstance());
  }
  
  public Growlithe(int level){
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
      friend = Enemies.ARCANINE;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Arcanine.png"))));
      setCutSprites(35,30);
      setWalkAnimation(2,3,4,3);
      setHurtAnimation(0);
      setMeleeAnimation(5,8);
      setIdleAnimation(1);
      setSpecialAnimation(9);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setStats();
      setMaxMagic(1000);
    }
  }
}
