package cl.makinolas.atk.actors.bosses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.HBarFliped;
import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.attacks.AquaAttack;
import cl.makinolas.atk.actors.attacks.DroppingAttack;
import cl.makinolas.atk.actors.attacks.ShootAttack;
import cl.makinolas.atk.actors.attacks.states.BlueBeamState;
import cl.makinolas.atk.actors.friend.OldMewtwo;
import cl.makinolas.atk.stages.AbstractStage;

public abstract class OldMewtwoBoss extends Boss {
  
  private final float enemyAttack = 2f;
  private boolean isLaunchingAttack;
  private final float accumulator;
  private final float accumulatorAttack;
  private final int attackAnimation;
  private final int secondaryAttackAnimation;
  private float nextEnemyAttackAt;
  private World myWorld;
  private int currentAttack;
  private Hero hero;
  private int maxHealth;
  private float vx;
  private final float velocityMewtwo = 3;
  private int[] myAttacks;
  
  public OldMewtwoBoss(World myWorld, Hero hero) {
    
    health = 50;
    maxHealth = 50;
    width = 39;
    height = 33;
    parent = new OldMewtwo();
    nextEnemyAttackAt = enemyAttack;
    isAttacking = true;
    isLaunchingAttack = false;
    isFacingRight = false;
    vx = -velocityMewtwo;
    this.hero = hero;
    healthBar = new HBarFliped(health, health, 20, 133, new TextureRegion( new Texture(Gdx.files.internal("Overlays/bar_green.png"))));
    isDamaged = false;
    dead = false;
    accumulator = 0;
    accumulatorAttack = 0;
    this.myWorld = myWorld;
    
    // Definici�n del cuerpo del jugador.
    BodyDef myBodyDefinition = new BodyDef();
    myBodyDefinition.type = BodyDef.BodyType.DynamicBody;
    myBodyDefinition.position.set(new Vector2(16,3));
    
    // Forma del collider del jugador.
    Body myBody = myWorld.createBody(myBodyDefinition);
    
    PolygonShape shape = new PolygonShape();
    shape.setAsBox(0.6f,0.7f);
    ///
    myBody.setGravityScale(1);
    myBody.createFixture(shape, 0.5f);
    myBody.resetMassData();
    shape.dispose();
    
    // Guardar body.
    setBody(myBody);
    
    // Guardar animaciones del jugador
    setAnimation(new TextureRegion(new Texture(Gdx.files.internal("Actors/OldMewtwo.png"))), 39, 33);
    hurtAnimation = addAnimation(0.2f, 0);
    walkAnimation = addAnimation(0.2f, 3,4);
    attackAnimation = addAnimation(0.2f, 5,6,7,8,9);
    secondaryAttackAnimation = addAnimation(0.2f, 1);
    changeAnimation(walkAnimation);
    
    myAttacks = new int[]{1,2,3};
    currentAttack = 0;
  }
  
  @Override
  public void act(float delta){     
    super.act(delta);   
    
    moveBoss();
    
    nextEnemyAttackAt -= delta;
    
    if(nextEnemyAttackAt < 0){
      isLaunchingAttack = true;
      changeAnimation(secondaryAttackAnimation);
      seeHero(); 
      if(myAttacks[currentAttack] == 1){
        primaryAttack();
      } else if (myAttacks[currentAttack] == 2){
        secondaryAttack();
      } else if (myAttacks[currentAttack] == 3){
        if(this.health < this.maxHealth * 0.3){
          finalAttack();
        } else {
          primaryAttack();
        }
      }
      
      nextEnemyAttackAt = enemyAttack;
      currentAttack = (currentAttack + 1)%myAttacks.length;
   }
    
  }
  
  private void finalAttack() {
    GameActor fireball = new DroppingAttack(new BlueBeamState(), myWorld,4,10,isFacingRight, this);
    GameActor fireball2 = new DroppingAttack(new BlueBeamState(), myWorld, 9,10,isFacingRight, this);
    GameActor fireball3 = new DroppingAttack(new BlueBeamState(), myWorld, 14,10,isFacingRight, this);
    GameActor fireball4 = new DroppingAttack(new BlueBeamState(), myWorld, 19,10,isFacingRight, this);
    GameActor fireball5 = new DroppingAttack(new BlueBeamState(), myWorld, 24,10,isFacingRight, this);
    ((AbstractStage) getStage()).addGameActor(fireball);   
    ((AbstractStage) getStage()).addGameActor(fireball2);  
    ((AbstractStage) getStage()).addGameActor(fireball3);  
    ((AbstractStage) getStage()).addGameActor(fireball4);  
    ((AbstractStage) getStage()).addGameActor(fireball5);   
  }

  private void secondaryAttack() {
    GameActor fireball = new AquaAttack(myWorld, myBody.getPosition().x,myBody.getPosition().y,isFacingRight, this);
    ((AbstractStage) getStage()).addGameActor(fireball);
  }

  private void primaryAttack(){
    GameActor fireball = new ShootAttack(new BlueBeamState(), myWorld, myBody.getPosition().x,myBody.getPosition().y,isFacingRight, this);
    ((AbstractStage) getStage()).addGameActor(fireball);
  }
  
  private void moveBoss() {
    myBody.setLinearVelocity(vx, myBody.getLinearVelocity().y);
    
  }

  private void seeHero() {
    if (myBody.getPosition().x > hero.getBody().getPosition().x && isFacingRight){
      isFacingRight = false;
      vx = -velocityMewtwo;
    }
    else if (myBody.getPosition().x < hero.getBody().getPosition().x && !isFacingRight){
      isFacingRight = true;
      vx = velocityMewtwo;
    }
  }

  @Override
  public int getMeleeDamage() {
    return 10;
  }

@Override
public float getXDirection() {
	return vx;
	}
  
  
}
