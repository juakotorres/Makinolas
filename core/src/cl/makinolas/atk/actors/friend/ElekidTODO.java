package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.BombAttack;
import cl.makinolas.atk.actors.attacks.states.ThunderBoltState;
import cl.makinolas.atk.actors.friend.AbstractFriend.Evolution;
import cl.makinolas.atk.actors.friend.AbstractFriend.Level;

public class ElekidTODO extends AbstractFriend {
	
	private TextureRegion[][] faces;
	
	public ElekidTODO(){
		friend = Enemies.ELEKID;
		/* aquí yace todo lo relacionado con los sprites */
	    setFaceSprite(faces[0][0]);
	    initLevel(10);
	    initDead();
	    newMonster();
	    setActualEvolution(0);
	    setMaxMagic(1000);
	}

	public ElekidTODO(int level){
		this();
		initLevel(level);
	}
	
	@Override
	protected void initLevel(int level) {
		this.level = new Level(level);
		setStats();
		setHealth(getMaxHealth());
		new Evolution(this.level, 30, 1);
		new Evolution(this.level, 48, 2);
	}
	
	@Override
	protected void evolve(int numberOfLevel) {
		if (numberOfLevel == 1 && getActualEvolution() < 1) {
			friend = Enemies.ELECTABUZZ;
			/* texturas */
		    setFaceSprite(faces[0][1]);
		    setActualEvolution(1);
		    setStats();
		    setMaxMagic(1000);
			
		} else if (numberOfLevel == 2 && getActualEvolution() < 2) {
			friend = Enemies.ELECTIVIRE;
			/* texturas */
		    setFaceSprite(faces[0][2]);
		    setActualEvolution(2);
		    setStats();
		    setMaxMagic(1000);
		}
	}
	
	
	@Override
	public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source) {
		return new BombAttack(new ThunderBoltState(), myWorld, x, y, facingRight, source);
	}
	
}
