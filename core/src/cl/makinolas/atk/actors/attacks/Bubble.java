package cl.makinolas.atk.actors.attacks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;

public class Bubble extends Attacks {
  
  private float attackTime;
  private float accumulator;
  
  public Bubble(World myWorld, float x , float y, boolean facingRight, Monsters source){
    super(myWorld, x, y, facingRight, source);

    accumulator = 0;
    
    this.xVelocity = (facingRight)? 10: -10;
    this.initialPosition= (facingRight)? 1f: -1f;
    this.initialPosition = this.initialPosition * source.getMonsterWidth() * 2;
    initializeBody(x, y, 0.5f, 0.5f);
    
  }
  
  @Override
  public void act(float delta){
    myBody.setLinearVelocity(xVelocity, 0);
    checkFinish(delta);
  }
  
  private void checkFinish(float delta) {
    accumulator += delta;
    if(accumulator >= attackTime){
      dead = true;
    }
    
  }

  @Override
  protected void setAnimation(){
    setMasterTexture(new TextureRegion(new Texture(Gdx.files.internal("Attacks/Bubble.png"))),15, 15);
    addAttackAnimation(0.2f, Animation.PlayMode.LOOP, 0, 4);
    attackTime = 5 * 0.2f;    
  }

  @Override
  public int getAttackDamage() {
    return 40;
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
  
  @Override
  public void setSource(Monsters monsters){
    this.mySource = monsters;
  }

}
