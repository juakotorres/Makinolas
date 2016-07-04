package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Enemy;
import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;

public interface Friend {  
  public void setVariables(int health, int magic);
  public void setHealth(int health);
  public int getHealth(); 
  public int getMaxHealth();
  public void setMagic(int magic);
  public int getMaxMagic();
  public int getMagic();
  public boolean getDead();
  public void isDead();
  public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source);
  public int[][] getHurtAnimation();
  public int[][] getWalkAnimation();
  public int[][] getMeleeAnimation();
  public int getMeleeFrame();
  public TextureRegion getTexture();
  public int getWidth();
  public int getHeight();
  public int getLevel();
  public void gainExperience(int wildLevel);
  public double thisLevelExp();
  public TextureRegion getFriendFaceSprite();
  public Enemy returnEnemy(World myWorld, int heroPosition);
}
