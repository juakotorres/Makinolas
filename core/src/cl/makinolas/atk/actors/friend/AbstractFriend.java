package cl.makinolas.atk.actors.friend;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.GameActor;
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
import cl.makinolas.atk.types.IType;
import cl.makinolas.atk.utils.Formulas;

public abstract class AbstractFriend implements Friend {
  
  private int health;
  private int hp;
  private int ivs;
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
  private int[][] singAnimation;
  private TextureRegion faceSprite;
  protected Level level;
  private int actualEvolution;
  public Enemies friend;
  public ArrayList<IType> type = new ArrayList<IType>();
  
  private int evs1;
  private int evs2;
  private int totalEvs;
  private int evHp;
  private int evAttack;
  private int evDefense;
  private int evSpAttack;
  private int evSpDefense;
  private int evSpeed;
  private int criticModificator;

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
  
  protected void setSingAnimation(int beginSpecialAnimation, int endSpecialAnimation){
	    this.singAnimation = new int[endSpecialAnimation - beginSpecialAnimation + 1][];
	    for (int i = beginSpecialAnimation; i <= endSpecialAnimation; i++ ){
	      this.singAnimation[i - beginSpecialAnimation] = new int[]{0,i};
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
  public int[][] getSingAnimation() {
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
    //private boolean evolved;
    
    public Evolution(Level level, float evolLevel, int numberOfEvolution){
      observe(level);
      this.evolLevel = evolLevel;
      this.numberOfEvolution = numberOfEvolution;
      //evolved = false;
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
       //evolved = true;
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
    this.hp = Formulas.getHpStatWithIV(friend.hpBase , level.level, getIVStat(6), evHp);
    this.attack = Formulas.getOtherStatWithIV(friend.attackBase, level.level, getIVStat(5), evAttack);
    this.defense = Formulas.getOtherStatWithIV(friend.defenseBase, level.level, getIVStat(4), evDefense);
    this.spAttack = Formulas.getOtherStatWithIV(friend.spAttackBase, level.level, getIVStat(3), evSpAttack);
    this.spDefense = Formulas.getOtherStatWithIV(friend.spDefenseBase, level.level, getIVStat(2), evSpDefense);
    this.speed = Formulas.getOtherStatWithIV(friend.speedBase, level.level, getIVStat(1), evSpeed);
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
  public Enemies getFriend(){
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
  
  public boolean secondaryAttack(){
	  return false;
  }
  
  public GameActor getFriendSecondaryAttack(World myWorld, float f, float y, boolean isFacingRight,
			Monsters source){
	  return null;
  }

  /**
   * HP -> 6
   * Attack -> 5
   * Defense -> 4
   * Special Attack -> 3
   * Special Defense -> 2
   * Speed -> 1
   * @param id specified with values above
   * @return individual value for the id stat
   */
  protected int getIVStat(int id){
    return (ivs >> (id - 1) * 5) & 0x0000001F;
    /*int ivStat = ivs;
    int mask = 0x0000001F;
    ivStat = ivStat >> (id - 1) * 5;
    ivStat = ivStat & mask;
    return ivStat;*/
  }

  protected void newMonster(){
    ivs = newIVs();
    evs1 = 0;
    evs2 = 0;
    totalEvs = 0;
    evHp = 0;
    evAttack = 0;
    evDefense = 0;
    evSpAttack = 0;
    evSpDefense = 0;
    evSpeed = 0;
    criticModificator = 0;
  }

  private int newIVs(){
    int ivs = 0;
    for(int i = 0; i < 30; i++) {
      int n = Math.random() > 0.5 ? 1 : 0;
      ivs = ivs | n;
      ivs = ivs << 1;
    }
    return ivs;
  }
  
  @Override
  public int getSpeed(){
    return speed;
  }
  
	@Override
	public int getCriticModificator() {
		return criticModificator;
	}

  public void forceEvolve(int numberOfEvolution){
    this.evolve(numberOfEvolution);
    setStats();
    setHealth(getMaxHealth());
  }

  @Override
  public void setIvs(int individualValue){
    ivs = individualValue;
  }

  @Override
  public int getIvs(){
    return ivs;
  }
  
  @Override
  public String getName(){
    return this.getFriend().toString();
  }

  @Override
  public ArrayList<IType> getType(){
    return this.type;
  }
  
  @Override
  public void addType(IType type){
	  this.type.add(type);
  }
  
  @Override
  public void resetType(){
	  this.type = new ArrayList<IType>();
  }  

  @Override
  public void setEvs(int ev1, int ev2){
    evs1 = ev1;
    evs2 = ev2;

    shiftEvs1(ev1, ev2);
    totalEvs = evHp + evAttack + evDefense + evSpAttack + evSpDefense + evSpeed;
  }

  /**
   * [Hp|At|Df]
   * [SpAt|SpDf|Speed]
   * @param ev1 first effort value
   * @param ev2 second effort value
   *
   */
  private void shiftEvs1(int ev1, int ev2) {
    int mask = 0x000000FF;
    evDefense = ev1 & mask;
    evSpeed = ev2 & mask;
    ev1 >>= 8;
    ev2 >>= 8;
    evAttack = ev1 & mask;
    evSpDefense = ev2 & mask;
    ev1 >>= 8;
    ev2 >>= 8;
    evHp = ev1 & mask;
    evSpAttack = ev2 & mask;
  }

  @Override
  public int getEv1(){
    return ((((evs1 | evHp) << 8) | evAttack) << 8) | evDefense;
  }

  @Override
  public int getEv2(){
    return ((((evs2 | evSpAttack) << 8) | evSpDefense) << 8) | evSpeed;
  }

  @Override
  public void addHpEv(int n){
    int sum = (totalEvs + n > 510)? 510-totalEvs : n;
    if(evHp < 255){
      int posibleSum = (evHp + sum > 255)? 255-evHp: sum;
      evHp += posibleSum;
      totalEvs += posibleSum;
    }
  }

  @Override
  public void addAttackEv(int n){
    int sum = (totalEvs + n > 510)? 510-totalEvs : n;
    if(evAttack < 255){
      int posibleSum = (evAttack + sum > 255)? 255-evAttack: sum;
      evAttack += posibleSum;
      totalEvs += posibleSum;
    }
  }

  @Override
  public void addDefenseEv(int n){
    int sum = (totalEvs + n > 510)? 510-totalEvs : n;
    if(evDefense < 255){
      int posibleSum = (evDefense + sum > 255)? 255-evDefense: sum;
      evDefense += posibleSum;
      totalEvs += posibleSum;
    }
  }

  @Override
  public void addSpAttackEv(int n){
    int sum = (totalEvs + n > 510)? 510-totalEvs : n;
    if(evSpAttack < 255) {
      int posibleSum = (evSpAttack + sum > 255) ? 255 - evSpAttack : sum;
      evSpAttack += posibleSum;
      totalEvs += posibleSum;
    }
  }

  @Override
  public void addSpDefenseEv(int n){
    int sum = (totalEvs + n > 510)? 510-totalEvs : n;
    if(evSpDefense < 255){
      int posibleSum = (evSpDefense + sum > 255)? 255-evSpDefense: sum;
      evSpDefense += posibleSum;
      totalEvs += posibleSum;
    }
  }

  @Override
  public void addSpeedEv(int n){
    int sum = (totalEvs + n > 510)? 510-totalEvs : n;
    if(evSpeed < 255){
      int posibleSum = (evSpeed + sum > 255)? 255-evSpeed: sum;
      evSpeed += posibleSum;
      totalEvs += posibleSum;
    }
  }
  
  public int getAttackiv(){
	  return this.evAttack;
  }
  
  public void setAttackiv(int val){
	  this.evAttack = val;
  }
  
  public void setCriticModificator(int val){
	  this.criticModificator = val;
  }
  public int getAttackMagicRequirement() {
	// TODO Auto-generated method stub
	return DragonBreathState.magicRequirement;
  }
  
}
