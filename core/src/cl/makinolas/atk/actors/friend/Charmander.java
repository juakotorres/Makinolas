package cl.makinolas.atk.actors.friend;

import cl.makinolas.atk.actors.attacks.ParabolicAttack;
import cl.makinolas.atk.actors.attacks.states.FireballState;
import cl.makinolas.atk.types.TypeFactory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;

public class Charmander extends AbstractFriend {
  
  private TextureRegion[][] faces;
  
  public Charmander() {
    friend = Enemies.CHARMANDER;
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Charmander_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/charmander.png"))));
    setAnimations(new int[]{22,22},
                  new int[][]{new int[]{0,0},new int[]{0,1},new int[]{0,2},new int[]{0,1}},
                  new int[][]{new int[]{0,7}});
    setMeleeAnimation(3,5);
    setIdleAnimation(0,1,2,1);
    setSpecialAnimation(6);
    setFaceSprite(faces[0][0]);
    initLevel(5);
    initDead();
    newMonster();
    setActualEvolution(0);
    setMaxMagic(1000);
    addType(TypeFactory.getType("Fire"));
  }
  
  public Charmander(int level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level){
   this.level = new Level(level);
   setStats();
   setHealth(getMaxHealth());
   new Evolution(this.level, 16, 1);
   new Evolution(this.level, 36, 2);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      friend = Enemies.CHARMELEON;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/charmeleon.png"))));
      setAnimations(new int[]{34,31},
          new int[][]{new int[]{0,3},new int[]{0,4},new int[]{0,5},new int[]{0,4}},
          new int[][]{new int[]{0,0}});
      setMeleeAnimation(6,8);
      setIdleAnimation(1,2);
      setSpecialAnimation(9,10);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setStats();
      setMaxMagic(1000);
    } else if (numberOfLevel == 2 && getActualEvolution() < 2){
      friend = Enemies.CHARIZARD;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Charizard.png"))));
      setAnimations(new int[]{32,32},
          new int[][]{new int[]{0,1},new int[]{0,2},new int[]{0,3},new int[]{0,2}},
          new int[][]{new int[]{0,0}});
      setMeleeAnimation(4,7);
      setIdleAnimation(1,2,3,2);
      setSpecialAnimation(8,9);
      setFaceSprite(faces[0][2]);
      setActualEvolution(2);
      setStats();
      setMaxMagic(1000);
      addType(TypeFactory.getType("Flying"));
    }
  }
  
  
  @Override
  public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source){
    return new ParabolicAttack(new FireballState(),myWorld, x, y, facingRight, source);
  }
  
  @Override
  public int getAttackMagicRequirement() {
	return FireballState.getMagicRequirement();
  }  
  
}
