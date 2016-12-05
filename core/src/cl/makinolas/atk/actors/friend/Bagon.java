package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.ShootAttack;
import cl.makinolas.atk.actors.attacks.states.DragonBreathState;
import cl.makinolas.atk.types.DragonType;
import cl.makinolas.atk.types.FlyingType;

public class Bagon extends AbstractFriend {
  
  private TextureRegion[][] faces;
  
  public Bagon() {
    friend = Enemies.BAGON;
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Bagon_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Bagon.png"))));
    setCutSprites(25,26);
    setIdleAnimation(1,2);
    setWalkAnimation(3,4,5,4);
    setHurtAnimation(0);
    setMeleeAnimation(6,9);
    setSpecialAnimation(10,11);
    setFaceSprite(faces[0][0]);
    initLevel(10);
    initDead();
    newMonster();
    setActualEvolution(0);
    setMaxMagic(1000);
    addType(DragonType.getInstance());
  }
  
  public Bagon(int level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level){
   this.level = new Level(level);
   setStats();
   setHealth(getMaxHealth());
   new Evolution(this.level, 30, 1);
   new Evolution(this.level, 50, 2);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      friend = Enemies.SHELGON;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Shelgon.png"))));
      setCutSprites(28,29);
      setIdleAnimation(1,2);
      setWalkAnimation(3,4,5,4);
      setHurtAnimation(0);
      setMeleeAnimation(6,9);
      setSpecialAnimation(10,11);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setStats();
      setMaxMagic(1000);
    } else if (numberOfLevel == 2 && getActualEvolution() < 2){
      friend = Enemies.SALAMENCE;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Salamence.png"))));
      setCutSprites(55,48);
      setIdleAnimation(1,2);
      setWalkAnimation(3,4);
      setHurtAnimation(0);
      setMeleeAnimation(5,9);
      setSpecialAnimation(10,11);
      setFaceSprite(faces[0][2]);
      setActualEvolution(2);
      setStats();
      setMaxMagic(1000);
      addType(FlyingType.getInstance());
    }
  }
  
  @Override
  public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source){
    return new ShootAttack(new DragonBreathState(), myWorld, x, y, facingRight, source);
  }

  @Override
  public int getAttackMagicRequirement() {
	// TODO Auto-generated method stub
	return DragonBreathState.getMagicRequirement();
  }

}
