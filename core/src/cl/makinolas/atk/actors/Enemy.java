package cl.makinolas.atk.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.GameConstants;

public class Enemy extends Monsters {


  private float vx;
  private int health;
  private HBar healthBar;
  private boolean isDamaged;
  private boolean isAttacking;
  private int meleeDamage;
  private boolean dead;
  private int walkAnimation;
  private int hurtAnimation;
  private final float hurtTime = 1 / 4f;
  private float accumulator;
  
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
               , int heroPosition) {
    
    health = givenHealth;
    isAttacking = true;
    healthBar = new HBar(givenHealth, health, cutSprite[0], 4, new TextureRegion( new Texture(Gdx.files.internal("Overlays/bar_green.png"))));
    isDamaged = false;
    dead = false;
    meleeDamage = 10;
    accumulator = 0;
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
    inflictor.setDead();
    healthBar.setCurrent(health);
    if(health <= 0){
      dead = true;
    }

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
  public void meleedamage(int damage) {
    
  }

  @Override
  public void interact(GameActor actor2) {
    actor2.interactWithEnemy(this);
  }
  
  @Override
  public void interactWithAttack(Attacks attack){
    this.damage(attack.getAttackDamage(), attack);
  }
  
  @Override
  public void interactWithHero(Hero hero){
    interactWithHero2(hero);
    hero.interactWithEnemy2(this);
  }

  public void interactWithHero2(Hero hero) {
    meleeAttack(hero, isAttacking);   
  }
  
}
