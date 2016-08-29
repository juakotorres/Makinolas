package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Acid;
import cl.makinolas.atk.actors.attacks.Attacks;

public class Gastly extends AbstractFriend {
  
 private TextureRegion[][] faces;
  
  public Gastly(Hero hero) {
    super(hero);
    friend = Enemies.GASTLY;
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Gastly_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Gastly.png"))));
    setAnimations(new int[]{30,30},
                  new int[][]{new int[]{0,1},new int[]{0,2},new int[]{0,3}},
                  new int[][]{new int[]{0,0}});
    setMeleeAnimation(4,5);
    setFaceSprite(faces[0][0]);
    initLevel(5);
    initDead();
    setActualEvolution(0);
    setMaxMagic(1000);
  }
  
  public Gastly(int level, Hero hero){
    this(hero);
    initLevel(level);
  }
  
  public Gastly(int level){
    this(Hero.getInstance());
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level){
   this.level = new Level(level);
   setStats();
   setHealth(getMaxHealth());
   new Evolution(this.level, 25, 1);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      friend = Enemies.HAUNTER;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Haunter.png"))));
      setAnimations(new int[]{46,36},
          new int[][]{new int[]{0,2},new int[]{0,3},new int[]{0,4},new int[]{0,3}},
          new int[][]{new int[]{0,0}});
      setMeleeAnimation(6,10);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setStats();
      setMaxMagic(1000);
    }
    if (numberOfLevel == 2 && getActualEvolution() < 2){
      friend = Enemies.GENGAR;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Gengar.png"))));
      setCutSprites(41,28);
      setWalkAnimation(4,5,6,5);
      setHurtAnimation(0);
      setMeleeAnimation(7,8,9);
      setFaceSprite(faces[0][2]);
      setActualEvolution(2);
      setStats();
      setMaxMagic(1000);
    }
  }
  
  @Override
  public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source){
    return new Acid(myWorld, x, y, facingRight, source);
  }
  
}
