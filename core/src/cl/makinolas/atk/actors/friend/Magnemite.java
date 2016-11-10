package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.BombAttack;
import cl.makinolas.atk.actors.attacks.states.ThunderBoltState;
import cl.makinolas.atk.types.ElectricType;
import cl.makinolas.atk.types.IType;
import cl.makinolas.atk.types.SteelType;
import cl.makinolas.atk.types.WaterType;

public class Magnemite extends AbstractFriend {
  
  private TextureRegion[][] faces;
  
  public Magnemite() {
    friend = Enemies.MAGNEMITE;
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Magnemite_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Magnemite.png"))));
    setCutSprites(20,22);
    setWalkAnimation(1,2,3,2);
    setHurtAnimation(0);
    setMeleeAnimation(4,4);
    setIdleAnimation(1,2,3,2);
    setSpecialAnimation(5,6);
    setFaceSprite(faces[0][0]);
    initLevel(10);
    initDead();
    newMonster();
    setActualEvolution(0);
    setMaxMagic(1000);
    addType(new ElectricType());
    addType(new SteelType());
  }
  
  public Magnemite(int level){
    this();
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
      setIdleAnimation(1,2,3,2);
      setSpecialAnimation(5,6);
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
      setIdleAnimation(1,2,3,2);
      setSpecialAnimation(4);
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
    return new BombAttack(new ThunderBoltState(), myWorld, x, y, facingRight, source);
  }
  
  @Override
  public int getAttackMagicRequirement() {
	 // TODO Auto-generated method stub
	 return ThunderBoltState.magicRequirement;
  }
  
}
