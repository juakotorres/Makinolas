package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.BombAttack;
import cl.makinolas.atk.actors.attacks.states.ThunderBoltState;
import cl.makinolas.atk.types.ElectricType;


public class Elekid extends AbstractFriend {
	
	private TextureRegion[][] faces;
	
	public Elekid(){
		friend = Enemies.ELEKID;
		faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Elekid_faces.png"))).split(40,40);
		setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Elekid.png"))));
		/* aqui yace todo lo relacionado con los sprites */
		
	    setCutSprites(33,35);
	    setWalkAnimation(3,4,5,4);
	    setHurtAnimation(0);
	    setMeleeAnimation(6,7,8,9);
	    setIdleAnimation(1,2,1);
	    setSpecialAnimation(10);
	    
	    setFaceSprite(faces[0][0]);
	    initLevel(5);
	    initDead();
	    newMonster();
	    setActualEvolution(0);
	    setMaxMagic(1000);
	    addType(ElectricType.getInstance());
	}

	public Elekid(int level){
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
			setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Electabuzz.png"))));
			
		    setCutSprites(32,35);
		    setWalkAnimation(1,2,3,2);
		    setHurtAnimation(0);
		    setMeleeAnimation(4,5,6,7);
		    setIdleAnimation(1,2,3,2);
		    setSpecialAnimation(8);
			
		    setFaceSprite(faces[0][1]);
		    setActualEvolution(1);
		    setStats();
		    setMaxMagic(1000);
			
		} else if (numberOfLevel == 2 && getActualEvolution() < 2) {
			friend = Enemies.ELECTIVIRE;
			setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Electivire.png"))));
			
			setCutSprites(37,35);
		    setWalkAnimation(4,5,6,5);
		    setHurtAnimation(0);
		    setMeleeAnimation(7,8,9);
		    setIdleAnimation(1,2,3,2);
		    setSpecialAnimation(10,11);
			
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
	
	@Override
	public int getAttackMagicRequirement() {
	  // TODO Auto-generated method stub
	  return ThunderBoltState.magicRequirement;
	}
	
}
