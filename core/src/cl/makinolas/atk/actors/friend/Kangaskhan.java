package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Kangaskhan extends AbstractFriend {

  private TextureRegion[][] faces;
  
  public Kangaskhan() {
    friend = Enemies.KANGASKHAN;
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Kangaskhan_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Kangaskhan.png"))));
    setCutSprites(32,32);
    setWalkAnimation(4,5,6,5);
    setHurtAnimation(0);
    setMeleeAnimation(7,11);
    setIdleAnimation(1,2,3);
    setSpecialAnimation(12,13);
    setFaceSprite(faces[0][0]);
    initLevel(10);
    initDead();
    newMonster();
    setActualEvolution(0);
    setMaxMagic(1000);
  }
  
  public Kangaskhan(int level){
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
