package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.AquaAttack;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.states.AquaAttackState;
import cl.makinolas.atk.types.DragonType;
import cl.makinolas.atk.types.GroundType;
import cl.makinolas.atk.types.TypeFactory;

public class Gible extends AbstractFriend {
  
  private TextureRegion[][] faces;
  
  public Gible() {
    friend = Enemies.GIBLE;
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Gible_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/gible.png"))));
    setAnimations(new int[]{31,29},
                  new int[][]{new int[]{0,4},new int[]{0,5},new int[]{0,6},new int[]{0,5}},
                  new int[][]{new int[]{0,0}});
    setMeleeAnimation(7,9);
    setIdleAnimation(1,3);
    setSpecialAnimation(10,11);
    setFaceSprite(faces[0][0]);
    initLevel(5);
    initDead();
    newMonster();
    setActualEvolution(0);
    setMaxMagic(1000);
    addType(TypeFactory.getType("Dragon"));
    addType(TypeFactory.getType("Ground"));
    }

  public Gible(int level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level){
   this.level = new Level(level);
   setStats();
   setHealth(getMaxHealth());
   new Evolution(this.level, 34, 1);
   new Evolution(this.level, 50, 1);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      friend = Enemies.GABITE;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Gabite.png"))));
      setAnimations(new int[]{39,34},
          new int[][]{new int[]{0,4},new int[]{0,5},new int[]{0,6},new int[]{0,5}},
          new int[][]{new int[]{0,0}});
      setMeleeAnimation(7,9);
      setIdleAnimation(1,3);
      setSpecialAnimation(10);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setStats();
      setMaxMagic(1000);
    } else if (numberOfLevel == 2 && getActualEvolution() < 2){
      friend = Enemies.GARCHOMP;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Garchomp.png"))));
      setAnimations(new int[]{43,40},
          new int[][]{new int[]{0,4},new int[]{0,5},new int[]{0,6},new int[]{0,5}},
          new int[][]{new int[]{0,0}});
      setMeleeAnimation(7,10);
      setIdleAnimation(1,3);
      setSpecialAnimation(11,12);
      setFaceSprite(faces[0][2]);
      setActualEvolution(2);
      setStats();
      setMaxMagic(1000);
    }
  }
  
  @Override
  public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source){
    return new AquaAttack(myWorld, x, y, facingRight, source);
  }
  
  @Override
  public int getAttackMagicRequirement() {
	// TODO Auto-generated method stub
	return AquaAttackState.getMagicRequirement();
  }
  
}
