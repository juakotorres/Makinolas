package cl.makinolas.atk.actors.friend;

import java.util.Observable;
import java.util.Observer;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Enemy;

public abstract class AbstractFriend implements Friend {
  
  private int health;
  private boolean dead;
  private int magic;
  private TextureRegion friendTexture;
  private int[] cutSprites;
  private int[][] walkingAnimation;
  private int[][] hurtAnimation;
  private int[][] meleeAnimation;
  private TextureRegion faceSprite;
  protected Level level;
  private int actualEvolution;
  
  
  protected void setAnimations(int[] cutSprites, int[][] walkingAnimation,
      int[][] hurtAnimation){
    this.cutSprites = cutSprites;
    this.walkingAnimation = walkingAnimation;
    this.hurtAnimation = hurtAnimation;
  }
  
  protected void setFaceSprite(TextureRegion faceSprite){
    this.faceSprite = faceSprite;
  }
  
  protected void setTexture(TextureRegion setTexture){
    friendTexture = setTexture;
  }
  
  @Override
  public void setLevel(float level){
    this.level.setLevel(level);
  }
  
  protected void setActualEvolution(int evolution){
    actualEvolution = evolution;
  }
  
  protected int getActualEvolution(){
    return actualEvolution;
  }
  
  protected abstract void initLevel(float level);
  
  @Override
  public void setVariables(int health, boolean dead) {
   this.health = health;
   this.dead = dead;    
  }
  
  @Override
  public void setMagic(int magic){
    this.magic = magic;
  }
  
  @Override
  public int getMagic(){
    return magic;
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
    return new Enemy(myWorld, friendTexture, cutSprites, 
                walkingAnimation, hurtAnimation,  getHealth(), heroPosition);
  }
  
  @Override
  public int[][] getHurtAnimation() {
    return hurtAnimation;
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
    return level.getLevel();
  }
  
  protected class Level extends Observable {
    private float level;
    
    public Level(float level){
      this.level = level;
    }
    
    public void setLevel(float newLevel) {
      synchronized (this) {
        this.level = newLevel;
      }
      setChanged();
      notifyObservers();
    }

    public synchronized float getLevel() {
      return level;
    }
  }
  
  protected class Evolution implements Observer {
    
    private float evolLevel;
    private int numberOfEvolution;
    
    public Evolution(Level level, float evolLevel, int numberOfEvolution){
      observe(level);
      this.evolLevel = evolLevel;
      this.numberOfEvolution = numberOfEvolution;
    }
    
    public void observe(Observable o) {
      o.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
      float newLevel = ((Level) o).getLevel();
      if(newLevel >= evolLevel){
       evolve(this.numberOfEvolution);
      }
    }
  }

  // Override if it has an evolution.
  protected void evolve(int numberOfEvolution){
    
  }
}
