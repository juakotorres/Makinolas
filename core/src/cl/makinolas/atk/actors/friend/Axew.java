package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.ShootAttack;
import cl.makinolas.atk.actors.attacks.states.DragonBreathState;
import cl.makinolas.atk.types.DragonType;


public class Axew extends AbstractFriend {
	
	private TextureRegion[][] faces;
	
	public Axew(){
		friend = Enemies.AXEW;
		faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Axew_faces.png"))).split(40,40);
		setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Axew.png"))));
		/* aqui yace todo lo relacionado con los sprites */
		
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
	    addType(DragonType.getInstance());
	}
	
	public Axew(int level){
		this();
		initLevel(level);
	}
	
	@Override
	protected void initLevel(int level) {
		this.level = new Level(level);
		setStats();
		setHealth(getMaxHealth());
		new Evolution(this.level, 38, 1);
		new Evolution(this.level, 48, 2);
	}
	
	@Override
	protected void evolve(int numberOfLevel) {
		if (numberOfLevel == 1 && getActualEvolution() < 1) {
			friend = Enemies.FRAXURE;
			setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Fraxure.png"))));
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
			
		} else if (numberOfLevel == 2 && getActualEvolution() < 2) {
			friend = Enemies.HAXORUS;
			setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Haxorus.png"))));
			/* texturas */
			
		    /*setCutSprites(30,28);
		    setWalkAnimation(1,2,3,2);
		    setHurtAnimation(0);
		    setMeleeAnimation(4,6);
		    setIdleAnimation(1,2,3,2);
		    setSpecialAnimation(4,6);*/
			
		    setFaceSprite(faces[0][2]);
		    setActualEvolution(2);
		    setStats();
		    setMaxMagic(1000);
		}
	}
	
	@Override
	public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source){
	    return new ShootAttack(new DragonBreathState(), myWorld, x, y, facingRight, source);
	}
	
	@Override
	public int getAttackMagicRequirement() {
	  // TODO Auto-generated method stub
	  return DragonBreathState.magicRequirement;
	}

}
