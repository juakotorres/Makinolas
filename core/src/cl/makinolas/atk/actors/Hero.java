package cl.makinolas.atk.actors;

import cl.makinolas.atk.actors.items.ItemActor;
import cl.makinolas.atk.stages.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

import cl.makinolas.atk.GameConstants;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.ShootAttack;
import cl.makinolas.atk.actors.attacks.states.DragonBreathState;
import cl.makinolas.atk.actors.bosses.IBoss;
import cl.makinolas.atk.actors.enemies.Enemy;
import cl.makinolas.atk.actors.enemies.MonsterFactory;
import cl.makinolas.atk.actors.friend.Enemies;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.actors.friend.FriendDescriptor;
import cl.makinolas.atk.actors.fx.FxManager;
import cl.makinolas.atk.actors.fx.FxManager.Fx;
import cl.makinolas.atk.actors.items.Ball;
import cl.makinolas.atk.actors.items.BallActor;
import cl.makinolas.atk.actors.items.Inventory;
import cl.makinolas.atk.actors.platform.Platform;
import cl.makinolas.atk.actors.ui.MainBar;
import cl.makinolas.atk.screen.MapScreen;
import cl.makinolas.atk.start.GameText;
import cl.makinolas.atk.utils.Formulas;
import cl.makinolas.atk.utils.SaveDoesNotExistException;
import cl.makinolas.atk.utils.SaveManager;


public class Hero extends Monsters {

  public static Hero player = new Hero();
  private boolean changing;
  private int changeIndex;
  private boolean isJumping;
  private boolean isDamaged;
  private boolean isAttacking;
  private int[] attackAnimations;
  private int actualAnimation;
  protected final float spriteTime = 1 / 5f;
  private float countMeleeFrames;
  private boolean dead;
  private float jumpAccumulator;
  private boolean isAccumulatingJump;
  private World myWorld;
  private int walkAnimation;
  private int hurtAnimation;
  private final float hurtTime = 1 / 4f;
  private float accumulator;
  private Array<Friend> allies;
  private Friend actualFriend;
  private int indexFriend;
  private BodyDef myBodyDefinition;
  private Inventory inventory;
  private int vx;
  private boolean inertia;
  private boolean hasEvolved;
  private boolean[] levelsUnlocked;
  private JumpState state;
  private boolean onWall = false;
  private Spot currentSpot;
  private Vector2 platformSpeed;

  private Hero() {

    isJumping = false;
    isFacingRight = false;
    isDamaged = false;
    isAttacking = false;
    hasEvolved = false;
    dead = false;
    changing = false;
    isAccumulatingJump = false;
    changeIndex = 0;
    jumpAccumulator = 3;
    accumulator = 0;
    vx = 0;
    platformSpeed = new Vector2(0,0);
    inertia = false;

    // Set team for player;
    allies = new Array<Friend>();
    loadFriends();

    //Inventory uses loaded data
    inventory = new Inventory(this);

    // Set actual allie
    actualFriend = allies.get(0);
    indexFriend = 0;
    parent = actualFriend;
    
    // Set correct collider.
    myBodyDefinition = new BodyDef();
    myBodyDefinition.type = BodyDef.BodyType.DynamicBody;
    
    // Guardar animaciones del jugador
    setAnimation();
    changeAnimation(walkAnimation);
    state = new OnGround();
    myBodyDefinition.fixedRotation = true;
    
    
  }
  
  private void loadFriends() {
    try {
      SaveManager.getInstance().loadData(GameText.savePath);
    } catch (SaveDoesNotExistException e) {
      addAllie(MonsterFactory.getInstance().getHeroFriend("Kakuna", 6));
      addAllie(MonsterFactory.getInstance().getHeroFriend("Scyther", 20));
      return;
    }
    if(SaveManager.getInstance().hasSaveInstance()){
      FriendDescriptor[] friends = SaveManager.getInstance().getSaveInstance().friends;
      levelsUnlocked = SaveManager.getInstance().getSaveInstance().levelsUnlocked;

      if(Levels.values().length > levelsUnlocked.length){
        boolean[] newlevels = new boolean[Levels.values().length];
        for(int i = 0; i < Levels.values().length; i++){
          if(i < levelsUnlocked.length)
            newlevels[i] = levelsUnlocked[i];
          else
            newlevels[i] = false;
        }
        levelsUnlocked = newlevels;
      }

      if(friends.length == 0){
        addAllie(MonsterFactory.getInstance().getHeroFriend("Kakuna", 6));
      } else {
        for(int i = 0; i < friends.length; i++){
          addAllie(MonsterFactory.getInstance().getHeroFriend(friends[i].name, friends[i].level,
                                                              friends[i].exp, friends[i].individualValue,
                                                              friends[i].ev1, friends[i].ev2));
        }
      }
    } else {
      addAllie(MonsterFactory.getInstance().getHeroFriend("Kakuna", 6));
      addAllie(MonsterFactory.getInstance().getHeroFriend("Scyther", 20));
    }
  }

  public static Hero getInstance(){
    return player;
  }

  public void reset(){
    player = new Hero();
  }
  
  public World getMyWorld(){
    return myWorld;
  }
  
  public void setWorld(World myWorld, Vector2 initialPosition){
    this.myWorld = myWorld;
    isJumping = false;

    setSizeCollider(initialPosition, true);
    // Guardar animaciones del jugador
    setAnimation();
    changeAnimation(walkAnimation);
  }

  public void setWorld(World myWorld){
    setWorld(myWorld, new Vector2(2,3));
  }
  
  public void addAllie(Friend friend) {
    if(allies.size<4)
      allies.add(friend);
  }

  @Override
  public void act(float delta){
    checkChangingAllie();

    myBody.setLinearVelocity(vx + platformSpeed.x, myBody.getLinearVelocity().y);

    ((AbstractStage) getStage()).changeCamera(myBody.getPosition().x , myBody.getPosition().y );
    
    checkDamage(delta);
    checkMelee(delta);
    checkEvolution();
    checkAccumulatingJump();
    giveMagic();
    
    if (isJumping)
    	state.countFrames();
  }

  public void setMovablePLatformSpeed(float vX, float vY) {
    this.platformSpeed = new Vector2(vX, vY);
  }

  private void checkAccumulatingJump() {
   if(isAccumulatingJump && !isJumping){
     increaseJumpAccumulator();
   }    
  }

  private void checkEvolution() {
    if(hasEvolved){
      GameActor puff = new Puff(myWorld, myBody.getPosition().x,myBody.getPosition().y,isFacingRight, this);
      ((AbstractStage) getStage()).addGameActor(puff);
      hasEvolved = false;
      MainBar.getInstance().setBars();
    }
    
  }

  private void checkChangingAllie() {
    if(changing){
      setNewAllie(changeIndex);
      changing = false;
    }
  }

  private void changeAllie() {
    changing = true;
    actualFriend.isDead();
    lookForAliveAllie();
  }

  private void lookForAliveAllie() {
    for(int i = 0; i < allies.size; i++){
      if(!allies.get(i).getDead()){
        changeIndex = i; 
      }
    }
    if(allies.get(changeIndex).getDead()){
      heroIsDead();
    }
  }

  private void heroIsDead() {
    dead = true;   
  }

  @Override
  public boolean isDead(){
    return dead;
  }
  
  private void checkDamage(float delta) {
    if(isDamaged){
      accumulator += delta;
      if(accumulator > hurtTime){
        isDamaged = false;
        changeAnimation(walkAnimation);
        accumulator = 0;
      }
    }
  }

  private void giveMagic() {
    if(actualFriend.getMagic() < 1000){
      actualFriend.setMagic(((actualFriend.getMagic() + 1)%1001));
    } else {
      actualFriend.setMagic(1000);
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

  @Deprecated
  private float getImpulse(float impulse) {
    return getBody().getMass()*impulse; // El 12 se busco por testing.
  }

  public void landedPlatform(WorldManifold worldManifold, Platform platform){
    for(int i = 0; i < worldManifold.getNumberOfContactPoints(); i++){
      if(worldManifold.getPoints()[i].y < myBody.getPosition().y && (worldManifold.getNormal().y > 0.95 || worldManifold.getNormal().y < -0.95)){
        isJumping = false;
        setState(new OnGround());
        myBody.setGravityScale(1);
        onWall = false;
      }
      else {
    	  isJumping = false;
    	  if (!onWall)
    		  setState(new OnWall());
    	  else
    		  myBody.setGravityScale(1);
    	  onWall = true;
      }
    }
  }

  private void setAnimation(){
    setMasterTexture(actualFriend.getTexture(),actualFriend.getWidth(),actualFriend.getHeight());
    walkAnimation = addAnimation(0.2f, actualFriend.getWalkAnimation());
    hurtAnimation = addAnimation(0.2f, actualFriend.getHurtAnimation());
    attackAnimations = new int[actualFriend.getMeleeAnimation().length];
    countMeleeFrames = 0;
    for(int i = 0; i < actualFriend.getMeleeAnimation().length; i++){
      attackAnimations[i] = addAnimation(0.2f, actualFriend.getMeleeAnimation()[i][1]);
    }  
    actualAnimation = 0;
  }
  
  @Override
  public boolean isHero(){
    return true;
  }

  public int getHealth(){
    return actualFriend.getHealth();
  }
  
  public int getMagic(){
    return actualFriend.getMagic();
  }


  @Override
  public void damage(int damage, Attacks inflictor)  {
    if(!inflictor.getSource().isHero()){
      actualFriend.setHealth(actualFriend.getHealth() - damage);
      isDamaged = true;
      changeAnimation(hurtAnimation);
      inflictor.setDead();
    }
    if(getHealth() <= 0){
      changeAllie();
    }
  }


  @Override
  public int getMeleeDamage() {
    return 50;
  }
  
  public Friend getFriend(){
    return actualFriend;
  }
  
  private void setNewAllie(int index){
    if(!isJumping || actualFriend.getDead()){
      GameActor puff = new Puff(myWorld, myBody.getPosition().x,myBody.getPosition().y,isFacingRight, this);
      ((AbstractStage) getStage()).addGameActor(puff);
      allies.set(indexFriend, actualFriend);
      actualFriend = allies.get(index);
      indexFriend = index;
      parent = actualFriend;
      MainBar.getInstance().setBars();
      setSizeCollider(getBody().getPosition(), false);
      setAnimation();
    }
  }
  
  private void setSizeCollider(Vector2 position, boolean first) {
    myBodyDefinition.position.set(position);
    if(!first){
      myWorld.destroyBody(getBody());
    }
    Body myBody = myWorld.createBody(myBodyDefinition);
    PolygonShape shape = new PolygonShape();
    shape.setAsBox(getBodySize(actualFriend.getWidth()), getBodySize(actualFriend.getHeight()));
    myBody.setGravityScale(1);
    myBody.createFixture(shape, 0.5f);
    myBody.resetMassData();
    shape.dispose();
    
    // Change Body.
    setBody(myBody);
  }

  // This is used to get body width and height.
  private float getBodySize(int size){
    return (0.5f*size)/22;
  }
  
  public void evolved(){
    setAnimation();
    hasEvolved = true;
  }

  @Override
  public void interact(GameActor actor2, WorldManifold worldManifold){
    actor2.interactWithHero(this, worldManifold);
  }
  
  @Override
  public void interactWithPlatform(Platform platform, WorldManifold worldManifold){
    platform.interactWithHero(this, worldManifold);
  }
  
  @Override
  public void interactWithAttack(Attacks attack, WorldManifold worldManifold){
    attack.manageInteractWithMonster(this, worldManifold);    
  }

  @Override
  public void interactWithBoss(IBoss boss){
    interactWithMonster(boss.getBoss());
    boss.getBoss().interactWithHero2(this);
  }
  
  @Override
  public void interactWithEnemy(Enemy enemy, WorldManifold worldManifold){
    interactWithMonster( enemy);
    enemy.interactWithHero2(this);
  }

  public void interactWithMonster(Monsters monster) {
    meleeAttack(monster, isAttacking);  
  }
  
  @Override
  public void interactWithPortal(Portal portal){
    portal.completeStage();
  }

  public void endPlatformInteraction(Platform platform, WorldManifold worldManifold) { platform.endHeroInteraction(this, worldManifold);}

  @Override
  public float getMonsterWidth() {
    return getBodySize(actualFriend.getWidth());
  }

  @Override
  public float getMonsterHeight() {
    return getBodySize(actualFriend.getHeight());
  }


  public Inventory getInventory() {
    return inventory;
  }

  public void moveHorizontal(int i, boolean restitutive) {
    if(restitutive && !inertia) return;
    vx += 7*i;
    if(vx!=0)
      isFacingRight = (vx>0);
    inertia = true;
  }


  public void jump(int button) {
	isJumping = true;
	state.restarCount();
    state.jump();
  }
  
  public void isNotPressingSpace() {
	  isJumping = false;
	  state.release();
  }

  private void increaseJumpAccumulator() {
    if(jumpAccumulator < 12){
      jumpAccumulator += 1;
    }    
  }

  public void attackPrimary() {
    if(actualFriend.getMagic() >= 100){
      actualFriend.setMagic(actualFriend.getMagic() - 100);
      GameActor fireball = actualFriend.getFriendAttack(myWorld, myBody.getPosition().x,myBody.getPosition().y,isFacingRight, this);
      ((AbstractStage) getStage()).addGameActor(fireball);
    }
  }

  public void attackSecondary() {
    if(!isAttacking){
      isAttacking = true;
    }
  }


  public void changeAllie(int index) {
    if(indexFriend != index && !allies.get(index).getDead()){
      setNewAllie(index);
    }
  }

  @Override
  protected void gainExp(int enemyLevel, Enemies type) {
    actualFriend.gainExperience(enemyLevel, type);
  }

  public void throwBall(Ball.BallType type) {
    BallActor ball = new BallActor(type, myWorld, myBody.getPosition().x + ((isFacingRight)?0.6f:-0.6f)*actualFriend.getWidth()/ GameConstants.WORLD_FACTOR,
            myBody.getPosition().y);
    ball.setThrowImpulse((isFacingRight)?1:-1);
    ((AbstractStage) getStage()).addGameActor(ball);
  }

  public void nextAllie() {
    for (int i = 1; i <= allies.size; i++) {
      int j = (indexFriend + i) % allies.size;
      if(!allies.get(j).getDead()) {
        changeAllie(j);
        break;
      }
    }
  }

  public void prevAllie(){
    for (int i = 1; i <= allies.size; i++) {
      int j = (indexFriend - i + allies.size) % allies.size;
      if(!allies.get(j).getDead()) {
        changeAllie(j);
        break;
      }
    }
  }

  @Override
  public float getXDirection(){
      return vx;
  }

  public FriendDescriptor[] saveMyFriends() {
    FriendDescriptor[] friends = new FriendDescriptor[allies.size];
    for(int i = 0; i < allies.size; i++){
      Friend ally = allies.get(i);
      friends[i] = new FriendDescriptor();
      friends[i].individualValue = ally.getIvs();
      friends[i].ev1 = ally.getEv1();
      friends[i].ev2 = ally.getEv2();
      friends[i].name = ally.getName();
      friends[i].level = ally.getLevel();
      friends[i].exp = (int) ally.getNextExperience();
    }
    return friends;
  }
  public Array<Friend> getAllies(){
    return allies;
  }

  public int getIndexFriend(){
    return indexFriend;
  }

  public void earnMoney(int level, Enemies type) {
    inventory.earnMoney(Formulas.getMoney(level,type.baseExperience));
  }
    
  public boolean hasBody() {
    return myBody != null;
  }

  public void setSpot(Spot spot){
    currentSpot = spot;
  }

  public void completeStage(Game myGame){
    AbstractStage myStage = ((AbstractStage) getStage());
    Levels actualLevel = myStage.getLevel();
    
    AbstractStage.music.stop();

    int[] levels = actualLevel.unlockableLevels;
    for(int level : levels){
      levelsUnlocked[level] = true;
    }
    SaveManager.getInstance().saveState();

    vx = 0;

    MapScreen mapScreen = new MapScreen(myGame,new MapStage(new FitViewport(640, 480),myGame, currentSpot));
    myGame.setScreen(mapScreen);
  }



  public boolean[] getLevelsUnlocked() {
    return levelsUnlocked;
  }

  public float getStageX(){
    return myBody.getPosition().x * GameConstants.WORLD_FACTOR;
  }

  public float getStageY(){
    return myBody.getPosition().y * GameConstants.WORLD_FACTOR;
  }

  @Override
  public void setState(JumpState state) {
      this.state = state;
  }

  @Override
  public void setSpeed(float x, float y) {
      myBody.setLinearVelocity(x, y);
  }

  @Override
  public void endInteraction(GameActor actor2, WorldManifold worldManifold) {
    actor2.endHeroInteraction(this, worldManifold);
  }

  @Override
  public void interactWithItem(ItemActor item) {
    item.interactWithHero(this,null);
  }
  
  public void CriticalDamage() {
		FxManager.getInstance().addFx(FxManager.Fx.CRITICAL,  this.getStageX(),this.getStageY());
  }


}
