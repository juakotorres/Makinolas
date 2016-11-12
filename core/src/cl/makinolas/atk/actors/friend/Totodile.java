package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.ShootBombAttack;
import cl.makinolas.atk.actors.attacks.states.BubbleState;
import cl.makinolas.atk.types.GrassType;
import cl.makinolas.atk.types.IType;
import cl.makinolas.atk.types.WaterType;

public class Totodile extends AbstractFriend {
  
  private TextureRegion[][] faces;
  
  public Totodile() {
    friend = Enemies.TOTODILE;
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Totodile_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Totodile.png"))));
    setAnimations(new int[]{29,26},
                  new int[][]{new int[]{0,4},new int[]{0,5},new int[]{0,6},new int[]{0,5}},
                  new int[][]{new int[]{0,0}});
    setMeleeAnimation(7,11);
    setIdleAnimation(1,2,3);
    setSpecialAnimation(12,14);
    setFaceSprite(faces[0][0]);
    initLevel(5);
    initDead();
    newMonster();
    setActualEvolution(0);
    setMaxMagic(1000);
    addType(new WaterType());
  }
  
  public Totodile(int level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level){
   this.level = new Level(level);
   setStats();
   setHealth(getMaxHealth());
   new Evolution(this.level, 18, 1);
   new Evolution(this.level, 30, 2);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      friend = Enemies.CROCONAW;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Croconaw.png"))));
      setAnimations(new int[]{27,28},
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
      friend = Enemies.FERALIGATR;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Feraligatr.png"))));
      setAnimations(new int[]{38,37},
          new int[][]{new int[]{0,3},new int[]{0,4},new int[]{0,5},new int[]{0,4}},
          new int[][]{new int[]{0,0}});
      setMeleeAnimation(6,8);
      setIdleAnimation(1,2);
      setSpecialAnimation(9,11);
      setFaceSprite(faces[0][2]);
      setActualEvolution(2);
      setStats();
      setMaxMagic(1000);
    }
  }
  
  @Override
  public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source){
    return new ShootBombAttack(new BubbleState(),myWorld, x, y, facingRight, source);
  }
  
  @Override
  public int getAttackMagicRequirement() {
	// TODO Auto-generated method stub
	return BubbleState.magicRequirement;
  }
  
}
