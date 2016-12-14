package cl.makinolas.atk.actors.enemies;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;

import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.friend.Enemies;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.actors.platform.Platform;
import cl.makinolas.atk.stages.AbstractStage;

public class JumperEnemy extends Enemy {
  
  private final float attackTime = 3f;
  private float accumulatorAttack;
  private AttackDetector attackDetector;
  private boolean isJumping;

  public JumperEnemy(World myWorld, TextureRegion enemyTexture, int[] cutSprite, int[][] numberOfSprite,
      int[][] numberOfHurtSprites, int givenHealth, int positionX, int positionY, boolean facingRight, int level, Enemies type,
      Friend parent) {
    super(myWorld, enemyTexture, cutSprite, numberOfSprite, numberOfHurtSprites, givenHealth, positionX, positionY, facingRight, level,
        type, parent);
    
    accumulatorAttack = 0;
    isJumping = false;
    attackDetector = new AttackDetector(myWorld, this, parent);
  }
  
  
  @Override
  public void act(float delta){ 
	  super.act(delta);
	  if(!this.isSinging){
		  myBody.setLinearVelocity(vx, myBody.getLinearVelocity().y);
	  }
    if(!viewGround){
      flip();
      viewGround = true;
    }

    checkDamage(delta, 0);
    accumulatorAttack += delta; 
    
    if(!this.isSinging && accumulatorAttack > attackTime && super.isFree()){
        GameActor attack = parent.getFriendAttack(myWorld, myBody.getPosition().x - 0.5f, myBody.getPosition().y, isFacingRight, this);
        ((AbstractStage) getStage()).addGameActor(attack);
       ((Attacks) attack).getSpriteState().secondaryEfectsToSource(this);
        accumulatorAttack = 0;
    }

    checkGround(delta);
  }

  @Override
  protected void checkGround(float delta) {
    if(!isJumping) {
        groundAcc += delta;
        if(groundAcc > groundTime) {
            viewGround = false;
            myWorld.rayCast(rayListener, myBody.getPosition().x, myBody.getPosition().y, myBody.getPosition().x + vx*2, myBody.getPosition().y - 3);
            groundAcc = 0;
        }
    }
  }

  public void initDetector(AbstractStage stage) {
    stage.addGameActor(attackDetector);    
  }
  
  @Override
  public void jump() {
    if(!isJumping){
      isJumping = true;
      myBody.applyLinearImpulse(0, 10, myBody.getPosition().x, myBody.getPosition().y, true);
    }
  }
  
  @Override
  public void interactWithPlatform(Platform platform, WorldManifold worldManifold){
    super.interactWithPlatform(platform, worldManifold);
    landedPlatform(worldManifold, platform);
  }

  @Override
  public void landedPlatform(WorldManifold worldManifold, Platform platform) {
    for(int i = 0; i < worldManifold.getNumberOfContactPoints(); i++){
      if(worldManifold.getPoints()[i].y < myBody.getPosition().y && (worldManifold.getNormal().y > 0.95 || worldManifold.getNormal().y < -0.95)){
        isJumping = false;
      }
    } 
  }
  
  @Override
  public void setDead(){
    super.setDead();
    attackDetector.setDead();
  }
}
