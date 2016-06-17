package cl.makinolas.atk.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Fireball extends Attacks {
  
  private Animation fireballAnimation;
  private BodyDef myBodyDefinition; 
  private Monsters mySource;
  private float xVelocity;
  private float initialPosition;
  private float dt;
  private boolean dead;
  
  public Fireball(World myWorld, float x , float y, boolean facingRight, Monsters source){
    
    dt = 0;
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
    dt += delta;
    myBody.setLinearVelocity(xVelocity, 0);
  }
  
  private void setAnimation(){
    setMasterTexture(new TextureRegion(new Texture(Gdx.files.internal("Attacks/fireball.png"))),30,37);
    addAnimation(5,0.2f, new int[]{0, 0}, new int[]{0, 1}, new int[]{0, 2},new int[]{0, 3},new int[]{0, 4});
  }

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
}
