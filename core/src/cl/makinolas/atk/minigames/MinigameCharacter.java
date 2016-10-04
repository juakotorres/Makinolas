package cl.makinolas.atk.minigames;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;

import cl.makinolas.atk.actors.AnimatedActor;
import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.JumpState;
import cl.makinolas.atk.actors.OnGround;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.actors.friend.Pichu;
import cl.makinolas.atk.actors.platform.Platform;
import cl.makinolas.atk.stages.AbstractStage;
import cl.makinolas.atk.stages.OnWall;

public class MinigameCharacter extends AnimatedActor{
  
  
  private BodyDef myBodyDefinition;
  private JumpState state;
  private boolean onWall = false;
  private World myWorld;
  private boolean isJumping;
  private boolean inertia;
  private int vx;
  private boolean dead;
  private float accumulator;
  private int walkAnimation;
  private int jumpAnimation;
  private Friend actualFriend;

  
  public MinigameCharacter(World myWorld) {

    isJumping = false;
    isFacingRight = true;
    dead = false;
    accumulator = 0;
    vx = 10;
    inertia = false;
    this.myWorld = myWorld;
    actualFriend = new Pichu();
    
    // Set correct collider.
    myBodyDefinition = new BodyDef();
    myBodyDefinition.type = BodyDef.BodyType.DynamicBody;
    
    // Guardar animaciones del jugador
    setAnimation();
    changeAnimation(walkAnimation);
    state = new OnGround();
    state.setHero(this);
    myBodyDefinition.fixedRotation = true; 
    setSizeCollider(new Vector2(10,10), true);
  }
  
  private void setAnimation(){
    setMasterTexture(actualFriend.getTexture(),actualFriend.getWidth(),actualFriend.getHeight());
    walkAnimation = addAnimation(0.2f, actualFriend.getWalkAnimation());
    jumpAnimation = addAnimation(0.2f, actualFriend.getHurtAnimation());
  }

  @Override
  public void act(float delta){
    myBody.setLinearVelocity(vx, myBody.getLinearVelocity().y);
    
    checkPosition(delta);
    ((AbstractStage) getStage()).changeCamera(myBody.getPosition().x , myBody.getPosition().y );
       
    if (isJumping == true)
      state.countFrames();    
  }
  
  private void checkPosition(float delta) {
    accumulator += delta;
        
    if(accumulator > 1/2f){
      accumulator = 0;
      if(myBody.getPosition().y < -3){
        setDead();
      }
    }
    
  }
  
  @Override
  public boolean isMinigameCharacter() {
    return true;
  }

  private void setSizeCollider(Vector2 position, boolean first) {
    myBodyDefinition.position.set(position);
    if(!first){
      myWorld.destroyBody(getBody());
    }
    Body myBody = myWorld.createBody(myBodyDefinition);
    PolygonShape shape = new PolygonShape();
    shape.setAsBox(getBodySize(actualFriend.getWidth()), getBodySize(actualFriend.getHeight()));
    myBody.setGravityScale(1);
    myBody.createFixture(shape, 0.5f);
    myBody.resetMassData();
    shape.dispose();

    // Change Body.
    setBody(myBody);
  }
  
  // This is used to get body width and height.
  private float getBodySize(int size){
    return (0.5f*size)/22;
  }
  
  /*public void moveHorizontal(int i, boolean restitutive) {
    if(restitutive && !inertia) return;
    vx += 7*i;
    if(vx!=0)
      isFacingRight = (vx>0);
    inertia = true;
  }*/
  
  public void jump(int button) {
  isJumping = true;
  state.restarCount();
    state.jump();
  }
  
  public void isNotPressingSpace() {
    isJumping = false;
    state.release();
  }

  @Override
  public void setState(JumpState state) {
    this.state = state;
    this.state.setHero(this);
  }
  
  @Override
  public void setSpeed(float x, float y) {
    myBody.setLinearVelocity(x, y);
  }
  
  public void landedPlatform(WorldManifold worldManifold, Platform platform){
    for(int i = 0; i < worldManifold.getNumberOfContactPoints(); i++){
      if(worldManifold.getPoints()[i].y < myBody.getPosition().y && (worldManifold.getNormal().y > 0.95 || worldManifold.getNormal().y < -0.95)){
        isJumping = false;
        setState(new OnGround());
        myBody.setGravityScale(1);
        onWall = false;
      }
      else {
        isJumping = false;
        if (!onWall)
          setState(new OnWall());
        else
          myBody.setGravityScale(1);
        onWall = true;
      }
    }
  }
  
  private void setDead() {
    dead = true;   
  }

  @Override
  public boolean isDead(){
    return dead;
  }
  
  @Override
  public void interact(GameActor actor2, WorldManifold worldManifold) {
    actor2.interactWithMinigameCharacter(this, worldManifold);
  }
  
  
  @Override
  public void interactWithPlatform(Platform platform, WorldManifold worldManifold){
    landedPlatform(worldManifold, platform);
  }
  
  
}
