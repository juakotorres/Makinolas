package cl.makinolas.atk.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;

public class Puff extends AnimatedActor {
  
  protected final float spriteTime = 1 / 50f;
  private int[] attackAnimations;
  private int actualAnimation;
  private float accumulator;
  private boolean dead;
  private Monsters mySource;
  private float initialPosition;
  private BodyDef myBodyDefinition;
  
  public Puff(World myWorld, float x , float y, boolean facingRight, Monsters source){
    dead = false;
    mySource = source;
    isFacingRight = !facingRight;
    this.initialPosition= (facingRight)? .5f: -.5f;
    
    accumulator = 0;
    myBodyDefinition = new BodyDef();
    myBodyDefinition.type = BodyDef.BodyType.DynamicBody;
    myBodyDefinition.position.set(new Vector2(x + initialPosition,y));
    
    Body myBody = myWorld.createBody(myBodyDefinition);
    
    PolygonShape shape = new PolygonShape();
    shape.setAsBox(getBodySize(41), getBodySize(32));
    
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
  
  protected float getBodySize(int size){
    return (0.5f*size)/22;
  }
  
  @Override
  public void act(float delta){
    
    myBody.setTransform(new Vector2(mySource.getBody().getPosition().x ,mySource.getBody().getPosition().y)
        , myBody.getAngle());
    accumulator += delta;
    
    if(accumulator > spriteTime){
      actualAnimation += 1;
      if(actualAnimation  < 7) {
        changeAnimation(actualAnimation);
        accumulator = 0;
      } else {
        setDead();
      }
    }
  }
  

  protected void setAnimation(){
    attackAnimations = new int[7];
    setMasterTexture(new TextureRegion(new Texture(Gdx.files.internal("Attacks/Puff.png"))),41,32);
    for(int i = 0; i < 7; i++){
      attackAnimations[i] = addAnimation(0.2f, i);
    }  
    actualAnimation = 0;
    changeAnimation(0);  
  }

  public Monsters getSource() {
    return mySource;
  }
  
  public void setDead(){
    dead = true;
  }
  
  @Override
  public boolean isDead(){
    return dead;
  }

  @Override
  public void interact(GameActor actor2, WorldManifold worldManifold) {
    
  }
  
  @Override
  public boolean isPuff() {
    return true;
  }
  
}
