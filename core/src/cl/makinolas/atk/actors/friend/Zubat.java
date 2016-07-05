package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.Tornado;

public class Zubat extends AbstractFriend {
  
  private TextureRegion[][] faces;
  
  public Zubat(Hero hero) {
    super(hero);
    friend = Enemies.ZUBAT;
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
    setStats();
    setMaxMagic(1000);
  }
  
  public Zubat(int level, Hero hero){
    this(hero);
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level){
   this.level = new Level(level);
   new Evolution(this.level, 20, 1);
   new Evolution(this.level, 30, 2);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      friend = Enemies.GOLBAT;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Golbat.png"))));
      setCutSprites(20,32);
      setWalkAnimation(1,2,3,2);
      setHurtAnimation(0);
      setMeleeAnimation(4,4);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setStats();
      setMaxMagic(1000);
    } else if (numberOfLevel == 2 && getActualEvolution() < 2){
      friend = Enemies.CROBAT;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Crobat.png"))));
      setCutSprites(26,28);
      setWalkAnimation(1,2,3,2);
      setHurtAnimation(0);
      setMeleeAnimation(4,7);
      setFaceSprite(faces[0][2]);
      setActualEvolution(2);
      setStats();
      setMaxMagic(1000);
    }
  }
  
  @Override
  public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source){
    return new Tornado(myWorld, x, y, facingRight, source);
  }
}
