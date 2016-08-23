package cl.makinolas.atk.actors.attacks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;

public class Puff extends Attacks {
  
  private BodyDef myBodyDefinition; 
  private Monsters mySource;
  protected final float spriteTime = 1 / 50f;
  private int[] attackAnimations;
  private int actualAnimation;
  private float accumulator;
  private boolean dead;
  
public Puff(World myWorld, float x , float y, boolean facingRight, Monsters source){
    xVelocity =0;
    dead = false;
    mySource = source;
    accumulator = 0;
    isFacingRight = !facingRight;
    myBodyDefinition = new BodyDef();
    myBodyDefinition.type = BodyDef.BodyType.DynamicBody;
    myBodyDefinition.position.set(new Vector2(x, y));
    
    Body myBody = myWorld.createBody(myBodyDefinition);
    
    PolygonShape shape = new PolygonShape();
    shape.setAsBox(0.5f, 0.5f);
    
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
  

  private void setAnimation(){
    attackAnimations = new int[7];
    setMasterTexture(new TextureRegion(new Texture(Gdx.files.internal("Attacks/Puff.png"))),41,32);
    for(int i = 0; i < 7; i++){
      attackAnimations[i] = addAnimation(0.2f, i);
    }  
    actualAnimation = 0;
    changeAnimation(0);  
  }

  @Override
  public int getAttackDamage() {
    return 0;
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
  
  @Override
  public void setSource(Monsters monsters){
    this.mySource = monsters;
  }

  
}
