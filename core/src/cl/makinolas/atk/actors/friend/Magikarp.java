package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.ShootAttack;
import cl.makinolas.atk.actors.attacks.states.WindShurikenState;
import cl.makinolas.atk.types.FlyingType;
import cl.makinolas.atk.types.IType;
import cl.makinolas.atk.types.WaterType;

public class Magikarp extends AbstractFriend {

  private TextureRegion[][] faces;
  
  public Magikarp() {
    friend = Enemies.MAGIKARP;
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Magikarp_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Magikarp.png"))));
    setCutSprites(28,24);
    setWalkAnimation(3,4,5,4);
    setHurtAnimation(0);
    setMeleeAnimation(3,4,5,4);
    setIdleAnimation(1,2);
    setSpecialAnimation(3,4,5,4);
    setFaceSprite(faces[0][0]);
    initLevel(10);
    initDead();
    setActualEvolution(0);
    setMaxMagic(1000);
    addType(new WaterType());
  }
  
  public Magikarp(int level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level){
   this.level = new Level(level);
   setStats();
   setHealth(getMaxHealth());
   new Evolution(this.level, 20, 1);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      friend = Enemies.GYARADOS;
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Gyarados.png"))));
      setCutSprites(69,67);
      setWalkAnimation(2,3,4,3);
      setHurtAnimation(0);
      setMeleeAnimation(5,6,7);
      setIdleAnimation(1);
      setSpecialAnimation(8,11);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setStats();
      setMaxMagic(1000);
      addType(new FlyingType());
    }
  }
  
  
  @Override
  public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source){
    return new ShootAttack(new WindShurikenState(), myWorld, x, y, facingRight, source, false, new WaterType());
  }
  
  
  
}
