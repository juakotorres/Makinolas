package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.enemies.Enemy;

public interface Friend {  
  public void setVariables(int health, int magic);
  public void setHealth(int health);
  public int getHealth(); 
  public int getMaxHealth();
  public void setMagic(int magic);
  public int getMaxMagic();
  public int getMagic();
  public void setDead(boolean dead);
  public boolean getDead();
  public void isDead();
  public void setExp(double d);
  public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source);
  public int[][] getIdleAnimation();
  public int[][] getHurtAnimation();
  public int[][] getWalkAnimation();
  public int[][] getMeleeAnimation();
  public int[][] getSpecialAnimation();
  public int getMeleeFrame();
  public TextureRegion getTexture();
  public int getWidth();
  public int getHeight();
  public int getLevel();
  public void gainExperience(int wildLevel, Enemies type);
  public double thisLevelExp();
  public double getNextExperience();
  public TextureRegion getFriendFaceSprite();
  public Enemy returnEnemy(World myWorld, int positionX, int positionY, boolean facingRight);
  public Enemies getType();
  public int getAttack();
  public int getDefense();
  public int getSpecialAttack();
  public int getSpecialDefense();
  public int getSpeed();

  int getCatchRate();

  public Enemy returnLongRangeEnemy(World myWorld, int heroPosition);
  public Enemy returnPhysicalEnemy(World myWorld, int heroPosition);
  public String getName();
  public Enemy returnStayAndShootEnemy(World myWorld, int positionX, int positionY, boolean facingRight);
  public Enemy returnFlyWaveAndDropEnemy(World myWorld, int positionX, int positionY, boolean facingRight);
  public Enemy returnJumperEnemy(World myWorld, int positionX, int positionY, boolean facingRight);
  public Enemy returnFollowerEnemy(World myWorld, int positionX, int positionY, boolean facingRight);

  void setIvs(int individualValue);
  int getIvs();
  void setEvs(int effortValue1, int effortValue2);
  int getEv1();
  int getEv2();
  void addHpEv(int n);
  void addAttackEv(int n);
  void addDefenseEv(int n);
  void addSpAttackEv(int n);
  void addSpDefenseEv(int n);
  void addSpeedEv(int n);

}
