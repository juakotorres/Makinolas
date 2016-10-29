package cl.makinolas.atk.actors.AnimationStates;

import cl.makinolas.atk.actors.Monsters;

public class BlockedState extends AnimationState{

  public BlockedState(Monsters monster, float frameTime, int[] animations) {
    super(monster, animations, frameTime);
    blocked = true;
  }
}
