package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import cl.makinolas.atk.types.TypeFactory;

public class Spiritomb extends AbstractFriend {

  private TextureRegion[][] faces;
  
  public Spiritomb() {
    friend = Enemies.SPIRITOMB;
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Spiritomb_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Spiritomb.png"))));
    setCutSprites(17,24);
    setWalkAnimation(1,2,3,2);
    setHurtAnimation(0);
    setMeleeAnimation(1,2,3,2);
    setIdleAnimation(1,2,3,2);
    setSpecialAnimation(4,5,6,7);
    setFaceSprite(faces[0][0]);
    initLevel(10);
    initDead();
    newMonster();
    setActualEvolution(0);
    setMaxMagic(1000);
    addType(TypeFactory.getType("Ghost"));
    addType(TypeFactory.getType("Dark"));
  }
  
  public Spiritomb(int level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level){
   this.level = new Level(level);
   setStats();
   setHealth(getMaxHealth());
  }
  
}
