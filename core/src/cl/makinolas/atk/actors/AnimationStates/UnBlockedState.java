package cl.makinolas.atk.actors.AnimationStates;

import cl.makinolas.atk.actors.Monsters;

public class UnBlockedState extends AnimationState{

  public UnBlockedState(Monsters monster, float frameTime, int[] animations) {
    super(monster, animations, frameTime);
  }
}
