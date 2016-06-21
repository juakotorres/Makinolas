package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Enemy;

public class AbstractFriend implements Friend {
  
  private int health;
  private boolean dead;
  private TextureRegion friendTexture;
  private int[] cutSprites;
  private int walkingFrame;
  private int[][] walkingAnimation;
  private int hurtFrame;
  private int[][] hurtAnimation;
  private TextureRegion faceSprite;
  private float level;
  
  
  protected void setAnimations(int[] cutSprites, int walkingFrame, int[][] walkingAnimation,
      int hurtFrame, int[][] hurtAnimation){
    this.cutSprites = cutSprites;
    this.walkingAnimation = walkingAnimation;
    this.hurtAnimation = hurtAnimation;
    this.hurtFrame = hurtFrame;
    this.walkingFrame = walkingFrame;
  }
  
  protected void setFaceSprite(TextureRegion faceSprite){
    this.faceSprite = faceSprite;
  }
  
  protected void setTexture(TextureRegion setTexture){
    friendTexture = setTexture;
  }
  
  protected void setLevel(float level){
    this.level = level;
  }
  
  @Override
  public void setVariables(int health, boolean dead) {
   this.health = health;
   this.dead = dead;    
  }
  
  @Override
  public int getHealth() {
    return health;
  }
  
  @Override
  public boolean getDead() {
    return dead;
  }
  
  @Override
  public Enemy returnEnemy(World myWorld, int heroPosition) {
    return new Enemy(myWorld, friendTexture, cutSprites, walkingFrame,
                walkingAnimation, hurtFrame, hurtAnimation,  getHealth(), heroPosition);
  }

  @Override
  public int getHurtFrames() {
    return hurtFrame;
  }

  @Override
  public int[][] getHurtAnimation() {
    return hurtAnimation;
  }

  @Override
  public int getWalkFrames() {
    return walkingFrame;
  }

  @Override
  public int[][] getWalkAnimation() {
    return walkingAnimation;
  }

  @Override
  public TextureRegion getTexture() {
    return friendTexture;
  }

  @Override
  public int getWidth() {
    return cutSprites[0];
  }

  @Override
  public int getHeight() {
    return cutSprites[1];
  }

  @Override
  public TextureRegion getFriendFaceSprite() {
    return faceSprite;
  }

  @Override
  public float getLevel() {
    return level;
  }
}
