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
import cl.makinolas.atk.actors.HBar;
import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.attacks.AquaAttack;
import cl.makinolas.atk.actors.attacks.BlueBeam;
import cl.makinolas.atk.stages.AbstractStage;

public class OldMewtwo extends Boss {
  
  private final float enemyAttack = 2f;
  private float nextEnemyAttackAt;
  private World myWorld;
  private int currentAttack;
  private Hero hero;
  private float vx;
  private final float velocityMewtwo = 3;
  private int[] myAttacks;
  
  public OldMewtwo(World myWorld, Hero hero) {
    
    health = 200;
    width = 39;
    height = 33;
    nextEnemyAttackAt = enemyAttack;
    isAttacking = true;
    isLaunchingAttack = false;
    isFacingRight = false;
    vx = -velocityMewtwo;
    this.hero = hero;
    healthBar = new HBar(health, health, width, 4, new TextureRegion( new Texture(Gdx.files.internal("Overlays/bar_green.png"))));
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
    
    myAttacks = new int[]{1,1,2};
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
      }
      
      nextEnemyAttackAt = enemyAttack;
      currentAttack = (currentAttack + 1)%myAttacks.length;
   }
    
  }
  
  private void secondaryAttack() {
    GameActor fireball = new AquaAttack(myWorld, myBody.getPosition().x,myBody.getPosition().y,isFacingRight, this);
    ((AbstractStage) getStage()).addGameActor(fireball);
  }

  private void primaryAttack(){
    GameActor fireball = new BlueBeam(myWorld, myBody.getPosition().x,myBody.getPosition().y,isFacingRight, this);
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
