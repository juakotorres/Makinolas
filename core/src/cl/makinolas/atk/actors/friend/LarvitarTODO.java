package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.ShootAttack;
import cl.makinolas.atk.actors.attacks.states.DragonBreathState;
import cl.makinolas.atk.actors.attacks.states.RockState;
import cl.makinolas.atk.actors.attacks.states.ThunderBoltState;
import cl.makinolas.atk.actors.friend.AbstractFriend.Evolution;
import cl.makinolas.atk.actors.friend.AbstractFriend.Level;
import cl.makinolas.atk.types.DarkType;
import cl.makinolas.atk.types.GroundType;
import cl.makinolas.atk.types.RockType;

public class LarvitarTODO extends AbstractFriend {

	private TextureRegion[][] faces;
	
	public LarvitarTODO(){
		friend = Enemies.LARVITAR;
		/* aqu� yace todo lo relacionado con los sprites */
	    setFaceSprite(faces[0][0]);
	    initLevel(10);
	    initDead();
	    newMonster();
	    setActualEvolution(0);
	    setMaxMagic(1000);
	    addType(new RockType());
	    addType(new GroundType());
	}
	
	public LarvitarTODO(int level){
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
			friend = Enemies.PUPITAR;
			/* texturas */
		    setFaceSprite(faces[0][1]);
		    setActualEvolution(1);
		    setStats();
		    setMaxMagic(1000);
			
		} else if (numberOfLevel == 2 && getActualEvolution() < 2) {
			friend = Enemies.TYRANITAR;
			/* texturas */
		    setFaceSprite(faces[0][2]);
		    setActualEvolution(2);
		    setStats();
		    setMaxMagic(1000);
		    resetType();
		    addType(new RockType());
		    addType(new DarkType());
		}
	}
	
	@Override
	public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source){
	    return new ShootAttack(new RockState(), myWorld, x, y, facingRight, source);
	}
	
	@Override
	public int getAttackMagicRequirement() {
	  // TODO Auto-generated method stub
	  return RockState.magicRequirement;
	}
}
