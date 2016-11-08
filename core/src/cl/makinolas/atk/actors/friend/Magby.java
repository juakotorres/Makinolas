package cl.makinolas.atk.actors.friend;

import cl.makinolas.atk.actors.attacks.ParabolicAttack;
import cl.makinolas.atk.actors.attacks.states.FireballState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.types.FireType;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;

public class Magby extends AbstractFriend {

	  private TextureRegion[][] faces;
	  
	  public Magby() {
	    friend = Enemies.MAGBY;
	    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Magby_faces.png"))).split(40,40);
	    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Magby.png"))));

	    setCutSprites(25,33);
	    setWalkAnimation(1,2,3,4);
	    setHurtAnimation(0);
	    setMeleeAnimation(5,6,7);
	    setIdleAnimation(2);
	    setSpecialAnimation(8,9);
	    
	    setFaceSprite(faces[0][0]);
	    initLevel(5);
	    initDead();
	    newMonster();
	    setActualEvolution(0);
	    setMaxMagic(1000);
	    addType(new FireType());
	  }
	  
	  public Magby(int level){
	    this();
	    initLevel(level);
	  }
	  
	  @Override
	  protected void initLevel(int level){
	   this.level = new Level(level);
	   setStats();
	   setHealth(getMaxHealth());
	   new Evolution(this.level, 30, 1);
	   new Evolution(this.level, 48, 2);
	  }
	  
	  @Override
	  protected void evolve(int numberOfLevel){
	    if (numberOfLevel == 1 && getActualEvolution() < 1){
	      friend = Enemies.MAGMAR;
	      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Magmar.png"))));
	      
		  setCutSprites(36,33);
		  setWalkAnimation(3,4,5,4);
		  setHurtAnimation(0);
		  setMeleeAnimation(6,7,8);
		  setIdleAnimation(1,2,1,2);
		  setSpecialAnimation(9,10,2);
	      
	      setFaceSprite(faces[0][1]);
	      setActualEvolution(1);
	      setStats();
	      setMaxMagic(1000);
	      
	    } else if (numberOfLevel == 2 && getActualEvolution() < 2){
	      friend = Enemies.MAGMORTAR;
	      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Magmortar.png"))));
		  setCutSprites(30,33);
		  setWalkAnimation(3,4,5,4);
		  setHurtAnimation(0);
		  setMeleeAnimation(8,9);
		  setIdleAnimation(1,2,1);
		  setSpecialAnimation(6,7);
	      
	      setFaceSprite(faces[0][2]);
	      setActualEvolution(2);
	      setStats();
	      setMaxMagic(1000);
	    }
	  }
	  
	  
	  @Override
	  public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source){
	    return new ParabolicAttack(new FireballState(),myWorld, x, y, facingRight, source);
	  }

}
