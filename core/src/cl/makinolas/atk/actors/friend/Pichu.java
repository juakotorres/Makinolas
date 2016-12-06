package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.BombAttack;
import cl.makinolas.atk.actors.attacks.states.ThunderBoltState;
import cl.makinolas.atk.types.TypeFactory;

public class Pichu extends AbstractFriend {
  
  private TextureRegion[][] faces;
  
  public Pichu() {
    friend = Enemies.PICHU;
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Pichu_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Pichu.png"))));
    setAnimations(new int[]{26,28},
                  new int[][]{new int[]{0,4},new int[]{0,5}},
                  new int[][]{new int[]{0,3}});
    setMeleeAnimation(2,2);
    setIdleAnimation(0,1);
    setSpecialAnimation(2);
    setFaceSprite(faces[0][0]);
    initLevel(5);
    initDead();
    newMonster();
    setActualEvolution(0);
    setMaxMagic(1000);
    addType(TypeFactory.getType("Electric"));
    setStats();
  }
  
  public Pichu(int level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level){
   this.level = new Level(level);
   setStats();
   setHealth(getMaxHealth());
   new Evolution(this.level, 10, 1);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      friend = Enemies.PIKACHU;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Pikachu.png"))));
      setAnimations(new int[]{31,25},
          new int[][]{new int[]{0,3},new int[]{0,4},new int[]{0,5},new int[]{0,4}},
          new int[][]{new int[]{0,0}});
      setMeleeAnimation(1,2);
      setIdleAnimation(1,2);
      setSpecialAnimation(6,7);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setStats();
      setMaxMagic(1000);
    } else if (numberOfLevel == 2 && getActualEvolution() < 2){
      friend = Enemies.RAICHU;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Raichu.png"))));
      setAnimations(new int[]{34,30},
          new int[][]{new int[]{0,1},new int[]{0,2},new int[]{0,3},new int[]{0,2}},
          new int[][]{new int[]{0,0}});
      setMeleeAnimation(4,4);
      setIdleAnimation(1,2,3,2);
      setSpecialAnimation(5,6);
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
	return ThunderBoltState.getMagicRequirement();
  }
  
}
