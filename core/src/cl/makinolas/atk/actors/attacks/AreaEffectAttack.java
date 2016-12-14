package cl.makinolas.atk.actors.attacks;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.states.SpriteState;
import cl.makinolas.atk.actors.platform.Platform;

public class AreaEffectAttack extends Attacks {
	
	private ArrayList<Monsters> monsterList;
	private float localTime = 0;
		
	  protected float accumulator;
	  protected float attackTime;

	public AreaEffectAttack(SpriteState spriteState,World myWorld, float x, float y, boolean facingRight, Monsters source, boolean rotated) {
		super(myWorld, x, y, facingRight, source, rotated);
	
		monsterList = new ArrayList<Monsters>();
		
	    mySpriteState = spriteState;
	    
	    spriteState.setAttack(this);
	    spriteState.initializeBody(x,y); 
	    
	    xVelocity = 0;
	    yVelocity = 0;
	    accumulator = 0;
	    
	    this.initialPosition= (facingRight)? -1f: 1f;
	    this.initialPosition *= source.getMonsterWidth() * 3;
	    
	    setSprite();
  }
  
  @Override
  public void act(float delta){
	  super.act(delta);
    myBody.setLinearVelocity(getSource().getBody().getLinearVelocity().x, getSource().getBody().getLinearVelocity().y);
    checkFinish(delta);
	localTime+=delta;
	if(this.localTime>0.5){
		this.localTime -= 0.5 ;
	    damage();
	}
  }
  
  protected void checkFinish(float delta) {
    accumulator += delta;
    if(accumulator >= attackTime){
    	unPress();
    }    
  }


  @Override
  public Monsters getSource() {
    return mySource;
  }

  
	@Override
	public void manageInteractWithMonster(Monsters monster) {
		monsterList.add(monster);
	}
	
	@Override
	  public void endMonsterIntercation(Monsters monsters, WorldManifold worldManifold) {
		monsterList.remove(monsters);
	}
	
	public void damage(){
		for(Monsters monster : monsterList){
			monster.damage(getAttackDamage(monster), this);
		}
	}

	
  @Override
  public void setDead(){}
  
  @Override
  public void unPress(){
      dead = true;
	  this.getSource().unSing();
  }
  
  public void unPressButton() {
	  if(attackTime- accumulator > 1){
		  accumulator = attackTime -1;
	  }
  }
  
  @Override
  public boolean isDead(){
    return dead;
  }
  
  @Override
  public void setSource(Monsters monsters){
    this.mySource = monsters;
  }

  @Override
  public int getAttackDamage() {
    return mySpriteState.getAttackDamage();
  }

  protected void setSprite() {
    setMasterTexture(mySpriteState.getTexture(),mySpriteState.getWidth(),mySpriteState.getHeight());
    addAttackAnimation(mySpriteState.getFrameTime(), mySpriteState.getModeAnimation(), mySpriteState.getInitialSprite(), mySpriteState.getFinalSprite());
    attackTime = mySpriteState.getAttackTime();
  }

  @Override
  protected void setAnimation() {
    
  }
  
  @Override
  public void interactWithPlatform(Platform platform, WorldManifold worldManifold){
    yVelocity = 0;
  }

}
