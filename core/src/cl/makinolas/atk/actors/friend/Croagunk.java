package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.ShootAttack;
import cl.makinolas.atk.actors.attacks.states.Poison_StingState;
import cl.makinolas.atk.types.TypeFactory;

public class Croagunk extends AbstractFriend {

	private TextureRegion[][] faces;
	
	public Croagunk(){
		friend = Enemies.CROAGUNK;
		faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Croagunk_faces.png"))).split(40,40);
		setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Croagunk.png"))));
		
	    setCutSprites(28,26);
	    setWalkAnimation(3,4,5,4);
	    setHurtAnimation(0);
	    setMeleeAnimation(6,7);
	    setIdleAnimation(1,2);
	    setSpecialAnimation(8,9,10);
		
	    setFaceSprite(faces[0][0]);
	    initLevel(5);
	    initDead();
	    newMonster();
	    setActualEvolution(0);
	    setMaxMagic(1000);
	    addType(TypeFactory.getType("Poison"));
	    addType(TypeFactory.getType("Fight"));
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
			setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Toxicroak.png"))));
			
		    setCutSprites(30,28);
		    setWalkAnimation(4,5,6,5);
		    setHurtAnimation(0);
		    setMeleeAnimation(7,8);
		    setIdleAnimation(1,2,3,2,1);
		    setSpecialAnimation(7,9);
			
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
