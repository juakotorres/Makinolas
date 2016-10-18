package cl.makinolas.atk.actors.friend;

import java.util.Observable;
import java.util.Observer;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.ShootAttack;
import cl.makinolas.atk.actors.attacks.states.DragonBreathState;
import cl.makinolas.atk.actors.enemies.Enemy;
import cl.makinolas.atk.actors.enemies.FlyWaveAndDropEnemy;
import cl.makinolas.atk.actors.enemies.FollowerEnemy;
import cl.makinolas.atk.actors.enemies.JumperEnemy;
import cl.makinolas.atk.actors.enemies.LongRangeEnemy;
import cl.makinolas.atk.actors.enemies.PhysicalEnemy;
import cl.makinolas.atk.actors.enemies.StayAndShootEnemy;
import cl.makinolas.atk.actors.ui.MainBar;
import cl.makinolas.atk.utils.Formulas;

public abstract class AbstractFriend implements Friend {
  
  private int health;
  private int hp;
  private int attack;
  private int defense;
  private int spAttack;
  private int spDefense;
  private int speed;
  private int magic;
  private int maxMagic;
  private boolean dead;
  private TextureRegion friendTexture;
  private int[] cutSprites;
  private int[][] idleAnimation;
  private int[][] walkingAnimation;
  private int[][] hurtAnimation;
  private int[][] meleeAnimation;
  private int[][] specialAnimation;
  private TextureRegion faceSprite;
  protected Level level;
  private int actualEvolution;
  public Enemies friend;
  
  protected void setCutSprites(int width, int height){
    this.cutSprites = new int[]{width, height};
  }
  
  protected void setAnimations(int[] cutSprites, int[][] walkingAnimation,
      int[][] hurtAnimation){
    this.cutSprites = cutSprites;
    this.walkingAnimation = walkingAnimation;
    this.hurtAnimation = hurtAnimation; 
  }
  
  protected void setMeleeAnimation(int beginMeleeAnimation, int endMeleeAnimation){
    this.meleeAnimation = new int[endMeleeAnimation - beginMeleeAnimation + 1][];
    for (int i = beginMeleeAnimation; i <= endMeleeAnimation; i++ ){
      this.meleeAnimation[i - beginMeleeAnimation] = new int[]{0,i};
    }
  }
  
  protected void setWalkAnimation(int beginWalkAnimation, int endWalkAnimation){
    this.walkingAnimation = new int[endWalkAnimation - beginWalkAnimation + 1][];
    for (int i = beginWalkAnimation; i <= endWalkAnimation; i++ ){
      this.walkingAnimation[i - beginWalkAnimation] = new int[]{0,i};
    }
  }
  
  protected void setHurtAnimation(int beginHurtAnimation, int endHurtAnimation){
    this.hurtAnimation = new int[endHurtAnimation - beginHurtAnimation + 1][];
    for (int i = beginHurtAnimation; i <= endHurtAnimation; i++ ){
      this.hurtAnimation[i - beginHurtAnimation] = new int[]{0,i};
    }
  }
  
  protected void setIdleAnimation(int beginIdleAnimation, int endIdleAnimation){
    this.idleAnimation = new int[endIdleAnimation - beginIdleAnimation + 1][];
    for (int i = beginIdleAnimation; i <= endIdleAnimation; i++ ){
      this.idleAnimation[i - beginIdleAnimation] = new int[]{0,i};
    }
  }
  
  protected void setSpecialAnimation(int beginSpecialAnimation, int endSpecialAnimation){
    this.specialAnimation = new int[endSpecialAnimation - beginSpecialAnimation + 1][];
    for (int i = beginSpecialAnimation; i <= endSpecialAnimation; i++ ){
      this.specialAnimation[i - beginSpecialAnimation] = new int[]{0,i};
    }
  }
  
  protected void setMeleeAnimation(int... positions){
    this.meleeAnimation = new int[positions.length][];
    for (int i = 0; i < positions.length; i++ ){
      this.meleeAnimation[i] = new int[]{0,positions[i]};
    }
  }
  
  protected void setWalkAnimation(int... positions){
    this.walkingAnimation = new int[positions.length][];
    for (int i = 0; i < positions.length; i++ ){
      this.walkingAnimation[i] = new int[]{0,positions[i]};
    }
  }
  
  protected void setHurtAnimation(int... positions){
    this.hurtAnimation = new int[positions.length][];
    for (int i = 0; i < positions.length; i++ ){
      this.hurtAnimation[i] = new int[]{0,positions[i]};
    }
  }
  
  protected void setIdleAnimation(int... positions){
    this.idleAnimation = new int[positions.length][];
    for (int i = 0; i < positions.length; i++ ){
      this.idleAnimation[i] = new int[]{0,positions[i]};
    }
  }
  
  protected void setSpecialAnimation(int... positions){
    this.specialAnimation = new int[positions.length][];
    for (int i = 0; i < positions.length; i++ ){
      this.specialAnimation[i] = new int[]{0,positions[i]};
    }
  }
  
  protected void setAnimations(int[] cutSprites, int beginWalkAnimation, int endWalkAnimation,
      int beginHurtAnimation, int endHurtAnimation, int beginMeleeAnimation, int endMeleeAnimation){
    
    this.cutSprites = cutSprites;
    this.walkingAnimation = new int[1][];
    this.hurtAnimation = new int[1][];
    this.meleeAnimation = new int[1][];
    for (int i = beginWalkAnimation; i <= endWalkAnimation; i++ ){
      this.walkingAnimation[i - beginWalkAnimation] = new int[]{0,i};
    }
    for (int i = beginHurtAnimation; i <= endHurtAnimation; i++ ){
      this.hurtAnimation[i - beginHurtAnimation] = new int[]{0,i};
    }
    for (int i = beginMeleeAnimation; i <= endMeleeAnimation; i++ ){
      this.meleeAnimation[i - beginMeleeAnimation] = new int[]{0,i};
    }
  }

  @Override
  public void setExp(double d) {
    level.nextExpLevel = d;
  }

  protected void setFaceSprite(TextureRegion faceSprite){
    this.faceSprite = faceSprite;
  }
  
  protected void setTexture(TextureRegion setTexture){
    friendTexture = setTexture;
  }
  
  @Override
  public void gainExperience(int wildLevel, Enemies type){
    level.gainExp(wildLevel, type);
  }
  
  @Override
  public double thisLevelExp(){
    return level.getLevelMax();
  }
  
  protected void setActualEvolution(int evolution){
    actualEvolution = evolution;
  }
  
  protected int getActualEvolution(){
    return actualEvolution;
  }
  
  protected abstract void initLevel(int level);
  
  @Override
  public void setVariables(int health, int magic) {
   setMagic(magic);
   setHealth(health);
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
  public boolean getDead(){
    return dead;
  }

  @Override
  public void setDead(boolean dead) {
    this.dead = dead;
  }

  @Override
  public void isDead(){
    dead = true;
  }
  
  protected void initDead(){
    dead = false;
  }
  
  @Override
  public int getHealth() {
    return health;
  }
  
  @Override
  public Enemy returnEnemy(World myWorld, int positionX, int positionY, boolean facingRight) {
    return new Enemy(myWorld, friendTexture, cutSprites,
                walkingAnimation, hurtAnimation,  getHealth(),
                positionX, positionY, facingRight, getLevel(), friend, this);
  }
  
  @Override
  public Enemy returnStayAndShootEnemy(World myWorld, int positionX, int positionY, boolean facingRight) {
    return new StayAndShootEnemy(myWorld, friendTexture, cutSprites,
                walkingAnimation, hurtAnimation,  getHealth(),
                positionX, positionY, facingRight, getLevel(), friend, this);
  }
  
  @Override
  public Enemy returnFlyWaveAndDropEnemy(World myWorld, int positionX, int positionY, boolean facingRight) {
    return new FlyWaveAndDropEnemy(myWorld, friendTexture, cutSprites,
                walkingAnimation, hurtAnimation,  getHealth(),
                positionX, positionY, facingRight, getLevel(), friend, this);
  }
  
  @Override
  public Enemy returnJumperEnemy(World myWorld, int positionX, int positionY, boolean facingRight) {
    return new JumperEnemy(myWorld, friendTexture, cutSprites,
                walkingAnimation, hurtAnimation,  getHealth(),
                positionX, positionY, facingRight, getLevel(), friend, this);
  }
  
  @Override
  public Enemy returnFollowerEnemy(World myWorld, int positionX, int positionY, boolean facingRight) {
    return new FollowerEnemy(myWorld, friendTexture, cutSprites,
                walkingAnimation, hurtAnimation,  getHealth(),
                positionX, positionY, facingRight, getLevel(), friend, this);
  }
  
  @Override
  public Enemy returnLongRangeEnemy(World myWorld, int heroPosition) {
    return new LongRangeEnemy(myWorld, friendTexture, cutSprites, 
                walkingAnimation, hurtAnimation,  getHealth(), heroPosition, getLevel(), friend,this);
  }
  @Override
  public Enemy returnPhysicalEnemy(World myWorld, int heroPosition) {
    return new PhysicalEnemy(myWorld, friendTexture, cutSprites, 
                walkingAnimation, hurtAnimation,  getHealth(), heroPosition, getLevel(), friend,this);
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
  public int[][] getMeleeAnimation() {
    return meleeAnimation;
  }
  
  @Override
  public int[][] getIdleAnimation() {
    return idleAnimation;
  }
  
  @Override
  public int[][] getSpecialAnimation() {
    return specialAnimation;
  }
  
  @Override
  public int getMeleeFrame() {
    return meleeAnimation.length;
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
  public int getLevel() {
    return level.getLevel();
  }

  @Override
  public double getNextExperience(){
    return level.getNextExpLevel();
  }

  protected class Level extends Observable {
    private double nextExpLevel;
    private double expLevelMax;
    private int level;
    
    public Level(int level){
      this.level = level;
      nextExpLevel = Formulas.nextExpLevel(level);
      expLevelMax = nextExpLevel;
    }
    
    public void gainExp(int wildPokemonLevel, Enemies type){
      this.nextExpLevel -= Formulas.gainExp(level, wildPokemonLevel, type);
      if(nextExpLevel < 0 && level < 100){
        double freeExp = Math.abs(nextExpLevel);
        levelUp(level + 1);
        this.nextExpLevel -= freeExp;
      }
    }
    
    private void levelUp(int newLevel) {
      synchronized (this) {
        this.level = newLevel;
        this.nextExpLevel = Formulas.nextExpLevel(newLevel);
        expLevelMax = nextExpLevel;
        setFriendStats();
      }
      setChanged();
      notifyObservers();
    }
    
    public synchronized int getLevel() {
      return level;
    }
    
    public double getLevelMax(){
      return expLevelMax;
    }

    public double getNextExpLevel(){
      return nextExpLevel;
    }

  }
  
  protected class Evolution implements Observer {
    
    private float evolLevel;
    private int numberOfEvolution;
    private boolean evolved;
    
    public Evolution(Level level, float evolLevel, int numberOfEvolution){
      observe(level);
      this.evolLevel = evolLevel;
      this.numberOfEvolution = numberOfEvolution;
      evolved = false;
    }
    
    public void observe(Observable o) {
      o.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
      float newLevel = ((Level) o).getLevel();
      if(newLevel >= evolLevel && getActualEvolution() < numberOfEvolution && getActualEvolution() + 1 == numberOfEvolution){
       evolve(this.numberOfEvolution);
       Hero.getInstance().evolved();
       evolved = true;
       setFriendStats();
      }
    }
  }

  // Override if it has an evolution.
  protected void evolve(int numberOfEvolution){
    
  }
  

  private void setFriendStats() {
    double percentage = (health * 100) /getMaxHealth();
    setStats();
    //System.out.println("Health previous: " + health);
    //System.out.println("health post: " + ((percentage * getMaxHealth()) / 100 + 1));
    setHealth((int) (percentage * getMaxHealth()) / 100 + 1);    
    MainBar.getInstance().setBars();
  }
  
  @Override
  public int getMaxHealth(){
    return hp;
  }
  
  @Override
  public void setHealth(int health){
    this.health = health > hp? hp:health;
    if(this.health <= 0){
      this.health = 0;
    }
  }

  protected void setStats(){
    this.hp = Formulas.getHpStat(friend.hpBase , level.level);
    this.attack = Formulas.getOtherStat(friend.attackBase, level.level);
    this.defense = Formulas.getOtherStat(friend.defenseBase, level.level);
    this.spAttack = Formulas.getOtherStat(friend.spAttackBase, level.level);
    this.spDefense = Formulas.getOtherStat(friend.spDefenseBase, level.level);
    this.speed = Formulas.getOtherStat(friend.speedBase, level.level);
  }
  
  @Override
  public int getMaxMagic(){
    return maxMagic;
  }
  
  protected void setMaxMagic(int maxMagic){
    this.maxMagic = maxMagic;
    this.magic = maxMagic;
  }
  
  @Override
  public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source){
    return new ShootAttack(new DragonBreathState(), myWorld, x, y, facingRight, source);
  }
  
  @Override
  public Enemies getType(){
    return friend;
  }
  
  @Override
  public int getAttack(){
    return attack;
  }
  
  @Override
  public int getDefense(){
    return defense;
  }
  
  @Override
  public int getSpecialAttack(){
    return spAttack;
  }
  
  @Override
  public int getSpecialDefense(){
    return spDefense;
  }

  @Override
  public int getCatchRate(){
    return friend.catchRate;
  }
  
  @Override
  public int getSpeed(){
    return speed;
  }
  
  public void forceEvolve(int numberOfEvolution){
    this.evolve(numberOfEvolution);
    setStats();
    setHealth(getMaxHealth());
  }
  
  @Override
  public String getName(){
    return this.getType().toString();
  }
}
