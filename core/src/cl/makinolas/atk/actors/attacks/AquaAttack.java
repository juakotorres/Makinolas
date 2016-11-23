package cl.makinolas.atk.actors.attacks;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.states.AquaAttackState;
import cl.makinolas.atk.actors.platform.Platform;
import cl.makinolas.atk.types.IType;

public class AquaAttack extends Attacks {
  
  private int[] attackAnimations;
  private int actualAnimation;
  protected final float spriteTime = 1 / 5f;
  protected float accumulator;
  private float initialPosition;
  private int[] spriteWidth;
  private int[] spriteHeight;
  
  public AquaAttack(World myWorld, float x , float y, boolean facingRight, Monsters source){
    super(myWorld, x, y, facingRight, source, false);
    
    spriteWidth = new int[]{24, 24, 25, 26, 26, 29, 29};
    spriteHeight = new int[]{18, 18, 26, 37, 37, 52, 52};
    accumulator = 0;
    this.xVelocity = (facingRight)? 8: -8;
    this.initialPosition= (facingRight)? 1f: -1f;
    
    mySpriteState = new AquaAttackState();
    
    mySpriteState.setAttack(this); 
    
    myBodyDefinition = new BodyDef();
    myBodyDefinition.type = BodyDef.BodyType.DynamicBody;
    setSizeCollider(new Vector2(x + initialPosition * source.getMonsterWidth() * 2, y), true);
    
    setSprite();
  }
  
  private void setSizeCollider(Vector2 position, boolean first) {
    myBodyDefinition.position.set(position);
    if(!first){
      myWorld.destroyBody(getBody());
    }
    Body myBody = myWorld.createBody(myBodyDefinition);
    PolygonShape shape = new PolygonShape();
    shape.setAsBox(getBodySize(getSpriteWidth()), getBodySize(getSpriteHeight()));
    myBody.setGravityScale(0);
    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.isSensor = true;
    fixtureDef.density = 0;
    fixtureDef.shape = shape;
    myBody.createFixture(fixtureDef);
    myBody.resetMassData();
    shape.dispose();
    
    // Change Body.
    setBody(myBody);
  }
  
  private int getSpriteHeight() {
    return spriteHeight[actualAnimation];
  }

  private int getSpriteWidth() {
    return spriteWidth[actualAnimation];
  }

  protected void setSprite(){
    attackAnimations = new int[7];
    setMasterTexture(mySpriteState.getTexture(),mySpriteState.getWidth(),mySpriteState.getHeight());
    for(int i = 0; i < 7; i++){
      attackAnimations[i] = addAnimation(0.2f, i);
    }  
    actualAnimation = 0;
    changeAnimation(0);
  }
  
  @Override
  public void act(float delta){
    myBody.setLinearVelocity(xVelocity, 0);
    accumulator += delta;
   
    if(accumulator > spriteTime){
      actualAnimation += 1;
      if(actualAnimation  < 7) {
        setSizeCollider(getBody().getPosition(), false);
        changeAnimation(actualAnimation);
        accumulator = 0;
      } else {
        setDead();
      }
    }
  }
  
  @Override
  public int getAttackDamage() {
    return mySpriteState.getAttackDamage();
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
  public void interactWithPlatform(Platform platform, WorldManifold worldManifold){
  } 
  
  @Override
  public void setSource(Monsters monsters){
    this.mySource = monsters;
  }

  @Override
  protected void setAnimation() {
    // TODO Auto-generated method stub
    
  }

  
}
