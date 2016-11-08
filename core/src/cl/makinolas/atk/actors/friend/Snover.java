package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.ShootAttack;
import cl.makinolas.atk.actors.attacks.states.IceRockState;
import cl.makinolas.atk.actors.friend.AbstractFriend.Evolution;
import cl.makinolas.atk.actors.friend.AbstractFriend.Level;
import cl.makinolas.atk.types.GrassType;
import cl.makinolas.atk.types.IceType;

public class Snover extends AbstractFriend {

	private TextureRegion[][] faces;
	
	public Snover(){
		friend = Enemies.SNOVER;
		faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Snover_faces.png"))).split(40,40);
		setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Snover.png"))));
		/* aquí yace todo lo relacionado con los sprites */
		
	    /*setCutSprites(30,28);
	    setWalkAnimation(1,2,3,2);
	    setHurtAnimation(0);
	    setMeleeAnimation(4,6);
	    setIdleAnimation(1,2,3,2);
	    setSpecialAnimation(4,6);*/
		
	    setFaceSprite(faces[0][0]);
	    initLevel(5);
	    initDead();
	    newMonster();
	    setActualEvolution(0);
	    setMaxMagic(1000);
	    addType(new IceType());
	    addType(new GrassType());
	}
	
	public Snover(int level){
		this();
		initLevel(level);
	}
	
	@Override
	protected void initLevel(int level) {
		this.level = new Level(level);
		setStats();
		setHealth(getMaxHealth());
		new Evolution(this.level, 30, 1);
		new Evolution(this.level, 55, 2);
	}
	
	@Override
	protected void evolve(int numberOfLevel) {
		if (numberOfLevel == 1 && getActualEvolution() < 1) {
			friend = Enemies.ABOMASNOW;
			setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Abomasnow.png"))));
			/* texturas */
			
		    /*setCutSprites(30,28);
		    setWalkAnimation(1,2,3,2);
		    setHurtAnimation(0);
		    setMeleeAnimation(4,6);
		    setIdleAnimation(1,2,3,2);
		    setSpecialAnimation(4,6);*/
			
		    setFaceSprite(faces[0][1]);
		    setActualEvolution(1);
		    setStats();
		    setMaxMagic(1000);
		}
	}
	
	@Override
	public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source){
	    return new ShootAttack(new IceRockState(), myWorld, x, y, facingRight, source);
	}

}
