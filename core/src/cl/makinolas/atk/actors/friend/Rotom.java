package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Rotom extends AbstractFriend {
 
  private TextureRegion[][] faces;
  
  public Rotom() {
    friend = Enemies.ROTOM;
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Rotom_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Rotom.png"))));
    setCutSprites(30,25);
    setWalkAnimation(1,2);
    setHurtAnimation(6);
    setMeleeAnimation(3,5);
    setIdleAnimation(0);
    setSpecialAnimation(7);
    setFaceSprite(faces[0][0]);
    initLevel(10);
    initDead();
    setActualEvolution(0);
    setMaxMagic(1000);
  }
  
  public Rotom(int level){
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
