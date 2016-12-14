package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;



import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.ShootBombAttack;
import cl.makinolas.atk.actors.attacks.states.BubbleState;
import cl.makinolas.atk.types.TypeFactory;

public class Piplup extends AbstractFriend {

	private TextureRegion[][] faces;
	
	public Piplup(){
		friend = Enemies.PIPLUP;
		faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Piplup_faces.png"))).split(40,40);
		setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Piplup.png"))));
	    setFaceSprite(faces[0][0]);
		
	    setCutSprites(20,26);
	    setWalkAnimation(1,2,3,2);
	    setHurtAnimation(0);
	    setMeleeAnimation(4,5,6);
	    setIdleAnimation(1);
	    setSpecialAnimation(4,5,6);
	    initLevel(5);
	    initDead();
	    newMonster();
	    setActualEvolution(0);
	    setMaxMagic(1000);
	    addType(TypeFactory.getType("Water"));
	    
	}

	public Piplup(int level){
		this();
		initLevel(level);
	}
	
	@Override
	protected void initLevel(int level) {
		this.level = new Level(level);
		setStats();
		setHealth(getMaxHealth());
		new Evolution(this.level, 16, 1);
		new Evolution(this.level, 36, 2);
	}
	
	@Override
	protected void evolve(int numberOfLevel) {
		if (numberOfLevel == 1 && getActualEvolution() < 1) {
			friend = Enemies.PRINPLUP;
			setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Prinplup.png"))));
			setCutSprites(25,26);
		    setWalkAnimation(1,2,3,2);
		    setHurtAnimation(0);
		    setMeleeAnimation(4,5,6);
		    setIdleAnimation(1);
		    setSpecialAnimation(7,6);
			
		    setFaceSprite(faces[0][1]);
		    setActualEvolution(1);
		    setStats();
		    setMaxMagic(1000);
			
		} else if (numberOfLevel == 2 && getActualEvolution() < 2) {
			friend = Enemies.EMPOLEON;
			setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Empoleon.png"))));
			/* texturas */
			
		    setCutSprites(32,35);
		    setWalkAnimation(3,4,5,4);
		    setHurtAnimation(0);
		    setMeleeAnimation(6,7,8);
		    setIdleAnimation(1,2);
		    setSpecialAnimation(6,7,10);
			
		    setFaceSprite(faces[0][2]);
		    setActualEvolution(2);
		    setStats();
		    setMaxMagic(1000);
		    addType(TypeFactory.getType("Steel"));
		}
	}
	
	
	@Override
	public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source) {
		return new ShootBombAttack(new BubbleState(), myWorld, x, y, facingRight, source);
	}
	
	@Override
	public int getAttackMagicRequirement() {
		// TODO Auto-generated method stub
		return BubbleState.getMagicRequirement();
	}

}
