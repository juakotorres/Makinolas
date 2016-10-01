package cl.makinolas.atk.actors.enemies;

import cl.makinolas.atk.GameConstants;
import cl.makinolas.atk.actors.*;
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
  private int width;
  private int height;
  private int meleeDamage;
  private boolean dead, free;
  private int walkAnimation;
  private int hurtAnimation;
  private final float hurtTime = 1 / 4f;
  private final float meleeTime = 2f;
  protected final float groundTime = 0.5f;
  private float meleeAccumulator;
  private float accumulator;
  protected float groundAcc;
  private float inflictorVelocity;
  private int level;
  private Enemies type;
  protected World myWorld;
  protected final float spriteTime = 1 / 5f;
  private float countMeleeFrames;
  private int[] attackAnimations;
  private int actualAnimation;
  protected boolean viewGround = true;

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
   * @param enemyTexture SpriteSheet of enemy animations
   * @param cutSprite dimensions of sprites [width, height]
   * @param numberOfSprite [[3], [0,0] , [0,1] , [0,2]] 3 Sprites for animation, (0,0) -> (0,1) -> (0,2)
   */
  public Enemy(World myWorld, TextureRegion enemyTexture,
               int[] cutSprite, int[][] numberOfSprite
               , int[][] numberOfHurtSprites, int givenHealth
               , int positionX, int positionY, int level, Enemies type, Friend parent) {
    
    this.myWorld = myWorld;
    health = givenHealth;
    width = cutSprite[0];
    height = cutSprite[1];
    inflictorVelocity = 0;
    meleeAccumulator = 0;
    this.type = type;
    isAttacking = false;
    healthBar = new HBar(givenHealth, health, cutSprite[0], 4, new TextureRegion( new Texture(Gdx.files.internal("Overlays/bar_green.png"))));
    isDamaged = false;
    dead = false;
    free = true;
    meleeDamage = 45;
    accumulator = 0;
    this.level = level;
    this.parent = parent;
    int actualPosition = positionX;

    int randomNum = actualPosition;
    
    if (randomNum >= actualPosition){
      isFacingRight = false;
      vx = -3;
    } else {
      isFacingRight = true;
      vx = 3;
    }
    
    // Definici�n del cuerpo del jugador.
    BodyDef myBodyDefinition = new BodyDef();
    myBodyDefinition.type = BodyDef.BodyType.DynamicBody;
    myBodyDefinition.position.set(new Vector2(randomNum,positionY));
    
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
    setAnimation(enemyTexture, cutSprite);
    hurtAnimation = addAnimation(0.2f,  numberOfHurtSprites);
    walkAnimation = addAnimation(0.2f, numberOfSprite);
    changeAnimation(walkAnimation);
   
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
    checkDamage(delta, inflictorVelocity);
    
    checkHeroNear(delta);
    checkMelee(delta);
    checkGround(delta);

  }

  protected void checkGround(float delta) {
    groundAcc += delta;
    if(groundAcc > groundTime) {
      viewGround = false;
      myWorld.rayCast(rayListener, myBody.getPosition().x, myBody.getPosition().y, myBody.getPosition().x + vx, myBody.getPosition().y - 2);
      groundAcc = 0;
    }
  }

  private void checkMelee(float delta) {
    if(isAttacking){
      countMeleeFrames += delta;
      if(countMeleeFrames > spriteTime){
        if(actualAnimation  < attackAnimations.length) {
          changeAnimation(attackAnimations[actualAnimation]);
          countMeleeFrames = 0;
          actualAnimation += 1;
        } else {
          isAttacking = false;
          countMeleeFrames = 0;
          actualAnimation = 0;
        }
      }
    }
    else if(!isDamaged){
      countMeleeFrames = 0;
      isAttacking = false;
      actualAnimation = 0;
      changeAnimation(walkAnimation);
    } else {
      countMeleeFrames = 0;
    }
    
  }

  protected void checkDamage(float delta, float inflictorVel) {
    if(isDamaged){
      myBody.setLinearVelocity(new Vector2(inflictorVel,0));
      accumulator += delta;
      if(accumulator > hurtTime){
        isDamaged = false;
        changeAnimation(walkAnimation);
        accumulator = 0;
      }
    }    
  }

  private void setAnimation(TextureRegion enemySprites, int[] cutSprite){
    setMasterTexture(enemySprites,cutSprite[0],cutSprite[1]);
    attackAnimations = new int[parent.getMeleeAnimation().length];
    countMeleeFrames = 0;
    for(int i = 0; i < parent.getMeleeAnimation().length; i++){
      attackAnimations[i] = addAnimation(0.2f, parent.getMeleeAnimation()[i][1]);
    }  
    actualAnimation = 0;
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
    isDamaged = true;
    changeAnimation(hurtAnimation);
    Monsters source = inflictor.getSource();
    inflictorVelocity = inflictor.getXVelocity();
    inflictor.setDead();
    healthBar.setCurrent(health);
    if(health <= 0){
      source.gainExperience(getLevel(), type);
      Hero.getInstance().earnMoney(getLevel(), type);
      ItemFinder.getInstance().requestDrop(myBody.getPosition().x,myBody.getPosition().y,getStage(),myWorld);
      setDead();     
    }

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
    return getBodySize(width);
  }

  @Override
  public void interactWithBall(BallActor ball) {
    if(free && Formulas.checkCatch(ball.getType().catchability/100f,0.9f,health,100)){
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
    return getBodySize(height);
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

  public void jump() {}

  public void landedPlatform(WorldManifold worldManifold, Platform platform) {}
  
  private void checkHeroNear(float delta) {
    Vector2 heroPosition = Hero.getInstance().getBody().getPosition();
    meleeAccumulator += delta;
    if(Math.abs(heroPosition.x - getBody().getPosition().x) < 3
        && Math.abs(heroPosition.y - getBody().getPosition().y) < 1
        && !isAttacking
        && meleeAccumulator > meleeTime){
      isAttacking = true;
      meleeAccumulator = 0;
    }
  }
  
  public void setDead(){
    dead = true;
  }
  

}


