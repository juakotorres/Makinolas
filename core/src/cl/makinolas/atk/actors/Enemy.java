package cl.makinolas.atk.actors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Enemy extends Monsters {
  
  private Animation enemyWalkingAnimation;
  private BodyDef myBodyDefinition;
  private float dt;  
  private float vx;
  private boolean isFacingRight;
  private int health;
  private boolean isDamaged;
  private boolean dead;
  
  /**
   * Constructor for Enemy
   * @param myWorld 
   * @param enemyTexture SpriteSheet of enemy animations
   * @param cutSprite dimensions of sprites [width, height]
   * @param numberOfSprite [[3], [0,0] , [0,1] , [0,2]] 3 Sprites for animation, (0,0) -> (0,1) -> (0,2)
   */
  public Enemy(World myWorld, TextureRegion enemyTexture,
               int[] cutSprite, int[][] numberOfSprite, int givenHealth) {
    
    dt = 0;
    health = givenHealth;
    isDamaged = false;
    dead = false;
    int randomNum = 0 + (int)(Math.random() * 32);
    
    if (randomNum > 16){
      isFacingRight = false;
      vx = -3;
    } else {
      isFacingRight = true;
      vx = 3;
    }
    
    // Definición del cuerpo del jugador.
    myBodyDefinition = new BodyDef();
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
    setAnimation(enemyTexture, cutSprite, numberOfSprite);
  }
  
  @Override
  public void act(float delta){
    dt += delta;      
    myBody.setLinearVelocity(vx, myBody.getLinearVelocity().y);
  }
  
  private void setAnimation(TextureRegion enemySprites, int[] cutSprite, int[][] sprites){
    TextureRegion[][] animation = enemySprites.split(cutSprite[0], cutSprite[1]);
    
    Array<TextureRegion> walking = new Array<TextureRegion>();
    
    for(int i = 1; i <= sprites[0][0]; i++){
      walking.add(animation[sprites[i][0]][sprites[i][1]]);
    }
    
    enemyWalkingAnimation = new Animation(0.2f, walking, PlayMode.LOOP);
  }
  
  @Override
  public void draw(Batch batch, float alpha){
    Vector2 myPosition = myBody.getPosition();
    TextureRegion actualSprite = enemyWalkingAnimation.getKeyFrame(dt);
    batch.draw(actualSprite, myPosition.x * 20 - actualSprite.getRegionWidth() / 2 , myPosition.y * 20 - actualSprite.getRegionHeight() / 2,
        actualSprite.getRegionWidth() / 2, getOriginY(), actualSprite.getRegionWidth(), actualSprite.getRegionHeight(), (isFacingRight)?-1:1, 1, 0);
  }

  @Override
  public void damage(int damage, Attacks inflictor) {
    health -= damage;   
    isDamaged = true;
    inflictor.setDead();
    if(health <= 0){
      dead = true;
    }

  }
  
  @Override
  public boolean isDead(){
    return dead;
  }
}
