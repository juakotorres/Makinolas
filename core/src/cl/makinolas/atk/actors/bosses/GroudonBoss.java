package cl.makinolas.atk.actors.bosses;

import cl.makinolas.atk.GameConstants;
import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.HBarFliped;
import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.BombAttack;
import cl.makinolas.atk.actors.attacks.DirectionAttack;
import cl.makinolas.atk.actors.attacks.states.FireWallState;
import cl.makinolas.atk.actors.attacks.states.TRockState;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.actors.friend.Groudon;
import cl.makinolas.atk.stages.AbstractStage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class GroudonBoss extends Boss {

    private float maxHealth;
    private World myWorld;
    private Hero hero;
    private float nextRockAt;
    private int numRocks;
    private float jumpTime;
    private int jumpDirection;
	private int singAnimation;

    public GroudonBoss(World myWorld, Hero hero) {
        super();
        health = Math.max(Hero.getInstance().getHealth()*5+50, 200);
        maxHealth = Math.max(Hero.getInstance().getHealth()*5, 150);
        jumpDirection = 1;
        width = 39;
        height = 33;
        isAttacking = true;
        isFacingRight = false;
        vx = 0;
        parent = new Groudon(maxLevelOfAllies(Hero.getInstance().getAllies()));
        this.hero = hero;
        healthBar = new HBarFliped(health, health, 20, 133, new TextureRegion( new Texture(Gdx.files.internal("Overlays/bar_green.png"))));
        isDamaged = false;
        dead = false;
        this.myWorld = myWorld;

        // Definición del cuerpo del jugador.
        BodyDef myBodyDefinition = new BodyDef();
        myBodyDefinition.type = BodyDef.BodyType.DynamicBody;
        myBodyDefinition.position.set(new Vector2(24,4));

        // Forma del collider del jugador.
        Body myBody = myWorld.createBody(myBodyDefinition);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/GameConstants.WORLD_FACTOR,height/GameConstants.WORLD_FACTOR);
        ///
        myBody.setGravityScale(1);
        myBody.createFixture(shape, 0.5f);
        myBody.resetMassData();
        shape.dispose();

        // Guardar body.
        setBody(myBody);

        // Guardar animaciones del jugador
        setAnimation(new TextureRegion(new Texture(Gdx.files.internal("Actors/Groudon.png"))), 64, 55);
        hurtAnimation = addAnimation(0.2f, 2);
        walkAnimation = addAnimation(0.2f, 3,4,5,6);
        singAnimation = hurtAnimation;
        changeAnimation(walkAnimation);
    }

    @Override
    public void defineStates() {
        RandomProcessor processor = new RandomProcessor(1.5f);
        setProcessor(processor);
        BossState fireball = new BossState(processor){
            @Override
            public void act(float delta) {
                generateFirewalls();
                health = (int) Math.min(maxHealth,health+5);
                healthBar.setCurrent(health);
                goBack();
            }
        };
        BossState rocks = new BossState(processor){
            @Override
            public void onChoose() {
                numRocks = (int) (8 - 7*health/maxHealth);
                nextRockAt = 0;
            }
            @Override
            public void act(float delta) {
                nextRockAt -= delta;
                if(nextRockAt <= 0){
                    nextRockAt = 0.5f;
                    throwRock();
                    throwRock();
                    numRocks--;
                    if(numRocks <= 0)
                        goBack();
                }
            }
        };
        BossState jump = new BossState(processor){
            @Override
            public void onChoose() {
                jumpTime = (jumpDirection + 1)/2;
                jumpDirection *= -1;
            }
            @Override
            public void act(float delta) {
                jumpTime += jumpDirection * delta;
                if(jumpTime >= 1 || jumpTime <= 0) {
                    jumpTime = (jumpDirection + 1)/2;
                    myBody.setAwake(true);
                    isFacingRight = !isFacingRight;
                    throwRock();
                    goBack();
                    throwRock();
                }
                myBody.setTransform(new Vector2(4+jumpTime*20,2 + 16*jumpTime*(1-jumpTime)),0);
            }
        };
        processor.addStates(fireball,rocks,jump);
        processor.setProbabilities(0.4f,0.4f,0.2f);
    }

    private void generateFirewalls() {
        Vector2 pos = myBody.getPosition();
        GameActor wall = new BombAttack(new FireWallState(),myWorld,pos.x+1-2*jumpDirection,pos.y,false,this);
        ((AbstractStage) getStage()).addGameActor(wall);
        ((Attacks) wall).getSpriteState().secondaryEfectsToSource(this);
    }

    private void throwRock() {
        Vector2 pos = myBody.getPosition();
        GameActor rock = new DirectionAttack(new TRockState(),myWorld,pos.x-1,pos.y+3,
                hero.getBody().getPosition().x,hero.getBody().getPosition().y,300,this);
        ((AbstractStage) getStage()).addGameActor(rock);
        ((Attacks) rock).getSpriteState().secondaryEfectsToSource(this);
    }

    @Override
    public int getMeleeDamage() {
        return 10;
    }

    @Override
    public float getXDirection() {
        return vx;
    }

	@Override
	public void sing() {
		this.changeAnimation(singAnimation);
	}

	@Override
	public void unSing() {
		
	}

	@Override
	public void sleep() {
		
	}

	@Override
	public void Awake() {
		
	}
	
	  private int maxLevelOfAllies(Array<Friend> allies) {
		    int maxLevel = allies.get(0).getLevel();
		    for(Friend ally : allies){
		      if(maxLevel < ally.getLevel())
		        maxLevel = ally.getLevel();
		    }
		    return maxLevel;
		  }

	@Override
	public void paraliza3() {
		
	}

	@Override
	public void desparaliza3() {
		
	}
}
