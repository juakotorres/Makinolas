package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.ShootAttack;
import cl.makinolas.atk.actors.attacks.states.Poison_StingState;
import cl.makinolas.atk.actors.friend.AbstractFriend.Evolution;
import cl.makinolas.atk.actors.friend.AbstractFriend.Level;
import cl.makinolas.atk.types.FightType;
import cl.makinolas.atk.types.PoisonType;

public class Croagunk extends AbstractFriend {

	private TextureRegion[][] faces;
	
	public Croagunk(){
		friend = Enemies.CROAGUNK;
		faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Croagunk_faces.png"))).split(40,40);
		/* aquí yace todo lo relacionado con los sprites */
	    setFaceSprite(faces[0][0]);
	    initLevel(10);
	    initDead();
	    newMonster();
	    setActualEvolution(0);
	    setMaxMagic(1000);
	    addType(new PoisonType());
	    addType(new FightType());
	}
	
	public Croagunk(int level){
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
			friend = Enemies.TOXICROAK;
			/* texturas */
		    setFaceSprite(faces[0][1]);
		    setActualEvolution(1);
		    setStats();
		    setMaxMagic(1000);
		}
	}
	
	@Override
	public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source){
	    return new ShootAttack(new Poison_StingState(), myWorld, x, y, facingRight, source);
	}

}
