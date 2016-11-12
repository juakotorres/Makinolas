package cl.makinolas.atk.actors.attacks;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.GameConstants;
import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.states.SpriteState;
import cl.makinolas.atk.types.IType;

public class ParabolicAttack extends Attacks {

  private boolean firstAttack;

  public ParabolicAttack(SpriteState spriteState, World myWorld, float x, float y, boolean facingRight, Monsters source) {
    super(myWorld, x, y, facingRight, source, false);
    
    mySpriteState = spriteState;
    
    spriteState.setAttack(this);
    spriteState.initializeBody(x,y + 0.5f);     
    
    myBody.setGravityScale(1);
    
    setSprite();
    firstAttack = true;
    
    yVelocity = Math.abs((float) (xVelocity * Math.sqrt(2)));
    xVelocity = (float) (xVelocity * Math.sqrt(2));
  }  
  
  
  @Override
  public void act(float delta){
    if(firstAttack){
      myBody.setLinearVelocity(xVelocity, yVelocity);
      firstAttack = false;
    }
  }
  
  
  protected void setSprite() {
    setMasterTexture(mySpriteState.getTexture(),mySpriteState.getWidth(),mySpriteState.getHeight());
    addAttackAnimation(mySpriteState.getFrameTime(), mySpriteState.getModeAnimation(), mySpriteState.getInitialSprite(), mySpriteState.getFinalSprite());
  }
  
  @Override
  public int getAttackDamage() {
    return mySpriteState.getAttackDamage();
  }

  @Override
  public Monsters getSource() {
    return mySource;
  }
  
  @Override
  public void setDead(){
    dead = true;
  }
  
  @Override
  public boolean isDead(){
    return dead;
  }
  
  public float getXVelocity(){
    return xVelocity;
  }
  
  @Override
  public void setSource(Monsters monsters){
    this.mySource = monsters;
  }

  @Override
  protected void setAnimation() {
    
  }
  
  
  @Override
  public void draw(Batch batch, float alpha){
      Vector2 myPosition = myBody.getPosition();
      Vector2 myVelocity = myBody.getLinearVelocity();
      TextureRegion actualSprite = getActualSprite();
      batch.draw(actualSprite, myPosition.x * GameConstants.WORLD_FACTOR - actualSprite.getRegionWidth() / 2 , myPosition.y * GameConstants.WORLD_FACTOR - actualSprite.getRegionHeight() / 2,
              actualSprite.getRegionWidth() / 2, actualSprite.getRegionHeight() / 2, actualSprite.getRegionWidth(), actualSprite.getRegionHeight(),
              getScaleX(), getScaleY(), 180f*(1-(isFacingRight? 0:1)) + (float) (180/Math.PI * Math.atan(myVelocity.y/myVelocity.x)));
  }
  
}
