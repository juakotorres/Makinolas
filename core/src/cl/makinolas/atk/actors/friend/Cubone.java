package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.BoomerangAttack;
import cl.makinolas.atk.actors.attacks.states.BoneAttackState;
import cl.makinolas.atk.types.GroundType;

public class Cubone extends AbstractFriend {

  private TextureRegion[][] faces;
  
  public Cubone() {
    friend = Enemies.CUBONE;
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Cubone_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Cubone.png"))));
    setCutSprites(25,24);
    setWalkAnimation(3,4,5,4);
    setHurtAnimation(0);
    setMeleeAnimation(6,8);
    setIdleAnimation(1,2);
    setSpecialAnimation(9);
    setFaceSprite(faces[0][0]);
    initLevel(10);
    initDead();
    newMonster();
    setActualEvolution(0);
    setMaxMagic(1000);
    addType(GroundType.getInstance());
  }
  
  public Cubone(int level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level){
   this.level = new Level(level);
   setStats();
   setHealth(getMaxHealth());
   new Evolution(this.level, 28, 1);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      friend = Enemies.MAROWAK;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Marowak.png"))));
      setCutSprites(31,29);
      setWalkAnimation(3,4,5,4);
      setHurtAnimation(0);
      setMeleeAnimation(6,8);
      setIdleAnimation(1,2);
      setSpecialAnimation(9);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setStats();
      setMaxMagic(1000);
    }
  }
  
  @Override
  public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source){
    return new BoomerangAttack(new BoneAttackState(), myWorld, x, y, facingRight, source, false);
  }
  
  @Override
  public int getAttackMagicRequirement() {
	// TODO Auto-generated method stub
	return BoneAttackState.magicRequirement;
  }
  
}
