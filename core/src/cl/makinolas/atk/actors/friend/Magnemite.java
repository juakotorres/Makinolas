package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.ThunderBolt;

public class Magnemite extends AbstractFriend {
  
  private TextureRegion[][] faces;
  
  public Magnemite(Hero hero) {
    super(hero);
    friend = Enemies.MAGNEMITE;
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Magnemite_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Magnemite.png"))));
    setCutSprites(20,22);
    setWalkAnimation(1,2,3,2);
    setHurtAnimation(0);
    setMeleeAnimation(4,4);
    setFaceSprite(faces[0][0]);
    initLevel(10);
    initDead();
    setActualEvolution(0);
    setMaxMagic(1000);
  }
  
  public Magnemite(int level){
    this(Hero.getInstance());
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level){
   this.level = new Level(level);
   setStats();
   setHealth(getMaxHealth());
   new Evolution(this.level, 30, 1);
   new Evolution(this.level, 45, 2);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      friend = Enemies.MAGNETON;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Magneton.png"))));
      setCutSprites(29,32);
      setWalkAnimation(1,2,3,2);
      setHurtAnimation(0);
      setMeleeAnimation(4,4);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setStats();
      setMaxMagic(1000);
    } else if (numberOfLevel == 2 && getActualEvolution() < 2){
      friend = Enemies.MAGNEZONE;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Magnezone.png"))));
      setCutSprites(31,30);
      setWalkAnimation(1,2,3,2);
      setHurtAnimation(0);
      setMeleeAnimation(4,4);
      setFaceSprite(faces[0][2]);
      setActualEvolution(2);
      setStats();
      setMaxMagic(1000);      
    }
  }
  
  @Override
  public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source){
    return new ThunderBolt(myWorld, x, y, facingRight, source);
  }
  
}
