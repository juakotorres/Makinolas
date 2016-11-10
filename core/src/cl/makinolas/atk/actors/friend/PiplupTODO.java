package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;



import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.ShootBombAttack;
import cl.makinolas.atk.actors.attacks.states.BubbleState;
import cl.makinolas.atk.types.SteelType;
import cl.makinolas.atk.types.WaterType;

public class PiplupTODO extends AbstractFriend {

	private TextureRegion[][] faces;
	
	public PiplupTODO(){
		friend = Enemies.PIPLUP;
		faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Piplup_faces.png"))).split(40,40);
		setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Piplup.png"))));
		/* aquí yace todo lo relacionado con los sprites */
		
	    setFaceSprite(faces[0][0]);
	    initLevel(5);
	    initDead();
	    newMonster();
	    setActualEvolution(0);
	    setMaxMagic(1000);
	    addType(new WaterType());
	    
	}

	public PiplupTODO(int level){
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
			friend = Enemies.EMPOLEON;
			setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Empoleon.png"))));
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
		    addType(new SteelType());
		}
	}
	
	
	@Override
	public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source) {
		return new ShootBombAttack(new BubbleState(), myWorld, x, y, facingRight, source);
	}

}
