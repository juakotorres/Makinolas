package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.BombAttack;
import cl.makinolas.atk.actors.attacks.states.ThunderBoltState;
import cl.makinolas.atk.actors.attacks.states.TornadoState;
import cl.makinolas.atk.types.FlyingType;
import cl.makinolas.atk.types.GrassType;
import cl.makinolas.atk.types.IType;
import cl.makinolas.atk.types.PoisonType;
import cl.makinolas.atk.types.PsychicType;

public class Zubat extends AbstractFriend {
  
  private TextureRegion[][] faces;
  
  public Zubat() {
    friend = Enemies.ZUBAT;
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Zubat_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Zubat.png"))));
    setCutSprites(28,28);
    setWalkAnimation(1,2,3,2);
    setHurtAnimation(0);
    setMeleeAnimation(4,6);
    setIdleAnimation(1,2,3,2);
    setSpecialAnimation(7);
    setFaceSprite(faces[0][0]);
    initLevel(10);
    initDead();
    newMonster();
    setActualEvolution(0);
    setMaxMagic(1000);
    addType(new PoisonType());
    addType(new FlyingType());
  }
  
  public Zubat(int level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level){
   this.level = new Level(level);
   setStats();
   setHealth(getMaxHealth());
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
      setIdleAnimation(1,2,3,2);
      setSpecialAnimation(4);
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
      setIdleAnimation(1,2,3,2);
      setSpecialAnimation(8,10);
      setFaceSprite(faces[0][2]);
      setActualEvolution(2);
      setStats();
      setMaxMagic(1000);
    }
  }
  
  @Override
  public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source){
    return new BombAttack(new TornadoState(), myWorld, x, y, facingRight, source);
  }
  
  @Override
  public int getAttackMagicRequirement() {
	// TODO Auto-generated method stub
	return TornadoState.magicRequirement;
  }
  
}
