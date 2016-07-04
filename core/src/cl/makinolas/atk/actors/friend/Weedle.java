package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.Poison_Sting;

public class Weedle extends AbstractFriend {
  
  private TextureRegion[][] faces;
  
  public Weedle(Hero hero) {
    super(hero);
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Weedle_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Weedle.png"))));
    setAnimations(new int[]{30,24},
                  new int[][]{new int[]{0,1},new int[]{0,2}, new int[]{0,3}, new int[]{0,2}}, 
                  new int[][]{new int[]{0,0}});
    setMeleeAnimation(6,7);
    setFaceSprite(faces[0][0]);
    initLevel(3);
    initDead();
    setActualEvolution(0);
    setMaxHealth(30);
    setMaxMagic(1000);
    friend = Enemies.WEEDLE;
  }
  
  public Weedle(int level, Hero hero){
    this(hero);
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level){
   this.level = new Level(level);
   new Evolution(this.level, 6, 1);
   new Evolution(this.level, 10, 2);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Kakuna.png"))));
      setCutSprites(25,27);
      setWalkAnimation(3,4);
      setHurtAnimation(0);
      setMeleeAnimation(4,9);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setMaxHealth(60);
      setMaxMagic(100);
      friend = Enemies.KAKUNA;
    } else if (numberOfLevel == 2 && getActualEvolution() < 2){
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Beedrill.png"))));
      setAnimations(new int[]{35,28},
          new int[][]{new int[]{0,1},new int[]{0,2},new int[]{0,3},new int[]{0,2}},
          new int[][]{new int[]{0,0}});
      setMeleeAnimation(7,10);
      setFaceSprite(faces[0][2]);
      setActualEvolution(2);
      setMaxHealth(80);
      setMaxMagic(100);
      friend = Enemies.BEEDRILL;
    }
  }
  
  @Override
  public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source){
    return new Poison_Sting(myWorld, x, y, facingRight, source);
  }
}
