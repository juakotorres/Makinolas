package cl.makinolas.atk.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.Fireball;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.actors.friend.Pichu;
import cl.makinolas.atk.actors.friend.Scyther;
import cl.makinolas.atk.actors.ui.MobileGroup;
import cl.makinolas.atk.stages.GameStage;

public class Hero extends Monsters {

  private boolean changing;
  private int changeIndex;
  private boolean isJumping;
  private int health;
  private int magic;
  private boolean isDamaged;
  private boolean isAttacking;
  private float numberOfAttackingFrames;
  private float countMeleeFrames;
  private boolean dead;
  private World myWorld;
  private int walkAnimation;
  private int hurtAnimation;
  private int meleeAnimation;
  private final float hurtTime = 1 / 4f;
  private float accumulator;
  private Array<Friend> allies;
  private Friend actualFriend;
  private int indexFriend;
  private BodyDef myBodyDefinition;
  private MobileGroup group;
  
  public Hero(World myWorld, MobileGroup g) {
    
    isJumping = false;
    isFacingRight = false;
    health = 100;
    magic = 1000;
    isDamaged = false;
    isAttacking = false;
    dead = false;
    changing = false;
    changeIndex = 0;
    accumulator = 0;
    countMeleeFrames = 0;    
    group = g;
    // Set team for player;
    allies = new Array<Friend>();
    addAllie(new Scyther());
    addAllie(new Pichu());
    
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
  
  private void addAllie(Friend friend) {
    friend.setVariables(100);
    allies.add(friend);
  }

  @Override
  public void act(float delta){
    checkChangingAllie();
    int vx = 0;
    if (Gdx.input.isKeyPressed(Keys.LEFT) || group.leftPressed()){
      vx -= 7;
      if (isFacingRight){
        isFacingRight = false;
      }
    }
    if (Gdx.input.isKeyPressed(Keys.RIGHT) || group.rightPressed()){
      vx += 7;
      if (!isFacingRight){
        isFacingRight = true;
      }
    }
    if (Gdx.input.isKeyJustPressed(Keys.SPACE) || group.upPressed()){
      if(!isJumping){
        myBody.applyLinearImpulse(0, getImpulse(), myBody.getPosition().x, myBody.getPosition().y, true);
        isJumping = true;
      }
    }
    if (Gdx.input.isKeyJustPressed(Keys.NUM_1)){
      if(indexFriend != 0 && !allies.get(0).getDead()){
        setNewAllie(0);
      }
    } else if (Gdx.input.isKeyJustPressed(Keys.NUM_2)){
      if(indexFriend != 1 && !allies.get(1).getDead()){
        setNewAllie(1);
      }
    }
    if ((Gdx.input.isKeyJustPressed(Keys.Z) || group.AJustPressed()) && magic > 100){
      magic -= 100;
      GameActor fireball = new Fireball(myWorld, myBody.getPosition().x,myBody.getPosition().y,isFacingRight, this);
      ((GameStage) getStage()).addGameActor(fireball);
    }
    if (Gdx.input.isKeyJustPressed(Keys.X)){
      if(!isAttacking){
        isAttacking = true;
        changeAnimation(meleeAnimation);
      }
    }
    myBody.setLinearVelocity(vx, myBody.getLinearVelocity().y);
    
    ((GameStage) getStage()).changeCamera(myBody.getPosition().x , myBody.getPosition().y );
    
    checkDamage(delta);
    checkMelee(delta);
    giveMagic();
    
  }
  
  private void checkChangingAllie() {
    if(changing){
      setNewAllie(changeIndex);
      changing = false;
    }
  }

  private void changeAllie() {
    changing = true;
    actualFriend.isDead();
    lookForAliveAllie();
  }

  private void lookForAliveAllie() {
    for(int i = 0; i < allies.size; i++){
      if(!allies.get(i).getDead()){
        changeIndex = i; 
      }
    }
    if(allies.get(changeIndex).getDead()){
      heroIsDead();
    }
  }

  private void heroIsDead() {
    dead = true;   
  }

  @Override
  public boolean isDead(){
    return dead;
  }
  
  private void checkDamage(float delta) {
    if(isDamaged){
      accumulator += delta;
      if(accumulator > hurtTime){
        isDamaged = false;
        changeAnimation(walkAnimation);
        accumulator = 0;
      }
    }
  }

  private void giveMagic() {
    if(magic < 1000){
      magic = ((magic + 1)%1001);
    }    
  }

  private void checkMelee(float delta) {
    if(isAttacking && countMeleeFrames <= numberOfAttackingFrames){
      countMeleeFrames += Math.floor(delta*100)/100;
    }
    else if(!isDamaged){
      countMeleeFrames = 0;
      isAttacking = false;
      reloadAnimation(meleeAnimation, 0.2f, actualFriend.getMeleeAnimation());
      changeAnimation(walkAnimation);
    } else {
      countMeleeFrames = 0;
      reloadAnimation(meleeAnimation, 0.2f, actualFriend.getMeleeAnimation());
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
    walkAnimation = addAnimation(0.2f, actualFriend.getWalkAnimation());
    hurtAnimation = addAnimation(0.2f, actualFriend.getHurtAnimation());
    meleeAnimation = addAnimation(0.2f, actualFriend.getMeleeAnimation());
    numberOfAttackingFrames = actualFriend.getMeleeFrame() * 0.2f;
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
      changeAllie();
    }
  }


  @Override
  public int getMeleeDamage() {
    return 10;
  }
  
  public Friend getFriend(){
    return actualFriend;
  }
  
  private void setNewAllie(int index){
    actualFriend.setVariables(health);
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
  
  public void evolved(){
    setAnimation();
  }

  @Override
  public void interact(GameActor actor2){
    actor2.interactWithHero(this);
  }
  
  @Override
  public void interactWithPlatform(Platform platform){
    landedPlatform();
  }
  
  @Override
  public void interactWithAttack(Attacks attack){
    this.damage(attack.getAttackDamage(), attack);
  }
  
  @Override
  public void interactWithEnemy(Enemy enemy){
    interactWithEnemy2(enemy);
    enemy.interactWithHero2(this);
  }

  public void interactWithEnemy2(Enemy enemy) {
    meleeAttack(enemy, isAttacking);  
  }
  
}
