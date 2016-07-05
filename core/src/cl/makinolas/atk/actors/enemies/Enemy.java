package cl.makinolas.atk.actors.enemies;

import cl.makinolas.atk.GameConstants;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.friend.Enemies;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.actors.items.BallActor;
import cl.makinolas.atk.stages.AbstractStage;
import cl.makinolas.atk.utils.Formulas;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;

import cl.makinolas.atk.GameConstants;
import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.HBar;
import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.friend.Enemies;

public class Enemy extends Monsters {
  
  protected float vx;
  private int health;
  private HBar healthBar;
  private boolean isDamaged;
  private int width;
  private int height;
  private int meleeDamage;
  private boolean dead;
  private int walkAnimation;
  private int hurtAnimation;
  private final float hurtTime = 1 / 4f;
  private float accumulator;
  private int level;
  private Enemies type;
  private Friend parent;
  
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
               , int heroPosition, int level, Enemies type, Friend parent) {
    
    health = givenHealth;
    width = cutSprite[0];
    height = cutSprite[1];
    this.type = type;
    isAttacking = true;
    healthBar = new HBar(givenHealth, health, cutSprite[0], 4, new TextureRegion( new Texture(Gdx.files.internal("Overlays/bar_green.png"))));
    isDamaged = false;
    dead = false;
    meleeDamage = 10;
    accumulator = 0;
    this.level = level;
    this.parent = parent;
    int actualPosition = heroPosition / 20;

    int randomNum = actualPosition  + (int)(Math.random() * 16) - 7;
    
    if (randomNum > actualPosition){
      isFacingRight = false;
      vx = -3;
    } else {
      isFacingRight = true;
      vx = 3;
    }
    
    // Definici�n del cuerpo del jugador.
    BodyDef myBodyDefinition = new BodyDef();
    myBodyDefinition.type = BodyDef.BodyType.DynamicBody;
    myBodyDefinition.position.set(new Vector2(randomNum,3));
    
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
    myBody.setLinearVelocity(vx, myBody.getLinearVelocity().y);
    //myBody.applyForce(1, 1, 10, 10, true);
    if(isDamaged){
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
  }
  
  @Override
  public void draw(Batch batch, float alpha){
    super.draw(batch,alpha);
    Vector2 myPosition = myBody.getPosition();
    batch.draw(healthBar.getSprite(), myPosition.x * GameConstants.WORLD_FACTOR - getActualSprite().getRegionWidth() / 2 ,
            myPosition.y * GameConstants.WORLD_FACTOR + getActualSprite().getRegionHeight() / 2);
  }

  @Override
  public void damage(int damage, Attacks inflictor) {
    health -= damage;   
    isDamaged = true;
    changeAnimation(hurtAnimation);
    Monsters source = inflictor.getSource();
    inflictor.setDead();
    healthBar.setCurrent(health);
    if(health <= 0){
      source.gainExperience(getLevel(), type);
      dead = true;
      
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
    actor2.interactWithEnemy(this);
  }
  
  @Override
  public void interactWithAttack(Attacks attack, WorldManifold worldManifold){
    this.damage(attack.getAttackDamage(), attack);
  }
  
  @Override
  public void interactWithHero(Hero hero, WorldManifold worldManifold){
    interactWithHero2(hero);
    hero.interactWithMonster(this);
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
    if(Formulas.checkCatch(ball.getType().catchability/100f,0.9f,health,100)){
      dead = true;
      ((AbstractStage) getStage()).addAllie(parent);
      ball.setDead();
    }
    else{
      ball.setDead();
    }
  }

  @Override
  public float getMonsterHeight() {
    return getBodySize(height);
  }

  @Override
  protected void gainExp(int level, Enemies type) {}
  
}
