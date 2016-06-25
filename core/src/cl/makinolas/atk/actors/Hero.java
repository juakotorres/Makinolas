package cl.makinolas.atk.actors;

import cl.makinolas.atk.actors.friend.Charmander;
import cl.makinolas.atk.actors.friend.Eevee;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.actors.items.Inventory;
import cl.makinolas.atk.stages.GameStage;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Hero extends Monsters {

  private boolean isJumping;
  private int health;
  private int magic;
  private boolean isDamaged;
  private boolean dead;
  private World myWorld;
  private int walkAnimation;
  private int hurtAnimation;
  private final float hurtTime = 1 / 4f;
  private float accumulator;
  private Array<Friend> allies;
  private Friend actualFriend;
  private int indexFriend;
  private BodyDef myBodyDefinition;
  private Inventory inventory;
  private int vx;

  public Hero(World myWorld) {
    
    isJumping = false;
    isFacingRight = false;
    health = 100;
    magic = 1000;
    isDamaged = false;
    dead = false;
    accumulator = 0;
    inventory = new Inventory(this);
    vx = 0;

    // Set team for player;
    allies = new Array<Friend>();
    Friend allie = new Eevee();
    Friend allie2 = new Charmander();
    allie.setVariables(health, dead);
    allie2.setVariables(health, dead);
    allies.add(allie);
    allies.add(allie2);
    // Set actual allie
    actualFriend = allies.get(0);
    indexFriend = 0;
    // define player world
    this.myWorld = myWorld;
    // Set correct collider.
    myBodyDefinition = new BodyDef();
    myBodyDefinition.type = BodyDef.BodyType.DynamicBody;
    setSizeCollider(new Vector2(3,4),true);
    
    // Guardar animaciones del jugador
    setAnimation();
    changeAnimation(walkAnimation);
  }
  
  @Override
  public void act(float delta){
    myBody.setLinearVelocity(vx, myBody.getLinearVelocity().y);
    
    ((GameStage) getStage()).changeCamera(myBody.getPosition().x , myBody.getPosition().y );
    
    if(isDamaged){
      accumulator += delta;
      if(accumulator > hurtTime){
        isDamaged = false;
        changeAnimation(walkAnimation);
        accumulator = 0;
      }
    }
    if(magic < 1000){
      magic = ((magic + 1)%1001);
    }
  }
  
  private float getImpulse() {
    return getBody().getMass()*12; // El 12 se busc� por testing.
  }

  public void landedPlatform(){
    isJumping = false;
  }

  private void setAnimation(){
    setMasterTexture(actualFriend.getTexture(),actualFriend.getWidth(),actualFriend.getHeight());
    walkAnimation = addAnimation(actualFriend.getWalkFrames(),0.2f, actualFriend.getWalkAnimation());
    hurtAnimation = addAnimation(actualFriend.getHurtFrames(),0.2f, actualFriend.getHurtAnimation());
  }
  
  @Override
  public boolean isHero(){
    return true;
  }

  public int getHealth(){
    return health;
  }
  
  public int getMagic(){
    return magic;
  }


  @Override
  public void damage(int damage, Attacks inflictor)  {
    if(!inflictor.getSource().isHero()){
      health -= damage;   
      isDamaged = true;
      changeAnimation(hurtAnimation);
      inflictor.setDead();
    }
    if(health <= 0){
      dead = true;
    }

  }
  
  @Override
  public boolean isDead(){
    return dead;
  }


  @Override
  public int getMeleeDamage() {
    return 0;
  }
  
  public Friend getFriend(){
    return actualFriend;
  }

  @Override
  public void meleedamage(int damage) {
    health -= damage;   
    isDamaged = true;
    changeAnimation(hurtAnimation);
    if(health <= 0){
      dead = true;
    }   
  }
  
  private void setNewAllie(int index){
    actualFriend.setVariables(health, dead);
    allies.set(indexFriend, actualFriend);
    actualFriend = allies.get(index);
    health = actualFriend.getHealth();
    indexFriend = index;
    setSizeCollider(getBody().getPosition(), false);
    setAnimation();
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

  public Inventory getInventory() {
    return inventory;
  }

  public void moveHorizontal(int i) {
    vx += 7*i;
    if(vx!=0)
      isFacingRight = (vx>0);
  }


  public void jump() {
    if(!isJumping){
      myBody.applyLinearImpulse(0, getImpulse(), myBody.getPosition().x, myBody.getPosition().y, true);
      isJumping = true;
    }
  }

  public void attackPrimary() {
    if(magic>=100){
      magic -= 100;
      GameActor fireball = new Fireball(myWorld, myBody.getPosition().x,myBody.getPosition().y,isFacingRight, this);
      ((GameStage) getStage()).addGameActor(fireball);
    }
  }

  public void attackSecondary() {

  }


  public void changeAllie(int index) {
    if(indexFriend != index){
      setNewAllie(index);
    }
  }
}
