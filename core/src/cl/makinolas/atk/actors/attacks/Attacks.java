package cl.makinolas.atk.actors.attacks;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;

import cl.makinolas.atk.actors.AnimatedActor;
import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.states.SpriteState;
import cl.makinolas.atk.actors.bosses.IBoss;
import cl.makinolas.atk.actors.enemies.AttackDetector;
import cl.makinolas.atk.actors.enemies.Enemy;
import cl.makinolas.atk.actors.platform.Platform;

public abstract class Attacks extends AnimatedActor {
	protected float xVelocity;
	protected float yVelocity = 0;
  protected boolean dead;
  protected World myWorld;
  protected Monsters mySource;
  protected float initialPosition;
  protected BodyDef myBodyDefinition; 
  public abstract int getAttackDamage();
  public abstract Monsters getSource();
  public abstract void setDead();
  protected SpriteState mySpriteState;
  protected boolean rotated;
  
  public Attacks(World myWorld, float x , float y, boolean facingRight, Monsters source, boolean rotated){
    this.myWorld = myWorld;
    this.rotated = rotated;
    dead = false;
    mySource = source;
    isFacingRight = !facingRight;
    this.xVelocity = (facingRight)? 10: -10;
    this.initialPosition= (facingRight)? .5f: -.5f;
    
    // Guardar animaciones del jugador
    setAnimation();
  }
  
  public void initializeBody(float x, float y){
    myBodyDefinition = new BodyDef();
    myBodyDefinition.type = BodyDef.BodyType.DynamicBody;
    myBodyDefinition.position.set(new Vector2(x + initialPosition,y));
    
    Body myBody = myWorld.createBody(myBodyDefinition);
    
    PolygonShape shape = new PolygonShape();
    if(!rotated){
      shape.setAsBox(getBodySize(mySpriteState.getBodyWidth()), getBodySize(mySpriteState.getBodyHeight()));
    } else {
      shape.setAsBox(getBodySize(mySpriteState.getBodyHeight()), getBodySize(mySpriteState.getBodyWidth()));
    }
    
    myBody.setGravityScale(0);
    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.isSensor = true;
    fixtureDef.density = 0;
    fixtureDef.shape = shape;
    myBody.createFixture(fixtureDef);
    myBody.resetMassData();
    shape.dispose();
    
    // Guardar body.
    setBody(myBody);
  }
  
  protected abstract void setAnimation();
  
  public  float getXVelocity() {
	  return xVelocity;
  }
  
  @Override
  public boolean isAttack(){
    return true;
  }
  
  @Override
  public void interact(GameActor actor, WorldManifold worldManifold){
    actor.interactWithAttack(this, worldManifold);
  }
  
  @Override
  public void interactWithHero(Hero hero, WorldManifold worldManifold){
    hero.damage(hero.getAttackDamage(this), this);
  }
  
  @Override
  public void interactWithEnemy(Enemy enemy, WorldManifold worldManifold){
    enemy.damage(enemy.getAttackDamage(this), this);
  }
  
  @Override
  public void interactWithBoss(IBoss boss){
    boss.getBoss().damage(boss.getBoss().getAttackDamage(this), this);
  }
  
  @Override
  public void interactWithAttackDetector(AttackDetector attackDetector){
    attackDetector.detected(this);
  }
  
  @Override
  public void interactWithPlatform(Platform platform, WorldManifold worldManifold){
    this.setDead();
  }
  
  protected float getBodySize(int size){
    return (0.5f*size)/22;
  }
  
  public abstract void setSource(Monsters monster);
  
  public void isDropping(){
    yVelocity = -3;
    xVelocity = 0;
  }
}
