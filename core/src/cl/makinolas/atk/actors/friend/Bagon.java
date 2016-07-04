package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.DragonBreath;

public class Bagon extends AbstractFriend {
  
  private TextureRegion[][] faces;
  
  public Bagon() {
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Bagon_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Bagon.png"))));
    setCutSprites(25,26);
    setWalkAnimation(3,4,5,4);
    setHurtAnimation(0);
    setMeleeAnimation(6,9);
    setFaceSprite(faces[0][0]);
    initLevel(10);
    initDead();
    setActualEvolution(0);
    setMaxHealth(30);
    setMaxMagic(100);
  }
  
  public Bagon(int level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level){
   this.level = new Level(level);
   new Evolution(this.level, 30, 1);
   new Evolution(this.level, 50, 2);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Shelgon.png"))));
      setCutSprites(28,29);
      setWalkAnimation(3,4,5,4);
      setHurtAnimation(0);
      setMeleeAnimation(6,9);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setMaxHealth(60);
      setMaxMagic(100);
    } else if (numberOfLevel == 2 && getActualEvolution() < 2){
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Salamence.png"))));
      setCutSprites(55,48);
      setWalkAnimation(3,4);
      setHurtAnimation(0);
      setMeleeAnimation(5,9);
      setFaceSprite(faces[0][2]);
      setActualEvolution(2);
      setMaxHealth(120);
      setMaxMagic(100);
      
    }
  }
  
  @Override
  public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source){
    return new DragonBreath(myWorld, x, y, facingRight, source);
  }
}
