package cl.makinolas.atk.actors.attacks;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;

public abstract class ThrowableAttacks extends Attacks {
  
  private BodyDef myBodyDefinition; 
  private Monsters mySource;
  private float initialPosition;
  private boolean dead;
  
  public ThrowableAttacks(World myWorld, float x , float y, boolean facingRight, Monsters source){
    
    dead = false;
    mySource = source;
    isFacingRight = !facingRight;
    this.xVelocity = (facingRight)? 10: -10;
    this.initialPosition= (facingRight)? .5f: -.5f;
    myBodyDefinition = new BodyDef();
    myBodyDefinition.type = BodyDef.BodyType.DynamicBody;
    myBodyDefinition.position.set(new Vector2(x + initialPosition,y));
    
    Body myBody = myWorld.createBody(myBodyDefinition);
    
    PolygonShape shape = new PolygonShape();
    shape.setAsBox(0.3f, 0.3f);
    
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
    
    // Guardar animaciones del jugador
    setAnimation();
  }
  
  @Override
  public void act(float delta){
    myBody.setLinearVelocity(xVelocity, yVelocity);
  }
  
  protected abstract void setAnimation();

  @Override
  public int getAttackDamage() {
    return 10;
  }

  @Override
  public Monsters getSource() {
    return mySource;
  }
  
  @Override
  public void setDead(){
    dead = true;
  }
  
  @Override
  public boolean isDead(){
    return dead;
  }
  
  public float getXVelocity(){
	  return xVelocity;
  }
  
  @Override
  public void setSource(Monsters monsters){
    this.mySource = monsters;
  }

  
}
