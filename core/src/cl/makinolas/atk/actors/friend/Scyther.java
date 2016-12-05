package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.CloseRangeAttack;
import cl.makinolas.atk.actors.attacks.states.VineWhipState;
import cl.makinolas.atk.types.BugType;
import cl.makinolas.atk.types.FlyingType;
import cl.makinolas.atk.types.SteelType;

public class Scyther extends AbstractFriend {
  
  private TextureRegion[][] faces;
  
  public Scyther() {
    friend = Enemies.SCYTHER;
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Scyther_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Scyther.png"))));
    setAnimations(new int[]{43,35},
                  new int[][]{new int[]{0,3},new int[]{0,4},new int[]{0,5},new int[]{0,4}},
                  new int[][]{new int[]{0,0}});
    setMeleeAnimation(6,8);
    setIdleAnimation(1,2);
    setSpecialAnimation(9,10);
    setFaceSprite(faces[0][0]);
    initLevel(5);
    initDead();
    newMonster();
    setActualEvolution(0);
    setMaxMagic(1000);
    addType(BugType.getInstance());
    addType(FlyingType.getInstance());
  }
  
  public Scyther(int level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level){
   this.level = new Level(level);
   setStats();
   setHealth(getMaxHealth());
   new Evolution(this.level, 30, 1);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      friend = Enemies.SCIZOR;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Scyzor.png"))));
      setAnimations(new int[]{35,34},
          new int[][]{new int[]{0,3},new int[]{0,4},new int[]{0,5},new int[]{0,4}},
          new int[][]{new int[]{0,0}});
      setMeleeAnimation(6,10);
      setIdleAnimation(1,2);
      setSpecialAnimation(11,12);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setStats();
      setMaxMagic(1000);
      resetType();
      addType(BugType.getInstance());
      addType(SteelType.getInstance());      
    } 
  }
  
  @Override
  public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source){
    return new CloseRangeAttack(new VineWhipState(), myWorld, x, y, facingRight, source);
  }
  
  @Override
  public int getAttackMagicRequirement() {
	// TODO Auto-generated method stub
	return VineWhipState.getMagicRequirement();
  }
  
}
