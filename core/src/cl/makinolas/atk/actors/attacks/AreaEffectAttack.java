package cl.makinolas.atk.actors.attacks;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.states.SpriteState;
import cl.makinolas.atk.actors.platform.Platform;

public class AreaEffectAttack extends Attacks {
	
	  protected float accumulator;
	  protected float attackTime;

	public AreaEffectAttack(SpriteState spriteState,World myWorld, float x, float y, boolean facingRight, Monsters source, boolean rotated) {
		super(myWorld, x, y, facingRight, source, rotated);
	
	
	    mySpriteState = spriteState;
	    
	    spriteState.setAttack(this);
	    spriteState.initializeBody(x,y); 
	    
	    source.sing();
	    
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
    myBody.setLinearVelocity(Hero.getInstance().getBody().getLinearVelocity().x, Hero.getInstance().getBody().getLinearVelocity().y);
    checkFinish(delta);
  }
  
  protected void checkFinish(float delta) {
    accumulator += delta;
    if(accumulator >= attackTime){
    	this.getSource().unSing();
      dead = true;
    }    
  }


  @Override
  public Monsters getSource() {
    return mySource;
  }
  
  @Override
  public void setDead(){}
  
  @Override
  public void unPress(){
	  this.getSource().unSing();
      dead = true;
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
