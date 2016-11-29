package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.DroppingAttack;
import cl.makinolas.atk.actors.attacks.states.FallingLeafState;
import cl.makinolas.atk.types.BugType;
import cl.makinolas.atk.types.FlyingType;

public class Caterpie extends AbstractFriend {

  private TextureRegion[][] faces;
  
  public Caterpie() {
    friend = Enemies.CATERPIE;
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Caterpie_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Caterpie.png"))));
    setCutSprites(24,22);
    setWalkAnimation(4,5,6,5);
    setHurtAnimation(0);
    setMeleeAnimation(7,10);
    setIdleAnimation(1,2,3,2);
    setSpecialAnimation(11,13);
    setFaceSprite(faces[0][0]);
    initLevel(10);
    initDead();
    newMonster();
    setActualEvolution(0);
    setMaxMagic(1000);
    addType(BugType.getInstance());
  }
  
  public Caterpie(int level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level){
   this.level = new Level(level);
   setStats();
   setHealth(getMaxHealth());
   new Evolution(this.level, 6, 1);
   new Evolution(this.level, 10, 2);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      friend = Enemies.METAPOD;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Metapod.png"))));
      setCutSprites(34,26);
      setWalkAnimation(4,5,6,5);
      setHurtAnimation(0);
      setMeleeAnimation(7,10);
      setIdleAnimation(1,2,3,2);
      setSpecialAnimation(11);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setStats();
      setMaxMagic(1000);
    } else if (numberOfLevel == 2 && getActualEvolution() < 2){
      friend = Enemies.BUTTERFREE;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Butterfree.png"))));
      setCutSprites(27,28);
      setWalkAnimation(1,2,3,2);
      setHurtAnimation(0);
      setMeleeAnimation(4,5);
      setIdleAnimation(1,2,3,2);
      setSpecialAnimation(6,7);
      setFaceSprite(faces[0][2]);
      setActualEvolution(2);
      setStats();
      setMaxMagic(1000);
      addType(FlyingType.getInstance());
    }
  }
  
  
  @Override
  public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source){
    return new DroppingAttack(new FallingLeafState(), myWorld, x, y, facingRight, source, true);
  }
  
  @Override
  public int getAttackMagicRequirement() {
	// TODO Auto-generated method stub
	return FallingLeafState.magicRequirement;
  }

}
