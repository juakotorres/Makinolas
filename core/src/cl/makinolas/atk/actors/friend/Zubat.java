package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.Tornado;

public class Zubat extends AbstractFriend {
  
  private TextureRegion[][] faces;
  
  public Zubat() {
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Zubat_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Zubat.png"))));
    setCutSprites(28,28);
    setWalkAnimation(1,2,3,2);
    setHurtAnimation(0);
    setMeleeAnimation(4,6);
    setFaceSprite(faces[0][0]);
    initLevel(10);
    initDead();
    setActualEvolution(0);
    setMaxHealth(30);
    setMaxMagic(100);
  }
  
  public Zubat(float level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(float level){
   this.level = new Level(level);
   new Evolution(this.level, 20, 1);
   new Evolution(this.level, 30, 2);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Golbat.png"))));
      setCutSprites(20,32);
      setWalkAnimation(1,2,3,2);
      setHurtAnimation(0);
      setMeleeAnimation(4,4);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setMaxHealth(60);
      setMaxMagic(100);
    } else if (numberOfLevel == 2 && getActualEvolution() < 2){
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Crobat.png"))));
      setCutSprites(26,28);
      setWalkAnimation(1,2,3,2);
      setHurtAnimation(0);
      setMeleeAnimation(4,7);
      setFaceSprite(faces[0][2]);
      setActualEvolution(2);
      setMaxHealth(80);
      setMaxMagic(100);
      
    }
  }
  
  @Override
  public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source){
    return new Tornado(myWorld, x, y, facingRight, source);
  }
}
