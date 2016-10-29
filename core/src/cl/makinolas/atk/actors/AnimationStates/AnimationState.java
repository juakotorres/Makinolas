package cl.makinolas.atk.actors.AnimationStates;

import cl.makinolas.atk.actors.Monsters;

public abstract class AnimationState {

  protected Monsters source;
  protected int[] myAnimation;
  protected boolean blocked;
  private float timeFrame;
  private float accumulator;
  private int currentAnimation;

  public AnimationState(Monsters monster, int[] animation, float timeFrame){
    source = monster;
    myAnimation = animation;
    blocked = false;
    accumulator = 0;
    this.timeFrame = timeFrame;
    currentAnimation = 0;
  }

  public boolean getBlocked(){
    return blocked;
  }

  public void act(float delta){
    accumulator += delta;
    if(accumulator > timeFrame){
      accumulator = 0;
      currentAnimation += 1;
    }
  }

  public int getActualAnimation(){
    return myAnimation[currentAnimation];
  }

  public boolean finished(){
    return currentAnimation > myAnimation.length - 1;
  }


  public void reset() {
    accumulator = 0;
    currentAnimation = 0;
  }
}
