package cl.makinolas.atk.actors.attacks;

import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;

public abstract class BombAttack extends Attacks{

  protected float accumulator;
  protected float attackTime;
  
  public BombAttack(World myWorld, float x, float y, boolean facingRight, Monsters source) {
    super(myWorld, x, y, facingRight, source);
    
    xVelocity =0;
    accumulator = 0;
    
    this.initialPosition= (facingRight)? 1f: -1f;
    this.initialPosition *= source.getMonsterWidth() * 3;
    
    // Guardar animaciones del jugador
    setAnimation();
  }
  
  @Override
  public void act(float delta){
    checkFinish(delta);
  }
  
  private void checkFinish(float delta) {
    accumulator += delta;
    if(accumulator >= attackTime){
      dead = true;
    }    
  }


  @Override
  public Monsters getSource() {
    return mySource;
  }
  
  @Override
  public void setDead(){
    
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
