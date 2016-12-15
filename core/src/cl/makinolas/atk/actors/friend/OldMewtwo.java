package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.ShootAttack;
import cl.makinolas.atk.actors.attacks.states.BlueBeamState;
import cl.makinolas.atk.types.TypeFactory;
//
public class OldMewtwo extends AbstractFriend {
  
 private TextureRegion[][] faces;
  
  public OldMewtwo() {
    friend = Enemies.MEWTWO;
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Mewtwo_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Mewtwo.png"))));
    setCutSprites(39,33);
    setWalkAnimation(3,4);
    setHurtAnimation(0);
    setMeleeAnimation(5,9);
    setIdleAnimation(1,2);
    setSpecialAnimation(5,9);
    setFaceSprite(faces[0][0]);
    initLevel(30);
    initDead();
    newMonster();
    setActualEvolution(0);
    setStats();
    setMaxMagic(1000);
    addType(TypeFactory.getType("Psychic"));
  }
  
  @Override
  protected void initLevel(int level){
   this.level = new Level(level);
  }
  
  @Override
  public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source){
    return new ShootAttack(new BlueBeamState(),myWorld, x, y, facingRight, source);
  }
  
  @Override
  public int getAttackMagicRequirement() {
	// TODO Auto-generated method stub
	return BlueBeamState.getMagicRequirement();
  }
  
}
