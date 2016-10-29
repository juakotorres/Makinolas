package cl.makinolas.atk.actors.enemies;

import cl.makinolas.atk.GameConstants;
import cl.makinolas.atk.actors.AnimationStates.AnimationState;
import cl.makinolas.atk.actors.AnimationStates.BlockedState;
import cl.makinolas.atk.actors.AnimationStates.UnBlockedState;
import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.HBar;
import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.friend.Enemies;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.actors.items.BallActor;
import cl.makinolas.atk.actors.items.ItemFinder;
import cl.makinolas.atk.actors.platform.Platform;
import cl.makinolas.atk.actors.ui.MainBar;
import cl.makinolas.atk.utils.Formulas;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Enemy extends Monsters {
  
  protected float vx;
  private int health;
  private HBar healthBar;
  private boolean isDamaged;
  private int meleeDamage;
  private boolean dead, free;
  private final float meleeTime = 2f;
  protected final float groundTime = 0.5f;
  private float meleeAccumulator;
  private float accumulator;
  protected float groundAcc;
  private int level;
  private Enemies type;
  protected World myWorld;
  protected boolean viewGround = true;
  private AnimationState damageState;
  private AnimationState walkingState;
  private AnimationState attackState;
  private AnimationState idleState;
  private AnimationState currentState;

  protected RayCastCallback rayListener = new RayCastCallback() {
    @Override
    public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
      viewGround = true;
      return 0;
    }
  };


  /**
   * Constructor for Enemy
   * @param myWorld Box2D World
   * @param facingRight 
   */
  public Enemy(World myWorld, int givenHealth
               , int positionX, int positionY, boolean facingRight,
               int level, Enemies type, Friend parent) {
    
    this.myWorld = myWorld;
    health = givenHealth;
    meleeAccumulator = 0;
    this.type = type;
    isAttacking = false;
    healthBar = new HBar(givenHealth, health, parent.getWidth(), 4, new TextureRegion( new Texture(Gdx.files.internal("Overlays/bar_green.png"))));
    isDamaged = false;
    dead = false;
    free = true;
    meleeDamage = 45;
    accumulator = 0;
    this.level = level;
    this.parent = parent;

    isFacingRight = facingRight;
    vx = isFacingRight? 3:-3;
    // Definici�n del cuerpo del jugador.
    BodyDef myBodyDefinition = new BodyDef();
    myBodyDefinition.type = BodyDef.BodyType.DynamicBody;
    myBodyDefinition.position.set(new Vector2(positionX,positionY));
    
    // Forma del collider del jugador.
    Body myBody = myWorld.createBody(myBodyDefinition);
    
    PolygonShape shape = new PolygonShape();
    shape.setAsBox(0.6f,0.7f);
    ///
    myBody.setGravityScale(1);
    myBody.createFixture(shape, 0.5f);
    myBody.resetMassData();
    shape.dispose();
    
    // Guardar body.
    setBody(myBody);
    
    // Guardar animaciones del jugador
    setAnimation();
  }
  
  @Override
  public void act(float delta){
    if(!free){
      myBody.setLinearVelocity(0,0);
      return;
    }
    if(!viewGround){
      flip();
      viewGround = true;
    }
    myBody.setLinearVelocity(vx, myBody.getLinearVelocity().y);
    //myBody.applyForce(1, 1, 10, 10, true);


    checkHeroNear(delta);
    checkGround(delta);
    checkState(delta);

  }

  protected void checkState(float delta) {
    currentState.act(delta);
    if(isDamaged){
      if(currentState.finished()){
        isDamaged = false;
        changeStateBackToNormal();
      }
    } else if(isAttacking){
      if(currentState.finished()){
        isAttacking = false;
        changeStateBackToNormal();
      }

    }
    if(currentState.finished())
      changeState(currentState);
    changeAnimation(currentState.getActualAnimation());
  }

  private void changeStateBackToNormal() {
    if(vx != 0)
      changeState(walkingState);
    else
      changeState(idleState);
  }

  protected void checkGround(float delta) {
    groundAcc += delta;
    if(groundAcc > groundTime) {
      viewGround = false;
      myWorld.rayCast(rayListener, myBody.getPosition().x, myBody.getPosition().y, myBody.getPosition().x + vx, myBody.getPosition().y - 2);
      groundAcc = 0;
    }
  }

  private void setAnimation(){
    setMasterTexture(parent.getTexture(),parent.getWidth(), parent.getHeight());
    setMasterTexture(parent.getTexture(),parent.getWidth(),parent.getHeight());

    int[] walkAnimation = new int[parent.getWalkAnimation().length];
    int[] hurtAnimation = new int[parent.getHurtAnimation().length];
    int[] attackAnimation = new int[parent.getMeleeAnimation().length];
    int[] idleAnimation = new int[parent.getIdleAnimation().length];
    for(int i = 0; i < parent.getMeleeAnimation().length; i++){
      attackAnimation[i] = addAnimation(0.2f, parent.getMeleeAnimation()[i][1]);
    }
    for(int i = 0; i < parent.getIdleAnimation().length; i++){
      idleAnimation[i] = addAnimation(0.2f, parent.getIdleAnimation()[i][1]);
    }
    for(int i = 0; i < parent.getWalkAnimation().length; i++){
      walkAnimation[i] = addAnimation(0.2f, parent.getWalkAnimation()[i][1]);
    }
    for(int i = 0; i < parent.getHurtAnimation().length; i++){
      hurtAnimation[i] = addAnimation(0.2f, parent.getHurtAnimation()[i][1]);
    }

    attackState = new BlockedState(this, 0.2f, attackAnimation);
    idleState = new UnBlockedState(this, 0.2f, idleAnimation);
    walkingState = new UnBlockedState(this, 0.2f, walkAnimation);
    damageState = new BlockedState(this, 0.2f, hurtAnimation);
    changeStateBackToNormal();
  }
  
  @Override
  public void draw(Batch batch, float alpha){
    if(!free) return;
    super.draw(batch,alpha);
    Vector2 myPosition = myBody.getPosition();
    batch.draw(healthBar.getSprite(), myPosition.x * GameConstants.WORLD_FACTOR - getActualSprite().getRegionWidth() / 2 ,
            myPosition.y * GameConstants.WORLD_FACTOR + getActualSprite().getRegionHeight() / 2);
  }

  @Override
  public void damage(int damage, Attacks inflictor) {
    if(inflictor.getSource().isEnemy()){
      return;
    }
    if(health - damage <= 0){
      health = 0;
    } else {
      health -= damage;
    }
    if(!currentState.getBlocked() || currentState.finished()) {
      isDamaged = true;
      changeState(damageState);
    }
    Monsters source = inflictor.getSource();
    inflictor.setDead();
    healthBar.setCurrent(health);
    if(health <= 0){
      source.gainExperience(getLevel(), type);
      source.gainEffortValues(type);
      Hero.getInstance().earnMoney(getLevel(), type);
      ItemFinder.getInstance().requestDrop(myBody.getPosition().x,myBody.getPosition().y,getStage(),myWorld);
      setDead();     
    }

  }

  private void changeState(AnimationState state) {
    currentState = state;
    currentState.reset();
  }
  
  private int getLevel() {
    return level;
  }

  @Override
  public int getMeleeDamage(){
    return meleeDamage;
  }
  
  @Override
  public boolean isDead(){
    return dead;
  }

  @Override
  public void interact(GameActor actor2, WorldManifold worldManifold) {
    if(free)
      actor2.interactWithEnemy(this, worldManifold);
  }
  
  @Override
  public void interactWithAttack(Attacks attack, WorldManifold worldManifold){
    if(free)
      attack.manageInteractWithMonster(this, worldManifold);
  }
  
  @Override
  public void interactWithHero(Hero hero, WorldManifold worldManifold){
    if(free) {
      interactWithHero2(hero);
      hero.interactWithMonster(this);
    }
  }
  
  private float getBodySize(int size){
    return (0.5f*size)/22;
  }

  @Override
  public float getMonsterWidth() {
    return getBodySize(parent.getWidth());
  }

  @Override
  public void interactWithBall(BallActor ball) {
    if(free && Formulas.checkCatch(ball.getType().catchability/100f,parent.getCatchRate(),health,parent.getMaxHealth())){
      ball.roll(3, new BallActor.BrokeListener() {
        @Override
        public void onBroke(float x, float y) {
          setDead();
          Hero.getInstance().addAllie(parent);
          MainBar.getInstance().updateTeam();
        }
      });
      free = false;
      myBody.setLinearVelocity(0,0);
    }
    else if(free){
      ball.roll(2, new BallActor.BrokeListener() {
        @Override
        public void onBroke(float x, float y) {
          free = true;
          myBody.setTransform(x,y,0);
        }
      });
      free = false;
      myBody.setLinearVelocity(0,0);
    }
  }

  @Override
  public float getMonsterHeight() {
    return getBodySize(parent.getHeight());
  }

  @Override
  protected void gainExp(int level, Enemies type) {}
  
  @Override
  public float getXDirection() {
    return vx;
  }
  
  
  @Override
  public void interactWithPlatform(Platform platform, WorldManifold worldManifold) {
    if (worldManifold.getNormal().x < -0.95 || worldManifold.getNormal().x >0.95){
      vx = -vx;
      isFacingRight = !isFacingRight;
    }
  }
  
  @Override
  public void interactWithEnemy(Enemy enemy, WorldManifold worldManifold) {
    this.flip();
    enemy.flip();
  }
  
  public void flip(){
    vx=-vx;
    isFacingRight = !isFacingRight;
  }
  
  @Override
  public boolean isEnemy(){
    return true;
  }

  @Override
  public void endInteraction(GameActor actor2, WorldManifold worldManifold) {}

  public void jump() {}

  public void landedPlatform(WorldManifold worldManifold, Platform platform) {}
  
  private void checkHeroNear(float delta) {
    if(!Hero.getInstance().hasBody()){
      return;
    }
    Vector2 heroPosition = Hero.getInstance().getBody().getPosition();
    meleeAccumulator += delta;
    if(Math.abs(heroPosition.x - getBody().getPosition().x) < 3
        && Math.abs(heroPosition.y - getBody().getPosition().y) < 1
        && !isAttacking
        && meleeAccumulator > meleeTime){
      if(!currentState.getBlocked() || currentState.finished()) {
        isAttacking = true;
        meleeAccumulator = 0;
        changeState(attackState);
      }
    }
  }

  public void setDead(){
    dead = true;
  }
  

}


